package com.gildedrose.handler.impl;

import com.gildedrose.Item;
import com.gildedrose.handler.ItemHandler;

public class SulfurasItemHandler extends ItemHandler {

    public void handleItem(Item item) {
        // Sulfuras quality is always 80
        item.quality = 80;
    }
}
