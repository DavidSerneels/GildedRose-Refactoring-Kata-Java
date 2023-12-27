package com.gildedrose.handler.impl;

import com.gildedrose.Item;
import com.gildedrose.handler.ItemHandler;

public class AgedBrieItemHandler extends ItemHandler {

    public void handleItem(Item item) {
        item.quality = Math.min(item.quality + defaultQualityModifier, maxQuality);
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            item.quality = Math.min(item.quality + defaultQualityModifier, maxQuality);
        }
    }
}
