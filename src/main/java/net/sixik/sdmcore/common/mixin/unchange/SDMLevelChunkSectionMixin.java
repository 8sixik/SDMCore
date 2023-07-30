package net.sixik.sdmcore.common.mixin.unchange;

import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeResolver;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.level.chunk.PalettedContainerRO;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelChunkSection.class)
public class SDMLevelChunkSectionMixin {

    @Shadow
    private PalettedContainerRO<Holder<Biome>> biomes;

    @Shadow @Final
    private int bottomBlockY;

    @Inject(method = "fillBiomesFromNoise", at = @At("HEAD"), cancellable = true)
    public void GS$fillBiomesFromNoise(BiomeResolver biomeResolver, Climate.Sampler sampler, int posX, int posZ, CallbackInfo ci){
        ci.cancel();
        PalettedContainer<Holder<Biome>> palettedcontainer = this.biomes.recreate();
        int i = QuartPos.fromBlock(this.bottomBlockY);
        int j = 4;

        for(int k = 0; k < 4; ++k) {
            for(int l = 0; l < 4; ++l) {
                for(int i1 = 0; i1 < 4; ++i1) {
                    palettedcontainer.getAndSetUnchecked(k, l, i1, biomeResolver.getNoiseBiome(posX + k, i + l, posZ + i1, sampler));

                }
            }
        }

        this.biomes = palettedcontainer;
    }
}
