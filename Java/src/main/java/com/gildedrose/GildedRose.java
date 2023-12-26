package com.gildedrose;

import java.util.*;

class GildedRose {

    private final int maxQuality;
    private final int minQuality;
    private final int defaultQualityModifier;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
        this.maxQuality = PropertiesUtil.getIntProperty("maxQuality", 50);
        this.minQuality = PropertiesUtil.getIntProperty("minQuality", 0);
        this.defaultQualityModifier = PropertiesUtil.getIntProperty("defaultQualityModifier", 1);
    }

    /*public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }*/

    public void updateQuality() {
        Arrays.stream(items).forEach(item -> {
            switch (item.name) {
                case "Aged Brie": handleAgedBrie(item); break;
                case "Sulfuras, Hand of Ragnaros": handleSulfuras(item); break;
                case "Backstage passes to a TAFKAL80ETC concert": handleBackstagePass(item); break;
                default: handleDefaultItem(item); break;
            }
        });
    }

    private void handleDefaultItem(Item item) {
        boolean isConjured = item.name.toLowerCase().contains("conjured");
        int qualityModifier = isConjured ? defaultQualityModifier * 2 : defaultQualityModifier;
        item.quality = Math.max(item.quality - qualityModifier, minQuality);
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            item.quality = Math.max(item.quality - qualityModifier, minQuality);
        }
    }

    private void handleAgedBrie(Item item) {
        item.quality = Math.min(item.quality + defaultQualityModifier, maxQuality);
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            item.quality = Math.min(item.quality + defaultQualityModifier, maxQuality);
        }
    }

    private void handleSulfuras(Item item) {
        item.quality = 80;
    }

    private void handleBackstagePass(Item item) {
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