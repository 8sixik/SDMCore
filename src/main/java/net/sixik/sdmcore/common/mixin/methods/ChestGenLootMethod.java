package net.sixik.sdmcore.common.mixin.methods;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.sixik.sdmcore.common.events.SDMPlayerEvents;


import java.util.Objects;

public class ChestGenLootMethod {
    public static void onLootGen(Container inventory, LootContext context){
        try {

            if(context.hasParam(LootContextParams.THIS_ENTITY) && context.hasParam(LootContextParams.ORIGIN)
                    && context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player){
                BlockEntity chest = null;
                BlockPos pos = new BlockPos((Vec3) Objects.requireNonNull(context.getParamOrNull(LootContextParams.ORIGIN)));
                Player player = (Player) context.getParamOrNull(LootContextParams.THIS_ENTITY);
                Level world = player.level;
                if(inventory instanceof BlockEntity){
                    chest = (BlockEntity) inventory;
                }

                if(world == null){
                    return;
                }

                if(chest instanceof RandomizableContainerBlockEntity){
                    MinecraftForge.EVENT_BUS.post(new SDMPlayerEvents.PlayerChestLootEvent(player, context, inventory, pos));
                }
            }

        } catch (Exception var6){
            var6.printStackTrace();
        }
    }
}
