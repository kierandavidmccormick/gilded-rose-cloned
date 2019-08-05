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

        GildedRose gildedRose = spy(GildedRose.class);
        doNothing().when(gildedRose).printItems();

        gildedRose.addItems(Arrays.asList(sulfuras, agedBrie, conjuredAgedBrie, backstagePasses, conjuredBackstagePasses, cheese, conjuredCheese, potato));

        gildedRose.updateQuality();
        assertEquals(Integer.MAX_VALUE, sulfuras.getItem().sellIn);
        assertEquals(80, sulfuras.getItem().quality);
        assertEquals(4, agedBrie.getItem().sellIn);
        assertEquals(11, agedBrie.getItem().quality);
        assertEquals(4, conjuredAgedBrie.getItem().sellIn);
        assertEquals(11, conjuredAgedBrie.getItem().quality);
        assertEquals(11, backstagePasses.getItem().sellIn);
        assertEquals(10, backstagePasses.getItem().quality);
        assertEquals(11, conjuredBackstagePasses.getItem().sellIn);
        assertEquals(10, conjuredBackstagePasses.getItem().quality);
        assertEquals(4, cheese.getItem().sellIn);
        assertEquals(14, cheese.getItem().quality);
        assertEquals(4, conjuredCheese.getItem().sellIn);
        assertEquals(13, conjuredCheese.getItem().quality);
        assertEquals(9, potato.getItem().sellIn);
        assertEquals(14, potato.getItem().quality);

        gildedRose.updateQuality();
        assertEquals(3, agedBrie.getItem().sellIn);
        assertEquals(10, backstagePasses.getItem().quality);
        assertEquals(10, conjuredBackstagePasses.getItem().quality);

        gildedRose.updateQuality();
        assertEquals(2, agedBrie.getItem().sellIn);
        assertEquals(12, backstagePasses.getItem().quality);
        assertEquals(12, conjuredBackstagePasses.getItem().quality);

        gildedRose.updateQuality();

        gildedRose.updateQuality();
        assertEquals(10, cheese.getItem().quality);
        assertEquals(5, conjuredCheese.getItem().quality);

        gildedRose.updateQuality();
        assertEquals(8, cheese.getItem().quality);
        assertEquals(1, conjuredCheese.getItem().quality);
        assertEquals(4, potato.getItem().sellIn);
        assertEquals(14, potato.getItem().quality);

        gildedRose.updateQuality();
        assertEquals(5, backstagePasses.getItem().sellIn);
        assertEquals(20, backstagePasses.getItem().quality);
        assertEquals(20, conjuredBackstagePasses.getItem().quality);
        assertEquals(0, conjuredCheese.getItem().quality);
        assertEquals(12, potato.getItem().quality);

        gildedRose.updateQuality();
        assertEquals(23, backstagePasses.getItem().quality);
        assertEquals(23, conjuredBackstagePasses.getItem().quality);

        gildedRose.updateQuality();

        gildedRose.updateQuality();

        gildedRose.updateQuality();

        gildedRose.updateQuality();
        assertEquals(0, backstagePasses.getItem().sellIn);
        assertEquals(35, backstagePasses.getItem().quality);
        assertEquals(35, conjuredBackstagePasses.getItem().quality);

        gildedRose.updateQuality();

        assertEquals(0, backstagePasses.getItem().quality);
        assertEquals(0, conjuredBackstagePasses.getItem().quality);
    }

}
