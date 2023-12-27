package com.gildedrose.handler.impl;

import com.gildedrose.Item;
import com.gildedrose.handler.ItemHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SulfurasItemHandlerTest {

    ItemHandler itemHandler;

    @BeforeEach
    void setup() {
        this.itemHandler = new SulfurasItemHandler();
    }

    @Test
    void sulfurasDoesntChangeInQuality() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        this.itemHandler.handleItem(item);
        assertEquals(80, item.quality);
    }
}
