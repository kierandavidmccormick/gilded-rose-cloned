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

    public void setQualityChange(long qualityChange) {
        this.qualityChange = qualityChange;
    }

    public DateRange(int startDay, int endDay, long qualityChange) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.qualityChange = qualityChange;
    }

    private int startDay;
    private int endDay;
    //this is long to ensure that the entirety of the quality range can be reached by a single day's update
    private long qualityChange;

    @Override
    public String toString() {
        return "StartDay: " + startDay + ",  EndDay: " + endDay + ",  QualityChange: " + qualityChange;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !o.getClass().equals(this.getClass())) {
            return false;
        }
        return ((DateRange)o).getStartDay() == startDay && ((DateRange)o).getEndDay() == endDay && ((DateRange)o).getQualityChange() == qualityChange;
    }
}
