package net.sixik.sdmcore.common.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class SDMPlayerEvents {

    public static class PlayerEnchantEvent extends PlayerEvent {

        EnchantmentInstance enchantmentData;
        Enchantment enchantment;
        ItemStack stack;
        Level world;
        BlockPos pos;

        public PlayerEnchantEvent(Player player, ItemStack itemstack, BlockPos pos, EnchantmentInstance enchantmentinstance, net.minecraft.world.level.Level level) {
            super(player);
            this.stack = itemstack;
            this.enchantmentData = enchantmentinstance;
            this.enchantment = enchantmentinstance.enchantment;
            this.world = level;
            this.pos = pos;
        }

        @Override
        public boolean isCancelable() {return false;}
        public Enchantment getEnchantment() {return enchantment;}
        public ItemStack getItem() {return stack;}

        public net.minecraft.world.level.Level getWorld() {
            return world;
        }

        public BlockPos getPos() {
            return pos;
        }
    }

    public static class PlayerEatFoodEvent extends PlayerEvent {
        ItemStack food;
        public PlayerEatFoodEvent(Player player, ItemStack foodItem) {
            super(player);
            this.food = foodItem;
        }

        @Override
        public boolean isCancelable() {
            return false;
        }

        public ItemStack getFood() {
            return food;
        }

        public boolean isFastFood(){
            return food.getItem().getFoodProperties().isFastFood();
        }
        public boolean canAlwaysEat(){
            return food.getItem().getFoodProperties().canAlwaysEat();
        }
        public boolean isMeat(){
            return food.getItem().getFoodProperties().isMeat();
        }
        public int getNutrition(){
            return food.getItem().getFoodProperties().getNutrition();
        }
        public float getSaturationModifier(){
            return food.getItem().getFoodProperties().getSaturationModifier();
        }
    }

    public static class PlayerPotionEvent extends PlayerEvent {
        private ItemStack stack;
        public PlayerPotionEvent(Player player, ItemStack stack) {
            super(player);
            this.stack = stack;
        }

        public void setStack(ItemStack stack) {
            this.stack = stack;
        }

        public ItemStack getStack() {
            return stack;
        }

    }

    public static class PlayerChestOpenEvent extends PlayerEvent {

        BlockEntity block;
        public PlayerChestOpenEvent(Player player, BlockEntity block) {
            super(player);
            this.block = block;
        }

        public BlockEntity getBlock() {
            return block;
        }

        @Override
        public Player getEntity() {
            return super.getEntity();
        }

        public int getOpenCount(){
            if(block instanceof ChestBlockEntity chest){
                ChestBlockEntity.getOpenCount(chest.getLevel(),chest.getBlockPos());
            }
            return -404;
        }
    }

    public static class PlayerChestCloseEvent  extends PlayerEvent {

        public static BlockEntity block;
        public PlayerChestCloseEvent(Player player, BlockEntity block) {
            super(player);
        }

        public BlockEntity getBlock() {
            return block;
        }

        @Override
        public Player getEntity() {
            return super.getEntity();
        }

        public int getOpenCount(){
            if(block instanceof ChestBlockEntity chest){
                ChestBlockEntity.getOpenCount(chest.getLevel(),chest.getBlockPos());
            }
            return -404;
        }
    }

    public static class PlayerChestLootEvent extends PlayerEvent {
        public LootContext ctx;
        public Container inventory;
        public BlockPos pos;

        public PlayerChestLootEvent(Player player, LootContext ctx, Container inventory, BlockPos pos) {
            super(player);
            this.ctx = ctx;
            this.inventory = inventory;
            this.pos = pos;
        }

        public BlockPos getPos() {
            return pos;
        }

        public Container getInventory() {
            return inventory;
        }

        public LootContext getCtx() {
            return ctx;
        }
    }

    public static class PlayerBeforeLoadedChunkEvent extends PlayerEvent {
        private LevelChunk _chunkLevel;

        public PlayerBeforeLoadedChunkEvent(Player player, LevelChunk _chunkLevel) {
            super(player);
            this._chunkLevel = _chunkLevel;
        }

        public LevelChunk getLevelChunk() {
            return _chunkLevel;
        }
    }

    public static class PlayerAfterLoadedChunkEvent extends PlayerEvent{
        private LevelChunk _chunkLevel;
        public PlayerAfterLoadedChunkEvent(Player player, LevelChunk _chunkLevel) {
            super(player);
            this._chunkLevel = _chunkLevel;
        }
        public LevelChunk getLevelChunk() {
            return _chunkLevel;
        }
    }
}
