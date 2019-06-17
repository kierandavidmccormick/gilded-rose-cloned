public class ItemData {
    public ItemData(Item item, SellByInfo sellByInfo) {
        this.item = item;
        this.sellByInfo = sellByInfo;
    }

    public ItemData(String name, int sellIn, int quality, int maxQuality, int minQuality, int numberOfDateRanges, int... dateRanges) throws Exception{
        if (dateRanges.length % 3 != 0 || dateRanges.length == 0) {
            throw new Exception("Invalid date ranges");
        }
        this.item = new Item(name, sellIn, quality);
        DateRange[] dateRangeArray = new DateRange[numberOfDateRanges];
        for (int i = 0; i < numberOfDateRanges; i++) {
            dateRangeArray[i] = new DateRange(dateRanges[i], dateRanges[i + 1], dateRanges[i + 2]);
        }

        sellByInfo = new SellByInfo(dateRangeArray, maxQuality, minQuality);
    }

    //TODO: more robust validation
    public static boolean validateDateRanges(DateRange[] dateRanges) {
        if (dateRanges == null || dateRanges.length == 0) {
            return false;
        }
        int lowVal = Integer.MAX_VALUE;
        int highVal = Integer.MIN_VALUE;
        for (DateRange dateRange : dateRanges) {
            if (dateRange.getStartDay() > dateRange.getEndDay()) {
                return false;
            }
            if (dateRange.getStartDay() == lowVal || dateRange.getStartDay() == highVal || dateRange.getEndDay() == lowVal || dateRange.getEndDay() == highVal) {
                return false;
            }
            if ((dateRange.getStartDay() > lowVal && dateRange.getStartDay() < highVal) || (dateRange.getEndDay() > lowVal && dateRange.getEndDay() < highVal)) {
                return false;
            }
            if (dateRange.getStartDay() > highVal) {
                highVal = dateRange.getStartDay();
            }
            if (dateRange.getEndDay() < lowVal) {
                lowVal = dateRange.getEndDay();
            }
        }
        return true;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public SellByInfo getSellByInfo() {
        return sellByInfo;
    }

    public void setSellByInfo(SellByInfo sellByInfo) {
        this.sellByInfo = sellByInfo;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    private Item item;
    private SellByInfo sellByInfo;
    private int itemId;

    public void updateItem() {
        long uncappedQuality = item.quality + sellByInfo.getQualityChange(item.sellIn);
        if (uncappedQuality < sellByInfo.getMinQuality()) {
            uncappedQuality = sellByInfo.getMinQuality();
        } else if (uncappedQuality > sellByInfo.getMaxQuality()) {
            uncappedQuality = sellByInfo.getMaxQuality();
        }
        item.quality = (int) uncappedQuality;
        if (item.sellIn > 0) {
            item.sellIn--;
        }
    }

    @Override
    public String toString() {
        return "Item info:\n" + item.toString() + "\n\nSales info:\n" + sellByInfo.toString();
    }
}
