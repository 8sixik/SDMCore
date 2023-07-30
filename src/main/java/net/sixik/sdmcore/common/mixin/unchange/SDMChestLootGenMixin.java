package net.sixik.sdmcore.common.mixin.unchange;

import net.minecraft.world.Container;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.sixik.sdmcore.common.mixin.methods.ChestGenLootMethod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LootTable.class)
public class SDMChestLootGenMixin {

    @Inject(
            method = {"fill"},
            at = {@At("RETURN")}
    )
    public void ls$$onLootGenMixin(Container inventory, LootContext context, CallbackInfo ci) {
        try {
            ChestGenLootMethod.onLootGen(inventory, context);
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }
}
