import java.util.Arrays;
import java.util.Collections;

public class SellByInfo {
    public SellByInfo(DateRange[] dateRanges, int maxQuality, int minQuality) {
        this.dateRanges = dateRanges;
        this.maxQuality = maxQuality;
        this.minQuality = minQuality;
    }

    public SellByInfo(DateRange dateRange, int maxQuality, int minQuality) {
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

    public static boolean validateDateRanges(DateRange[] dateRanges) {
        if (dateRanges == null || dateRanges.length == 0) {
            return false;
        }
        if (dateRanges[dateRanges.length - 1].getEndDay() > dateRanges[dateRanges.length - 1].getStartDay()) {
            return false;
        }
        for (int i = 0; i < dateRanges.length - 1; i++) {
            if (dateRanges[i].getEndDay() > dateRanges[i].getStartDay()) {
                return false;
            }
            for (int j = i + 1; j < dateRanges.length; j++) {
                //TODO: can these be simplified
                //TODO: this might need more test coverage
                if (dateRanges[i].getStartDay() == dateRanges[j].getStartDay() || dateRanges[i].getStartDay() == dateRanges[j].getEndDay() || dateRanges[i].getEndDay() == dateRanges[j].getStartDay() || dateRanges[i].getEndDay() == dateRanges[j].getEndDay()) {
                    return false;
                }
                if ((dateRanges[i].getEndDay() > dateRanges[j].getEndDay() && dateRanges[i].getEndDay() < dateRanges[j].getStartDay()) || (dateRanges[i].getStartDay() > dateRanges[j].getEndDay() && dateRanges[i].getStartDay() < dateRanges[j].getStartDay())) {
                    return false;
                }
            }
        }
        return true;
    }
}
