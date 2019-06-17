public class ItemData {
    public ItemData(Item item, SellByInfo sellByInfo) {
        this.item = item;
        this.sellByInfo = sellByInfo;
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

    private Item item;
    private SellByInfo sellByInfo;

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
