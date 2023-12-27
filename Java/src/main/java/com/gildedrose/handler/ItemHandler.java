package com.gildedrose.handler;

import com.gildedrose.Item;
import com.gildedrose.PropertiesUtil;

public abstract class ItemHandler {

    protected final int maxQuality;
    protected final int minQuality;
    protected final int defaultQualityModifier;

    public ItemHandler() {
        this.maxQuality = PropertiesUtil.getIntProperty("maxQuality", 50);
        this.minQuality = PropertiesUtil.getIntProperty("minQuality", 0);
        this.defaultQualityModifier = PropertiesUtil.getIntProperty("defaultQualityModifier", 1);
    }

    public abstract void handleItem(Item item);

    protected boolean isConjuredItem(Item item) {
        return item.name.toLowerCase().contains("conjured");
    }
}
