package net.sixik.sdmcore.common.mixin.unchange;

import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.MinecraftForge;
import net.sixik.sdmcore.common.events.SDMPlayerEvents;
import org.apache.commons.lang3.mutable.MutableObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkMap.class)
public class SDMChunkMapMixin {

    @Inject(method = "Lnet/minecraft/server/level/ChunkMap;playerLoadedChunk(Lnet/minecraft/server/level/ServerPlayer;Lorg/apache/commons/lang3/mutable/MutableObject;Lnet/minecraft/world/level/chunk/LevelChunk;)V", at = @At("HEAD"))
    public void beforePlayerLoadedChunk(ServerPlayer _player, MutableObject<ClientboundLevelChunkWithLightPacket> p_183762_, LevelChunk p_183763_, CallbackInfo ci){
        try{
            MinecraftForge.EVENT_BUS.post(new SDMPlayerEvents.PlayerBeforeLoadedChunkEvent(_player, p_183763_));
        }
        catch (Exception var5) {
            var5.printStackTrace();
        }
    }

    @Inject(method = "Lnet/minecraft/server/level/ChunkMap;playerLoadedChunk(Lnet/minecraft/server/level/ServerPlayer;Lorg/apache/commons/lang3/mutable/MutableObject;Lnet/minecraft/world/level/chunk/LevelChunk;)V", at = @At("TAIL"))
    public void afterPlayerLoadedChunk(ServerPlayer _player, MutableObject<ClientboundLevelChunkWithLightPacket> p_183762_, LevelChunk p_183763_, CallbackInfo ci){
        try{
            MinecraftForge.EVENT_BUS.post(new SDMPlayerEvents.PlayerAfterLoadedChunkEvent(_player, p_183763_));
        }
        catch (Exception var5) {
            var5.printStackTrace();
        }
    }
}
