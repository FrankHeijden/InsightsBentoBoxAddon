package net.frankheijden.insights.addons.bentobox;

import net.frankheijden.insights.entities.CacheAssistant;
import net.frankheijden.insights.entities.Selection;
import org.bukkit.Location;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.IslandsManager;

public class BentoBoxAssistant extends CacheAssistant {

    private final IslandsManager manager = BentoBox.getInstance().getIslands();

    /**
     * There needs to be a constructor without parameters.
     */
    public BentoBoxAssistant() {
        super("BentoBox", "island");
    }

    public Selection adapt(Island is) {
        return new Selection(is.getWorld(), is.getProtectionBoundingBox());
    }

    @Override
    public Selection getSelection(Location location) {
        return manager.getIslandAt(location)
                .map(this::adapt)
                .orElse(null);
    }
}
