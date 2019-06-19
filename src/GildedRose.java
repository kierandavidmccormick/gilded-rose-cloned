import java.util.ArrayList;
import java.util.Collection;

class GildedRose {
    /*
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }
     */
    private ArrayList<ItemData> items;
    private int itemId = 0;

    public void addItem(ItemData item) {
        items.add(item);
        item.setItemId(itemId);
        itemId++;
    }

    public void addItems(Iterable<ItemData> items) {
        for (ItemData item : items) {
            addItem(item);
        }
    }

    public  GildedRose(Collection<ItemData> items) {
        this.items = new ArrayList<>(items);
    }

    public GildedRose() {
        items = new ArrayList<>();
    }

    public void updateQuality() {
        /*
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
         */

        for (ItemData item : items) {
            item.updateItem();
        }
    }
}
