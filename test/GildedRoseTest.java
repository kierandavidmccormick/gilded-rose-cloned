import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class GildedRoseTest {

    @Test
    public void globalTest() throws Exception {
        ItemData sulfuras = ItemLibrary.getNamedItemData("Sulfuras, Hand of Ragnaros", 10, 80);
        ItemData agedBrie = ItemLibrary.getNamedItemData("Aged Brie", 5, 10);
        ItemData conjuredAgedBrie = ItemLibrary.getNamedItemData("Conjured Aged Brie", 5, 10);
        ItemData backstagePasses = ItemLibrary.getNamedItemData("Backstage Passes to a Concert", 12, 10);
        ItemData conjuredBackstagePasses = ItemLibrary.getNamedItemData("Conjured Backstage Passes to a Concert", 12, 10);
        ItemData cheese = new ItemData(
                new Item("Cheese", 5, 15),
                new SellByInfo(
                        new DateRange[]{
                                new DateRange(Integer.MAX_VALUE, 1, -1),
                                new DateRange(0, Integer.MIN_VALUE, -2)
                        },
                        50, 0
                )
        );
        ItemData conjuredCheese = new ItemData(
                new Item("Conjured Cheese", 5, 15),
                new SellByInfo(
                        new DateRange[]{
                                new DateRange(Integer.MAX_VALUE, 1, -2),
                                new DateRange(0, Integer.MIN_VALUE, -4)
                        },
                        50, 0
                )
        );
        ItemData potato = new ItemData("Eldrath, Lord of Potatoes", 10, 14, 50, 0, 1, 4, 0, -2);

        //GildedRose gildedRose = new GildedRose(Arrays.asList(sulfuras, agedBrie, conjuredAgedBrie, backstagePasses, conjuredBackstagePasses, cheese, conjuredCheese, potato));

        GildedRose gildedRose = spy(GildedRose.class);
        doNothing().when(gildedRose).printItems();

        gildedRose.addItems(Arrays.asList(sulfuras, agedBrie, conjuredAgedBrie, backstagePasses, conjuredBackstagePasses, cheese, conjuredCheese, potato));

        gildedRose.updateQuality();
        assertEquals(sulfuras.getItem().sellIn > 10000, true);
        assertEquals(sulfuras.getItem().quality, 80);
        assertEquals(agedBrie.getItem().sellIn, 4);
        assertEquals(agedBrie.getItem().quality, 11);
        assertEquals(conjuredAgedBrie.getItem().sellIn, 4);
        assertEquals(conjuredAgedBrie.getItem().quality, 11);
        assertEquals(backstagePasses.getItem().sellIn, 11);
        assertEquals(backstagePasses.getItem().quality, 10);
        assertEquals(conjuredBackstagePasses.getItem().sellIn, 11);
        assertEquals(conjuredBackstagePasses.getItem().quality, 10);
        assertEquals(cheese.getItem().sellIn, 4);
        assertEquals(cheese.getItem().quality, 14);
        assertEquals(conjuredCheese.getItem().sellIn, 4);
        assertEquals(conjuredCheese.getItem().quality, 13);
        assertEquals(potato.getItem().sellIn, 9);
        assertEquals(potato.getItem().quality, 14);

        gildedRose.updateQuality();
        assertEquals(agedBrie.getItem().sellIn, 3);
        assertEquals(backstagePasses.getItem().quality, 10);
        assertEquals(conjuredBackstagePasses.getItem().quality, 10);

        gildedRose.updateQuality();
        assertEquals(agedBrie.getItem().sellIn, 2);
        assertEquals(backstagePasses.getItem().quality, 12);
        assertEquals(conjuredBackstagePasses.getItem().quality, 12);

        gildedRose.updateQuality();

        gildedRose.updateQuality();
        assertEquals(cheese.getItem().quality, 10);
        assertEquals(conjuredCheese.getItem().quality, 5);

        gildedRose.updateQuality();
        assertEquals(cheese.getItem().quality, 8);
        assertEquals(conjuredCheese.getItem().quality, 1);
        assertEquals(potato.getItem().sellIn, 4);
        assertEquals(potato.getItem().quality, 14);

        gildedRose.updateQuality();
        assertEquals(backstagePasses.getItem().sellIn, 5);
        assertEquals(backstagePasses.getItem().quality, 20);
        assertEquals(conjuredBackstagePasses.getItem().quality, 20);
        assertEquals(conjuredCheese.getItem().quality, 0);
        assertEquals(potato.getItem().quality, 12);

        gildedRose.updateQuality();
        assertEquals(backstagePasses.getItem().quality, 22);
        assertEquals(conjuredBackstagePasses.getItem().quality, 22);

        gildedRose.updateQuality();

        gildedRose.updateQuality();

        gildedRose.updateQuality();

        gildedRose.updateQuality();
        assertEquals(backstagePasses.getItem().sellIn, 0);
        assertEquals(backstagePasses.getItem().quality, 42);
        assertEquals(conjuredBackstagePasses.getItem().quality, 42);

        gildedRose.updateQuality();

        assertEquals(backstagePasses.getItem().quality, 0);
        assertEquals(conjuredBackstagePasses.getItem().quality, 0);
    }

}
