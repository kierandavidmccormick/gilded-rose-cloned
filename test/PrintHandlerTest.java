import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class PrintHandlerTest {


    @Test
    public void getHeaderTest() {
        //TODO: update this test
        PrintHandler print = new PrintHandler(true, 20);
        assertEquals("*    Gilded Rose   *\n--------------------\nItem ID:      NAME:                                       Quality:    Sell In:    \n\n", print.getHeader().toString());
        print.setScreenWidth(3);
        assertEquals("* Gilded Rose *\n---\nItem ID:      NAME:                                       Quality:    Sell In:    \n\n", print.getHeader().toString());
        print.setShowItemIds(false);
        assertEquals("* Gilded Rose *\n---\nNAME:                                       Quality:    Sell In:    \n\n", print.getHeader().toString());
        print.setScreenWidth(20);
        assertEquals("*    Gilded Rose   *\n--------------------\nNAME:                                       Quality:    Sell In:    \n\n", print.getHeader().toString());
        print.setScreenWidth(21);
        assertEquals("*    Gilded Rose    *\n---------------------\nNAME:                                       Quality:    Sell In:    \n\n", print.getHeader().toString());
    }

    @Test
    public void genCharsTest() {
        PrintHandler print = new PrintHandler(false, 120);
        assertEquals("aaaaaaaaaa", print.genChars('a', 10).toString());
        assertEquals("", print.genChars('g', 0).toString());
    }

    @Test
    public void setStringWidthTest() {
        PrintHandler print = new PrintHandler(false, 120);
        assertEquals("123456789...", print.setStringWidth("123456789 123456789 123456789 ", 12).toString());
        assertEquals("123       ", print.setStringWidth("123", 10).toString());
        assertEquals("123456", print.setStringWidth("123456", 6).toString());
        assertEquals("12", print.setStringWidth("123456", 2).toString());
        assertEquals("12", print.setStringWidth("12", 2).toString());
        assertEquals("", print.setStringWidth("123456", 0).toString());
        assertEquals("      ", print.setStringWidth("", 6).toString());
        assertEquals("", print.setStringWidth("", 0).toString());
    }

    @Test
    public void formatItemDataTest() {
        PrintHandler printHandler = new PrintHandler(true, 120);
        ItemData item = ItemLibrary.getNamedItemData("Sulfuras, Hand of Ragnaros", 10, 80);
        assertEquals("0             Sulfuras, Hand of Ragnaros                  80          21474...    \n", printHandler.formatItemData(item).toString());
        printHandler.setShowItemIds(false);
        assertEquals("Sulfuras, Hand of Ragnaros                  80          21474...    \n", printHandler.formatItemData(item).toString());
    }

    @Test
    public void getBodyTest() {
        PrintHandler printHandler = new PrintHandler(true, 120);
        ArrayList<ItemData> items = new ArrayList<ItemData>(Arrays.asList(ItemLibrary.getNamedItemData("Sulfuras, Hand of Ragnaros", 10, 80),
                                                                            ItemLibrary.getNamedItemData("Aged Brie", 20, 2),
                                                                            ItemLibrary.getNamedItemData("Conjured Backstage Passes", 4, 10)));
        assertEquals("0             Sulfuras, Hand of Ragnaros                  80          21474...    \n" +
                                "0             Aged Brie                                   2           20          \n" +
                                "0             Conjured Backstage Passes                   10          4           \n" +
                                "\n",
                                printHandler.getBody(items).toString());
    }

}
