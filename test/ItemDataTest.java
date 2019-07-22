import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDataTest {

    @Test
    void arrayConstructorTest() throws Exception {
        ItemData itemData1 = new ItemData(
                new Item("Backstage Passes to a Concert", 10, 10),
                new SellByInfo(new DateRange[] {
                        new DateRange(Integer.MIN_VALUE, 11, 1),
                        new DateRange(10, 6, 2),
                        new DateRange(5, 0, 5),
                        new DateRange(-1, Integer.MIN_VALUE, Integer.MIN_VALUE)
                },
                        50, 0
                )
        );
        //TODO: deal with the longs that are supposed to be in the int... list
        ItemData itemData2 = new ItemData("Backstage Passes to a Concert", 10, 10, 50, 0, 4,
                Integer.MIN_VALUE, 11, 1,
                10, 6, 2,
                5, 0, 5,
                -1, Integer.MIN_VALUE, Integer.MIN_VALUE);
        assertEquals(itemData1, itemData2);
    }
}