package com.gildedrose.handler.impl;

import com.gildedrose.Item;
import com.gildedrose.handler.ItemHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackstagePassItemHandlerTest {

    ItemHandler itemHandler;

    @BeforeEach
    void setup() {
        this.itemHandler = new BackstagePassItemHandler();
    }

    @Test
    void backStagePassIncreasesInQualityNormally() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 10);
        itemHandler.handleItem(item);
        assertEquals(14, item.sellIn);
        assertEquals(11, item.quality);
    }

    @Test
    void backStagePassIncreasesInQualityTwiceAsFast() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10);
        itemHandler.handleItem(item);
        assertEquals(9, item.sellIn);
        assertEquals(12, item.quality);
    }

    @Test
    void backStagePassIncreasesInQualityThriceAsFast() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10);
        itemHandler.handleItem(item);
        assertEquals(4, item.sellIn);
        assertEquals(13, item.quality);
    }

    @Test
    void backStagePassIsQuality0AfterSellIn() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10);
        itemHandler.handleItem(item);
        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);
    }

}
