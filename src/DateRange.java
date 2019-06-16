public class DateRange {
    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public long getQualityChange() {
        return qualityChange;
    }

    public void setQualityChange(int qualityChange) {
        this.qualityChange = qualityChange;
    }

    public DateRange(int startDay, int endDay, int qualityChange) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.qualityChange = qualityChange;
    }

    private int startDay;
    private int endDay;
    //this is long to ensure that the entirety of the quality range can be reached by a single day's update
    private long qualityChange;
}
