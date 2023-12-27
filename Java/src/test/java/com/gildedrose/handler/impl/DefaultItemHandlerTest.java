package com.gildedrose.handler.impl;

import com.gildedrose.Item;
import com.gildedrose.handler.ItemHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultItemHandlerTest {

    ItemHandler itemHandler;

    @BeforeEach
    void setup() {
        this.itemHandler = new DefaultItemHandler();
    }

    @Test
    void regularItemDegradesNormally() {
        Item item = new Item("Regular item", 5, 10);
        this.itemHandler.handleItem(item);
        assertEquals(4, item.sellIn);
        assertEquals(9, item.quality);
    }

    @Test
    void regularItemDegradesTwiceAsFastAfterSellIn() {
        Item item = new Item("Regular item", 2, 10);
        this.itemHandler.handleItem(item);
        this.itemHandler.handleItem(item);
        this.itemHandler.handleItem(item);
        assertEquals(-1, item.sellIn);
        assertEquals(6, item.quality);
    }

    @Test
    void regularItemQualityCannotDropBelowMinimum() {
        Item item = new Item("Regular item", 5, 1);
        this.itemHandler.handleItem(item);
        assertEquals(4, item.sellIn);
        assertEquals(0, item.quality);
        this.itemHandler.handleItem(item);
        assertEquals(3, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void conjuredItemDegradesTwiceAsFastNormally() {
        Item item = new Item("Conjured item", 2, 10);
        this.itemHandler.handleItem(item);
        assertEquals(1, item.sellIn);
        assertEquals(8, item.quality);
    }

    @Test
    void conjuredItemDegradesTwiceAsFastAfterSellIn() {
        Item item = new Item("Conjured item", 2, 10);
        this.itemHandler.handleItem(item);
        this.itemHandler.handleItem(item);
        this.itemHandler.handleItem(item);
        assertEquals(-1, item.sellIn);
        assertEquals(2, item.quality);
    }
}
