package dev.frankheijden.insights.addons.bentobox;

import dev.frankheijden.insights.api.addons.InsightsAddon;
import dev.frankheijden.insights.api.addons.Region;
import dev.frankheijden.insights.api.addons.SimpleCuboidRegion;
import dev.frankheijden.insights.api.objects.math.Vector3;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.BoundingBox;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.IslandsManager;
import java.util.Optional;

public class BentoBoxAddon implements InsightsAddon {

    private final IslandsManager manager = BentoBox.getInstance().getIslands();

    public String getId(Island is, World world) {
        return getPluginName() + "@" + is.getUniqueId() + "-" + world.getName();
    }

    public Region adapt(Island is, World world) {
        BoundingBox box = is.getProtectionBoundingBox();
        return new SimpleCuboidRegion(
                world,
                new Vector3((int) box.getMinX(), (int) box.getMinY(), (int) box.getMinZ()),
                new Vector3((int) box.getMaxX(), (int) box.getMaxY(), (int) box.getMaxZ()),
                getPluginName(),
                getId(is, world)
        );
    }

    @Override
    public String getPluginName() {
        return "BentoBox";
    }

    @Override
    public String getAreaName() {
        return "island";
    }

    @Override
    public String getVersion() {
        return "{version}";
    }

    @Override
    public Optional<Region> getRegion(Location location) {
        return manager.getIslandAt(location).map(is -> adapt(is, location.getWorld()));
    }
}
