package net.sixik.sdmcore.common.mixin.unchange;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.sixik.sdmcore.common.events.SDMPlayerEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin({EnchantmentMenu.class})
public class SDMEnchantMixin {

    @Inject(method = "lambda$clickMenuButton$1(Lnet/minecraft/world/item/ItemStack;ILnet/minecraft/world/entity/player/Player;ILnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;enchant(Lnet/minecraft/world/item/enchantment/Enchantment;I)V"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void ctus$$enchantHandler(ItemStack itemstack, int p_39466_, Player player, int i, ItemStack itemstack1, Level level, BlockPos pos, CallbackInfo ci, ItemStack itemstack2, List list, boolean flag, int j, EnchantmentInstance enchantmentinstance) {
        MinecraftForge.EVENT_BUS.post(new SDMPlayerEvents.PlayerEnchantEvent(player, itemstack, pos, enchantmentinstance, level));
    }
}
