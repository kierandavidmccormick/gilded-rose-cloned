import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDataTest {

    @Test
    void validateDateRangesTest() {
        DateRange dateRange1 = new DateRange(5,0, 1);
        DateRange dateRange2 = new DateRange(0,-2,-1);
        DateRange dateRange3 = new DateRange(-1, -5, Integer.MIN_VALUE);
        DateRange[] dateRanges1 = {dateRange1, dateRange2};
        DateRange[] dateRanges2 = {dateRange1, dateRange3};
        DateRange[] dateRanges3 = {dateRange2, dateRange3};
        assertEquals(false, ItemData.validateDateRanges(dateRanges1));
        assertEquals(true, ItemData.validateDateRanges(dateRanges2));
        assertEquals(false, ItemData.validateDateRanges(dateRanges3));
    }

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