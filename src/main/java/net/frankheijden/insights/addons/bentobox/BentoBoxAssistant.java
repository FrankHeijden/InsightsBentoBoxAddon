package net.frankheijden.insights.addons.bentobox;

import net.frankheijden.insights.entities.Area;
import net.frankheijden.insights.entities.CacheAssistant;
import net.frankheijden.insights.entities.CuboidSelection;
import org.bukkit.Location;
import org.bukkit.World;
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
        super("BentoBox", "BentoBox", "island", "1.1.1");
    }

    public String getId(Island is, World world) {
        return getPluginName() + "@" + is.getUniqueId() + "-" + world.getName();
    }

    public Area adapt(Island is, World world) {
        return Area.from(this, getId(is, world), Collections.singletonList(new CuboidSelection(is.getWorld(), is.getProtectionBoundingBox())));
    }

    @Override
    public Area getArea(Location location) {
        return manager.getIslandAt(location)
                .map(is -> adapt(is, location.getWorld()))
                .orElse(null);
    }
}
