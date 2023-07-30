package net.sixik.sdmcore.common.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

public class SDMLivingEvents {
    public static class LivingItemPickUpEvent extends LivingEvent {
        ItemStack itemStack;
        ItemEntity itemEntity;
        public LivingItemPickUpEvent(LivingEntity entity, ItemEntity itemStack) {
            super(entity);
            this.itemEntity = itemStack;
            this.itemStack = itemStack.getItem();
        }

        public ItemStack getItemStack() {
            return itemStack;
        }

        public ItemEntity getItemEntity() {
            return itemEntity;
        }
    }

    public static class LivingEntityTickEvent extends LivingEvent {

        public LivingEntityTickEvent(LivingEntity entity) {
            super(entity);
        }

    }

    public static class LivingEntityXpDropEvent extends LivingEvent {
        int reward;
        public LivingEntityXpDropEvent(LivingEntity entity, int reward) {
            super(entity);
            this.reward = reward;
        }

        public int getReward() {
            return reward;
        }
    }
}
