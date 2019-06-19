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
            if (dateRange.getStartDay() >= date && dateRange.getEndDay() <= date) {
                return dateRange.getQualityChange();
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (DateRange dateRange : dateRanges) {
            if (dateRange == null) {
                string.append("null\n");
            } else {
                string.append(dateRange.toString()).append("\n");
            }
        }
        string.append("Max quality: ").append(maxQuality).append(",  Min quality: ").append(minQuality);
        return string.toString();
    }

    @Override
    public boolean equals (Object o) {
        if (o == null || !o.getClass().equals(this.getClass())) {
            return false;
        }
        return Arrays.deepEquals(((SellByInfo)o).getDateRanges(), dateRanges) && ((SellByInfo)o).getMaxQuality() == maxQuality && ((SellByInfo)o).getMinQuality() == minQuality;
    }
}
