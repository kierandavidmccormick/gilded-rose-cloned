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
        //TODO: refactor away all the longs; they must go
        //instead, have changes of INT_MAX or INT_MIN set the quality to that value, and do everything in an int
        /*
        long qualityChange = sellByInfo.getQualityChange(item.sellIn);
        long quality = item.quality;
        long longResetValue = qualityChange > 0 ? Long.MAX_VALUE : Long.MIN_VALUE;

        try {
            quality = Math.addExact(quality, qualityChange);
        } catch (ArithmeticException e) {
            quality = longResetValue;
        }

        if (quality > sellByInfo.getMaxQuality()) {
            item.quality = sellByInfo.getMaxQuality();
        } else if (quality < sellByInfo.getMinQuality()) {
            item.quality = sellByInfo.getMinQuality();
        } else {
            item.quality = (int)quality;
        }
        */
        int qualityChange = (int)sellByInfo.getQualityChange(item.sellIn);

        if (qualityChange == Integer.MAX_VALUE || qualityChange == Integer.MIN_VALUE) {
            item.quality = qualityChange;
        } else {
            try {
                item.quality = Math.addExact(item.quality, qualityChange);
            } catch (ArithmeticException e) {
                item.quality = qualityChange > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        }
        if (item.quality > sellByInfo.getMaxQuality()) {
            item.quality = sellByInfo.getMaxQuality();
        } else if (item.quality < sellByInfo.getMinQuality()) {
            item.quality = sellByInfo.getMinQuality();
        }

        if (item.sellIn != Integer.MIN_VALUE && item.sellIn != Integer.MAX_VALUE) {
            item.sellIn--;
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
