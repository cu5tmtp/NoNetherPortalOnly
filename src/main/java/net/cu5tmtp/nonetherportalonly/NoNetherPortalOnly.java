package net.cu5tmtp.nonetherportalonly;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(NoNetherPortalOnly.MODID)
public class NoNetherPortalOnly {
    public static final String MODID = "nonetherportalonly";

    public NoNetherPortalOnly() {
        MinecraftForge.EVENT_BUS.register(ForgeEvents.class);
    }

    public static class ForgeEvents {
        @SubscribeEvent
        public static void onPortalIgnition(BlockEvent.PortalSpawnEvent event) {
            PortalShape shape = event.getPortalSize();

            if (shape != null) {
                for (Direction direction : Direction.values()) {
                    if (event.getLevel().getBlockState(event.getPos().relative(direction)).is(Blocks.OBSIDIAN)) {
                        event.setCanceled(true);
                        break;
                    }
                }
            }
        }
    }
}