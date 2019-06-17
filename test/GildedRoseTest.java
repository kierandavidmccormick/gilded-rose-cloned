import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GildedRoseTest {

    @Test
    public void test() {
        /*
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
        */
        ItemData[] itemData = {
                ItemLibrary.getNamedItemData("Sulfuras, Hand of Ragnaros", 10, 80),
                ItemLibrary.getNamedItemData("Aged Brie", 5, 10),
                ItemLibrary.getNamedItemData("Conjured Aged Brie", 5, 10),
                ItemLibrary.getNamedItemData("Backstage Passes to a Concert", 12, 10),
                ItemLibrary.getNamedItemData("Conjured Backstage Passes to a Concert", 12, 10),
                new ItemData(
                        new Item("Cheese", 5, 15),
                        new SellByInfo(
                                new DateRange[] {
                                        new DateRange(Integer.MAX_VALUE, 0, -1),
                                        new DateRange(-1, Integer.MIN_VALUE, -2)
                                },
                                0, 50
                        )
                ),
                new ItemData(
                        new Item("Conjured Cheese", 5, 15),
                        new SellByInfo(
                                new DateRange[] {
                                        new DateRange(Integer.MAX_VALUE, 0, -2),
                                        new DateRange(-1, Integer.MIN_VALUE, -4)
                                },
                                0, 50
                        )
                )
        };
        //TODO: the rest of the test
    }

}
