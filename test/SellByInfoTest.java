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
        DateRange dateRange4 = new DateRange(-3, -1, 5);

        SellByInfo sellByInfo1 = new SellByInfo(new DateRange[]{dateRange1, dateRange2}, 20, 10);
        SellByInfo sellByInfo2 = new SellByInfo(new DateRange[]{dateRange1, dateRange3}, 20, 10);
        SellByInfo sellByInfo3 = new SellByInfo(new DateRange[]{dateRange2, dateRange3}, 20, 10);
        SellByInfo sellByInfo4 = new SellByInfo(new DateRange[]{dateRange1, dateRange3}, 10, 20);
        SellByInfo sellByInfo5 = new SellByInfo(new DateRange[]{dateRange1, dateRange4}, 50, 0);
        SellByInfo sellByInfo6 = new SellByInfo(new DateRange[]{dateRange1, dateRange3}, 0, 50);


        assertEquals(false, sellByInfo1.validate());
        assertEquals(true, sellByInfo2.validate());
        assertEquals(false, sellByInfo3.validate());
        assertEquals(false, sellByInfo4.validate());
        assertEquals(false, sellByInfo5.validate());
        assertEquals(false, sellByInfo6.validate());
    }
}