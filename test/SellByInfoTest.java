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
}