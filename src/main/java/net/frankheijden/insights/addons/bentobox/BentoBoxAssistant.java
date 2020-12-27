package net.frankheijden.insights.addons.bentobox;

import net.frankheijden.insights.entities.Area;
import net.frankheijden.insights.entities.CacheAssistant;
import net.frankheijden.insights.entities.CuboidSelection;
import org.bukkit.Location;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.IslandsManager;

import java.util.Collections;

public class BentoBoxAssistant extends CacheAssistant {

    private final IslandsManager manager = BentoBox.getInstance().getIslands();

    /**
     * There needs to be a constructor without parameters.
     */
    public BentoBoxAssistant() {
        super("BentoBox", "BentoBox", "island", "v1.1.0");
    }

    public String getId(Island is) {
        return getPluginName() + "@" + is.getUniqueId();
    }

    public Area adapt(Island is) {
        return Area.from(this, getId(is), Collections.singletonList(new CuboidSelection(is.getWorld(), is.getProtectionBoundingBox())));
    }

    @Override
    public Area getArea(Location location) {
        return manager.getIslandAt(location)
                .map(this::adapt)
                .orElse(null);
    }
}
