public class TexttestFixture {
    public static void main(String[] args) {
        System.out.println("OMGHAI!");
        /*
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6) };
        */
        ItemData[] itemData = {
                ItemLibrary.getNamedItemData("Sulfuras, Hand of Ragnaros", 10, 80),
                ItemLibrary.getNamedItemData("Aged Brie", 5, 10),
                ItemLibrary.getNamedItemData("Conjured Aged Brie", 5, 10),
                ItemLibrary.getNamedItemData("Backstage Passes to a Concert", 12, 10),
                ItemLibrary.getNamedItemData("Conjured Backstage Passes to a Concert", 12, 10),
                new ItemData(
                        new Item("Cheese", 5, 15),
                        new SellByInfo(
                                new DateRange[] {
                                        new DateRange(Integer.MAX_VALUE, 0, -1),
                                        new DateRange(-1, Integer.MIN_VALUE, -2)
                                },
                                0, 50
                        )
                ),
                new ItemData(
                        new Item("Conjured Cheese", 5, 15),
                        new SellByInfo(
                                new DateRange[] {
                                        new DateRange(Integer.MAX_VALUE, 0, -2),
                                        new DateRange(-1, Integer.MIN_VALUE, -4)
                                },
                                0, 50
                        )
                )
        };
        GildedRose app = new GildedRose(itemData);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (ItemData item : itemData) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
    }

}
