import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SellByInfo {
    public SellByInfo(DateRange[] dateRanges, int maxQuality, int minQuality) {
        this.dateRanges = dateRanges;
        this.maxQuality = maxQuality;
        this.minQuality = minQuality;
    }

    public SellByInfo(DateRange dateRange, int maxQuality, int minQuality) {
        //DateRange[] dateRanges = {dateRange};
        //there has to be a better way to do this
        this(Collections.singletonList(dateRange).toArray(DateRange[]::new), maxQuality, minQuality);
    }

    public DateRange[] getDateRanges() {
        return dateRanges;
    }

    public void setDateRanges(DateRange[] dateRanges) {
        this.dateRanges = dateRanges;
    }

    public int getMaxQuality() {
        return maxQuality;
    }

    public void setMaxQuality(int maxQuality) {
        this.maxQuality = maxQuality;
    }

    public int getMinQuality() {
        return minQuality;
    }

    public void setMinQuality(int minQuality) {
        this.minQuality = minQuality;
    }

    private DateRange[] dateRanges;
    private int maxQuality;
    private int minQuality;

    public long getQualityChange(int date) {
        if (dateRanges == null) {
            return  0;
        }
        for (DateRange dateRange : dateRanges) {
            if (dateRange.getStartDay() <= date && dateRange.getEndDay() >= date) {
                return dateRange.getQualityChange();
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        String string = "";
        for (DateRange dateRange : dateRanges) {
            if (dateRange == null) {
                string += "null\n";
            } else {
                string += dateRange.toString() + "\n";
            }
        }
        string += "Max quality: " + maxQuality + ",  Min quality: " + minQuality;
        return  string;
    }
}
