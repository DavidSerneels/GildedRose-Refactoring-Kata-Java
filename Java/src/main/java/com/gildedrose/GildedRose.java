package com.gildedrose;

import com.gildedrose.handler.ItemHandler;
import com.gildedrose.handler.impl.AgedBrieItemHandler;
import com.gildedrose.handler.impl.BackstagePassItemHandler;
import com.gildedrose.handler.impl.DefaultItemHandler;
import com.gildedrose.handler.impl.SulfurasItemHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class GildedRose {

    Item[] items;
    final Map<String, ItemHandler> itemHandlers;
    final ItemHandler defaultItemHandler;

    public GildedRose(Item[] items) {
        this.items = items;
        this.itemHandlers = new HashMap<>();
        this.itemHandlers.put(PropertiesUtil.getStringProperty("agedBrie.name", "Aged Brie"), new AgedBrieItemHandler());
        this.itemHandlers.put(PropertiesUtil.getStringProperty("sulfuras.name", "Sulfuras, Hand of Ragnaros"), new SulfurasItemHandler());
        this.itemHandlers.put(PropertiesUtil.getStringProperty("backstagePass.name", "Backstage passes to a TAFKAL80ETC concert"), new BackstagePassItemHandler());
        this.defaultItemHandler = new DefaultItemHandler();
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(item -> {
            ItemHandler itemHandler = itemHandlers.getOrDefault(item.name, defaultItemHandler);
            itemHandler.handleItem(item);
        });
    }
}
