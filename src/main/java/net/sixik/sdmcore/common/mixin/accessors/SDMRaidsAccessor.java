package net.sixik.sdmcore.common.mixin.accessors;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(Raids.class)
public interface SDMRaidsAccessor {

    @Accessor
    Map<Integer, Raid> getRaidMap();
    @Accessor
    int getNextAvailableID();

    @Accessor
    int getTick();

}
