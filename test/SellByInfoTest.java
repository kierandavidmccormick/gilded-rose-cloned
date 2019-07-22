import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SellByInfoTest {

    @Test
    void getQualityChangeTest() {
        SellByInfo sellByInfo = new SellByInfo(
                new DateRange[]{
                        new DateRange(5,0,-1),
                        new DateRange(-1,-5, -2)
                },
                50,0
        );
        assertEquals(sellByInfo.getQualityChange(4), -1);
        assertEquals(sellByInfo.getQualityChange(-1), -2);
        assertEquals(sellByInfo.getQualityChange(Integer.MAX_VALUE), 0);
    }

    @Test
    void validateDateRangesTest() {
        DateRange dateRange1 = new DateRange(5,0, 1);
        DateRange dateRange2 = new DateRange(0,-2,-1);
        DateRange dateRange3 = new DateRange(-1, -5, Integer.MIN_VALUE);
        //TODO: add 4th test
        DateRange[] dateRanges1 = {dateRange1, dateRange2};
        DateRange[] dateRanges2 = {dateRange1, dateRange3};
        DateRange[] dateRanges3 = {dateRange2, dateRange3};
        assertEquals(false, SellByInfo.validateDateRanges(dateRanges1));
        assertEquals(true, SellByInfo.validateDateRanges(dateRanges2));
        assertEquals(false, SellByInfo.validateDateRanges(dateRanges3));
    }
}