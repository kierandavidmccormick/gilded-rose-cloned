import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class GildedRose {
    /*
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }
     */
    private ArrayList<ItemData> items;
    private int itemId = 0;
    private PrintHandler printHandler;

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

    public void removeItem(ItemData item) {
        items.remove(item);
    }

    public void removeItems(Collection<ItemData> items) {
        this.items.removeAll(items);
    }

    public ArrayList<ItemData> getItems() {
        return items;
    }

    public  GildedRose(Collection<ItemData> items) {
        this.items = new ArrayList<>(items);
        printHandler = new PrintHandler(true, 120);

    }

    public GildedRose() {
        items = new ArrayList<>();
        printHandler = new PrintHandler(true, 120);
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
        //printItems();
    }

    public void printItems() {
        Collections.sort(items);
        System.out.print(printHandler.getPrintString(items));
    }

    public void initItems() {
        addItem(ItemLibrary.getNamedItemData("Sulfuras, Hand of Ragnaros", 10, 50));
        addItem(ItemLibrary.getNamedItemData("Conjured Aged Brie", 3, 10));
        addItem(ItemLibrary.getNamedItemData("Backstage Passes to a boring concert", 5, 10));
    }

    //TODO: test
    public ItemData getItemByID(int id) {
        return items.stream().filter(o -> o.getItemId() == id).findFirst().orElse(null);
    }
}
