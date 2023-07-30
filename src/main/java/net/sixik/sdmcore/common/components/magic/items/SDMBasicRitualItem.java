package net.sixik.sdmcore.common.components.magic.items;

import net.sixik.sdmcore.common.components.magic.data.Essence;

public class SDMBasicRitualItem extends SDMBasicMagicItem {
    private final Essence essence;
    public SDMBasicRitualItem(Properties properties, Essence essence) {
        super(properties);
        this.essence = essence;
    }

    public Essence getEssence() {
        return essence;
    }
}
