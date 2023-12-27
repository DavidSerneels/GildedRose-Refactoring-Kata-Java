package com.gildedrose.handler.impl;

import com.gildedrose.Item;
import com.gildedrose.handler.ItemHandler;

public class DefaultItemHandler extends ItemHandler {

    public void handleItem(Item item) {
        int actualQualityModifier = isConjuredItem(item) ? defaultQualityModifier * 2 : defaultQualityModifier;
        item.quality = Math.max(item.quality - actualQualityModifier, minQuality);
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            item.quality = Math.max(item.quality - actualQualityModifier, minQuality);
        }
    }
}
