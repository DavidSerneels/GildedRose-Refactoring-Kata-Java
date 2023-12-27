package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void itemsDegrade1Day() {
        Item[] items = new Item[] {
            new Item("Regular item", 5, 10),
            new Item("Aged Brie", 5, 10),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 10),
            new Item("Conjured item", 2, 10)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(4, app.items[0].sellIn);
        assertEquals(9, app.items[0].quality);
        assertEquals(4, app.items[1].sellIn);
        assertEquals(11, app.items[1].quality);
        assertEquals(80, app.items[2].quality);
        assertEquals(14, app.items[3].sellIn);
        assertEquals(11, app.items[3].quality);
        assertEquals(1, app.items[4].sellIn);
        assertEquals(8, app.items[4].quality);
    }

    @Test
    void itemsDegrade3Days() {
        Item[] items = new Item[] {
            new Item("Regular item", 2, 10),
            new Item("Aged Brie", 2, 10),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10),
            new Item("Conjured item", 2, 10)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertEquals(-1, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
        assertEquals(-1, app.items[1].sellIn);
        assertEquals(14, app.items[1].quality);
        assertEquals(80, app.items[2].quality);
        assertEquals(8, app.items[3].sellIn);
        assertEquals(15, app.items[3].quality);
        assertEquals(-1, app.items[4].sellIn);
        assertEquals(2, app.items[4].quality);
    }

    @Test
    void itemQualityCannotPassThresholds() {
        Item[] items = new Item[] {
            new Item("Regular item", 5, 1),
            new Item("Aged Brie", 5, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 10),
            new Item("Conjured item", 3, 1),
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
        assertEquals(4, app.items[1].sellIn);
        assertEquals(50, app.items[1].quality);
        assertEquals(0, app.items[2].sellIn);
        assertEquals(13, app.items[2].quality);
        assertEquals(2, app.items[3].sellIn);
        assertEquals(0, app.items[3].quality);
        app.updateQuality();
        assertEquals(3, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
        assertEquals(3, app.items[1].sellIn);
        assertEquals(50, app.items[1].quality);
        assertEquals(-1, app.items[2].sellIn);
        assertEquals(0, app.items[2].quality);
        assertEquals(1, app.items[3].sellIn);
        assertEquals(0, app.items[3].quality);
        app.updateQuality();
        assertEquals(2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
        assertEquals(2, app.items[1].sellIn);
        assertEquals(50, app.items[1].quality);
        assertEquals(-2, app.items[2].sellIn);
        assertEquals(0, app.items[2].quality);
        assertEquals(0, app.items[3].sellIn);
        assertEquals(0, app.items[3].quality);
    }
}
