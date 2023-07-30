package net.sixik.sdmcore.common.mixin.unchange;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raids;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.sixik.sdmcore.common.events.SDMLevelEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Mixin(Raids.class)
public class SDMRaidMixin {
    @Shadow @Final private Map<Integer, Raid> raidMap;

    @Shadow @Final private ServerLevel level;

//    @Inject(method = "createOrExtendRaid(Lnet/minecraft/server/level/ServerPlayer;)Lnet/minecraft/world/entity/raid/Raid;",
//            at = @At(value = "INVOKE",
//                    target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"),
//            locals = LocalCapture.CAPTURE_FAILHARD)
//    public void ctus$$onStartRaid(ServerPlayer player, CallbackInfoReturnable<Raid> cir, DimensionType dimensiontype, BlockPos blockpos, List list, int i, Vec3 vec3, BlockPos blockpos1, Raid raid, boolean flag){
//        MinecraftForge.EVENT_BUS.post(new SDMLevelEvents.StartRaidEvent(player, raid, dimensiontype, blockpos, level, raidMap));
//    }
    @Inject(method = "Lnet/minecraft/world/entity/raid/Raids;tick()V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/raid/Raid;tick()V"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void ctus$$onTickRaid(CallbackInfo ci, Iterator iterator, Raid raid) {
        MinecraftForge.EVENT_BUS.post(new SDMLevelEvents.TickRaidEvent(raid, level, raidMap));

    }
    @Inject(method = "Lnet/minecraft/world/entity/raid/Raids;tick()V",
            at = @At(value = "INVOKE",
                    target = "Ljava/util/Iterator;remove()V"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void ctus$$onStopRaid(CallbackInfo ci, Iterator iterator, Raid raid){
        MinecraftForge.EVENT_BUS.post(new SDMLevelEvents.StopRaidEvent(raid, level, raidMap));
    }
}
