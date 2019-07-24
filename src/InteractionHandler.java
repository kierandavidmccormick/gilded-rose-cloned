import java.util.Scanner;

public class InteractionHandler {

    public InteractionHandler(Scanner scanner, GildedRose gildedRose) {
        this.scanner = scanner;
        this.gildedRose = gildedRose;
        this.gildedRose.setInteractionHandler(this);
    }

    Scanner scanner;
    GildedRose gildedRose;

    public boolean determineInteraction() {
        char c;
        while (true) {
            if (!scanner.hasNext()) {
                System.out.println("Please input a valid command");
            }
            c = Character.toUpperCase(scanner.next().charAt(0));
            switch (c) {
                case 'D':
                    advanceDay();
                    return false;
                case 'A':
                    addItem();
                    return false;
                case 'R':
                    removeItem();
                    return false;
                case 'E':
                    return true;
                default:
                    break;
            }
        }
    }

    public void advanceDay() {
        gildedRose.updateQuality();
        gildedRose.printItems();
    }

    public void removeItem() {
        ItemData item = getItemBYId();
        gildedRose.removeItem(item);
        gildedRose.printItems();
    }

    public void addItem() {
        gildedRose.addItem(createItem());
        gildedRose.printItems();
    }

    public ItemData getItemBYId() {
        int itemId = -1;
        ItemData itemData = null;
        System.out.print("Input Item ID: ");
        do {
            if (!scanner.hasNextInt()) {
                System.out.println("Please input a valid item ID");
            }
            itemId = scanner.nextInt();
            //TODO: fix capitalization for "ID"
            itemData = gildedRose.getItemByID(itemId);
            if (itemData == null) {
                System.out.println("Please input a valid item ID");
            }
        } while (itemId == -1 || itemData == null);
        return itemData;
    }

    public ItemData createItem() {
        scanner.nextLine();

        System.out.print("Please input an item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Please input the number of sell by days: ");
        //TODO: validation
        int sellByDays = scanner.nextInt();
        System.out.print("Please input the quality of the item: ");
        int quality = scanner.nextInt();

        int dateRangesSize = 0;
        do {
            //TODO: make some of these messages clearer
            System.out.print("Please input the number of date ranges: ");
            dateRangesSize = scanner.nextInt();
        } while (dateRangesSize < 0);

        DateRange[] dateRanges = new DateRange[dateRangesSize];
        do {
            for (int i = 0; i < dateRangesSize; i++) {
                System.out.println("Date range " + i);
                System.out.print("Enter start day: ");
                int startDay = scanner.nextInt();
                System.out.print("Enter end day: ");
                int endDay = scanner.nextInt();
                System.out.print("Enter quality change: ");
                int qualityChange = scanner.nextInt();
                dateRanges[i] = new DateRange(startDay, endDay, qualityChange);
            }
            //TODO: this is inefficient (not that bad, usually n is small), fixing it could be confusing
            if (!SellByInfo.validateDateRanges(dateRanges)) {
                System.err.println("ERROR: date ranges invalid; try again");
            }
        } while (!SellByInfo.validateDateRanges(dateRanges));

        System.out.print("Enter maximum quality: ");
        int maxQuality = scanner.nextInt();
        System.out.print("Enter minimum quality: ");
        int minQuality = scanner.nextInt();

        return new ItemData(new Item(itemName, sellByDays, quality), new SellByInfo(dateRanges, maxQuality, minQuality));
    }
}
