//non-exhaustive list of possible items
//it is up to the user to guarantee that information is correct for items without special names

public abstract class ItemLibrary {
    public static ItemData getNamedItemData(String name, int sellByDays, int baseQuality) {
        if (name == null) {
            return null;
        }
        ItemData itemData = null;
        if (name.matches(RegexLibrary.SULFURAS)) {
            itemData = new ItemData(
                    new Item(name, Integer.MAX_VALUE, 80),
                    new SellByInfo(new DateRange(Integer.MAX_VALUE, Integer.MIN_VALUE, 0), 80, 80)
            );
        } else if (name.matches(RegexLibrary.AGEDBRIE)) {
            itemData = new ItemData(
                    new Item(name, sellByDays, baseQuality),
                    new SellByInfo(new DateRange(Integer.MAX_VALUE, Integer.MIN_VALUE, 1), 50, 0)
            );
        } else if (name.matches(RegexLibrary.BACKSTAGEPASSES)) {
            itemData = new ItemData(
                    new Item(name, sellByDays, baseQuality),
                    new SellByInfo(new DateRange[]{
                            new DateRange(Integer.MIN_VALUE, 11, 1),
                            new DateRange(10, 5, 2),
                            new DateRange(4, 1, 5),
                            new DateRange(0, Integer.MIN_VALUE, Long.MIN_VALUE)
                    }, 50, 0)
            );
        }
        if (itemData != null && name.matches(RegexLibrary.CONJURED)) {
            for (DateRange dateRange : itemData.getSellByInfo().getDateRanges()) {
                if (dateRange.getQualityChange() < 0) {
                    if (dateRange.getQualityChange() <= Long.MIN_VALUE / 2) {
                        dateRange.setQualityChange(Long.MIN_VALUE);
                    } else {
                        long l = dateRange.getQualityChange() * 2;
                        dateRange.setQualityChange(l);
                    }
                }

            }
        }
        return itemData;
    }
}
