package net.sixik.sdmcore.common.mixin.unchange;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.sixik.sdmcore.common.events.SDMPlayerEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class SDMPlayerMixin {

    @Inject(at = @At("HEAD"),
            method = "Lnet/minecraft/world/entity/player/Player;eat(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;")
    public void ctus$$FoodEating(Level level, ItemStack stack, CallbackInfoReturnable<ItemStack> cir){
        MinecraftForge.EVENT_BUS.post(new SDMPlayerEvents.PlayerEatFoodEvent(((Player)(Object)this), stack));
    }
}
