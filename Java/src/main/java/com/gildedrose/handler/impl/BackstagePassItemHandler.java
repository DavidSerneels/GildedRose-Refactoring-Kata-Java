package com.gildedrose.handler.impl;

import com.gildedrose.Item;
import com.gildedrose.handler.ItemHandler;

public class BackstagePassItemHandler extends ItemHandler {

    public void handleItem(Item item) {
        if (item.sellIn > 10) {
            item.quality = Math.min(item.quality + defaultQualityModifier, maxQuality);
        } else if (item.sellIn > 5) {
            item.quality = Math.min(item.quality + defaultQualityModifier + 1, maxQuality);
        } else if (item.sellIn >= 0) {
            item.quality = Math.min(item.quality + defaultQualityModifier + 2, maxQuality);
        }
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            item.quality = minQuality;
        }
    }
}
