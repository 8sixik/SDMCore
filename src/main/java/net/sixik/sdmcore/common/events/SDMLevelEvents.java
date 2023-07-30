package net.sixik.sdmcore.common.events;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.eventbus.api.Event;

import java.util.Map;

public class SDMLevelEvents {

//    public static class StartRaidEvent extends Event {
//
//        ServerPlayer player;
//        Raid raid;
//        DimensionType dimensionType;
//        BlockPos blockpos;
//        Level level;
//        Map<Integer, Raid> raidMap;
//        public StartRaidEvent(ServerPlayer player, Raid raid, DimensionType dimensionType, BlockPos blockpos, Level level, Map<Integer, Raid> raidMap){
//            this.player = player;
//            this.raid = raid;
//            this.dimensionType = dimensionType;
//            this.blockpos = blockpos;
//            this.level = level;
//            this.raidMap =raidMap;
//
//        }
//
//        public Raid getRaid() {
//            return raid;
//        }
//
//        public BlockPos getBlockpos() {
//            return blockpos;
//        }
//
//        public DimensionType getDimensionType() {
//            return dimensionType;
//        }
//
//        public ServerPlayer getPlayer() {
//            return player;
//        }
//
//        public Level getWorld() {
//            return level;
//        }
//
//        public Map<Integer, Raid> getRaidMap() {
//            return raidMap;
//        }
//    }

    public static class TickRaidEvent extends Event {
        Raid raid;
        Level level;
        Map<Integer, Raid> raidMap;
        public TickRaidEvent(Raid raid, Level level, Map<Integer, Raid> raidMap){
            this.raid = raid;
            this.level = level;
            this.raidMap =raidMap;
        }

        @Override
        public boolean isCancelable() {
            return false;
        }

        public Raid getRaid() {
            return raid;
        }

        public Level getWorld() {
            return level;
        }

        public Map<Integer, Raid> getRaidMap() {
            return raidMap;
        }
    }

    public static class StopRaidEvent extends Event{

        Raid raid;
        Level level;
        Map<Integer, Raid> raidMap;
        public StopRaidEvent(Raid raid, Level level, Map<Integer, Raid> raidMap){
            this.raid = raid;
            this.level = level;
            this.raidMap = raidMap;
        }

        @Override
        public boolean isCancelable() {
            return false;
        }

        public Raid getRaid() {
            return raid;
        }

        public Level getWorld() {
            return level;
        }

        public Map<Integer, Raid> getRaidMap() {
            return raidMap;
        }
    }
}
