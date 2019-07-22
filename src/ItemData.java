public class ItemData implements Comparable<ItemData>{
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
            dateRangeArray[i] = new DateRange(dateRanges[i * 3], dateRanges[i * 3 + 1], dateRanges[i * 3 + 2]);
        }

        sellByInfo = new SellByInfo(dateRangeArray, maxQuality, minQuality);
    }

    //TODO: refactor this into a lower-level class; sellByInfo would work
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
        try {
            item.sellIn--;
        } catch (ArithmeticException e) {
            item.sellIn = Integer.MIN_VALUE;
        }
    }

    @Override
    public String toString() {
        return "Item info:\n" + item.toString() + "\n\nSales info:\n" + sellByInfo.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !o.getClass().equals(this.getClass())) {
            return false;
        }
        return ((ItemData) o).getItem().toString().equals(item.toString()) && ((ItemData) o).getSellByInfo().equals(sellByInfo);
    }

    public int compareTo(ItemData itemData) {
        return Integer.compare(itemId, itemData.itemId);
    }
}
