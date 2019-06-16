import java.util.ArrayList;

public class SellByInfo {
    public SellByInfo(ArrayList<DateRange> dateRanges, int maxQuality, int minQuality) {
        this.dateRanges = dateRanges;
        this.maxQuality = maxQuality;
        this.minQuality = minQuality;
    }

    public ArrayList<DateRange> getDateRanges() {
        return dateRanges;
    }

    public void setDateRanges(ArrayList<DateRange> dateRanges) {
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

    private ArrayList<DateRange> dateRanges;
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
}
