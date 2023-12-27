package com.gildedrose.handler.impl;

import com.gildedrose.Item;
import com.gildedrose.handler.ItemHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgedBrieItemHandlerTest {

    ItemHandler itemHandler;

    @BeforeEach
    void setup() {
        this.itemHandler = new AgedBrieItemHandler();
    }

    @Test
    void agedBrieIncreasesInQualityNormally() {
        Item item = new Item("Aged Brie", 5, 10);
        this.itemHandler.handleItem(item);
        assertEquals(4, item.sellIn);
        assertEquals(11, item.quality);
    }

    @Test
    void agedBrieIncreasesInQualityTwiceAsFastAfterSellIn() {
        Item item = new Item("Aged Brie", 2, 10);
        this.itemHandler.handleItem(item);
        this.itemHandler.handleItem(item);
        this.itemHandler.handleItem(item);
        assertEquals(-1, item.sellIn);
        assertEquals(14, item.quality);
    }

    @Test
    void agedBrieQualityCannotRaiseAboveMaximum() {
        Item item = new Item("Aged Brie", 5, 49);
        this.itemHandler.handleItem(item);
        assertEquals(4, item.sellIn);
        assertEquals(50, item.quality);
        this.itemHandler.handleItem(item);
        assertEquals(3, item.sellIn);
        assertEquals(50, item.quality);
    }
}
