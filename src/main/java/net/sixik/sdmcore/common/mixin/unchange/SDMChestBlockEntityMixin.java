package net.sixik.sdmcore.common.mixin.unchange;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.sixik.sdmcore.common.events.SDMPlayerEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(ChestBlockEntity.class)
public class SDMChestBlockEntityMixin {

    @Inject(method = "Lnet/minecraft/world/level/block/entity/ChestBlockEntity;startOpen(Lnet/minecraft/world/entity/player/Player;)V",
            at = @At("HEAD"))
    public void onOpen(Player p_59120_, CallbackInfo ci){
        MinecraftForge.EVENT_BUS.post(new SDMPlayerEvents.PlayerChestOpenEvent(p_59120_, (BlockEntity) (Object)this));
    }
    @Inject(method = "Lnet/minecraft/world/level/block/entity/ChestBlockEntity;stopOpen(Lnet/minecraft/world/entity/player/Player;)V",
            at = @At("HEAD"))
    public void onClose(Player p_59120_, CallbackInfo ci){
        MinecraftForge.EVENT_BUS.post(new SDMPlayerEvents.PlayerChestCloseEvent(p_59120_, (BlockEntity) (Object)this));
    }
}
