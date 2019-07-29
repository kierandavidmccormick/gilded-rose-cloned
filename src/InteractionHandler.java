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
                    System.out.println("Please input a valid command");
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
        if (item != null) {
            gildedRose.removeItem(item);
        } else {
            System.out.println("Cancelled item removal");
        }
        gildedRose.printItems();
    }

    public void addItem() {
        ItemData item = createItem();
        if (item != null) {
            gildedRose.addItem(item);
        } else {
            System.out.println("Cancelled item addition");
        }
        gildedRose.printItems();
    }

    public ItemData getItemBYId() {
        Integer itemId = null;
        ItemData itemData = null;
        System.out.print("Input Item ID: ");
        do {
            itemId = getInt();
            if (itemId == null) {
                return null;
            }
            itemData = gildedRose.getItemById(itemId);
            if (itemData == null) {
                System.out.println("Please input a valid item ID: ");
            }
        } while (itemData == null);
        return itemData;
    }

    public ItemData createItem() {
        scanner.nextLine();
        System.out.print("Please input an item name: ");
        String itemName = scanner.nextLine();
        /*
        System.out.print("Please input the number of sell by days: ");
        int sellByDays = getInt();
        System.out.print("Please input the quality of the item: ");
        int quality = getInt();
        */
        //prototype of new system:
        //can simplify the ifs
        System.out.print("Please input the number of sell by days: ");
        Integer sellByDays = getInt();
        if (sellByDays == null) {
            return null;
        }

        System.out.print("Please input the quality of the item: ");
        Integer quality = getInt();
        if (quality == null) {
            return null;
        }

        System.out.print("Please input the number of date ranges: ");
        Integer dateRangesSize = null;
        while (true) {
            dateRangesSize = getInt();
            if (dateRangesSize == null) {
                return null;
            } else if (dateRangesSize > 0) {
                break;
            }
            System.out.print("Please input a valid number of date ranges ( > 0):");
        }

        DateRange[] dateRanges = new DateRange[dateRangesSize];
        SellByInfo sellByInfo;
        while (true) {
            for (int i = 0; i < dateRangesSize; i++) {
                System.out.println("Date range " + i);

                System.out.print("Enter start day: ");
                Integer startDay = getInt();
                if (startDay == null) {
                    return null;
                }

                System.out.print("Enter end day: ");
                Integer endDay = getInt();
                if (endDay == null) {
                    continue;
                }


                System.out.print("Enter quality change: ");
                Integer qualityChange = getInt();
                if (qualityChange == null) {
                    continue;
                }

                dateRanges[i] = new DateRange(startDay, endDay, qualityChange);
            }

            System.out.print("Enter maximum quality: ");
            Integer maxQuality = getInt();
            if (maxQuality == null) {
                return null;
            }

            System.out.print("Enter minimum quality: ");
            Integer minQuality = getInt();
            if (minQuality == null) {
                return null;
            }

            sellByInfo = new SellByInfo(dateRanges, maxQuality, minQuality);

            if (!sellByInfo.validate()) {
                System.out.println("ERROR: Date Range or Quality Range invalid; try again");
            } else {
                break;
            }
        }

        return new ItemData(new Item(itemName, sellByDays, quality), sellByInfo);
    }

    public Integer getInt() {
        String intString = null;
        while (true) {
            intString = scanner.nextLine();
            if (intString.matches(RegexLibrary.MAXVALSTRING)) {
                return Integer.MAX_VALUE;
            } else if (intString.matches(RegexLibrary.MINVALSTRING)) {
                return Integer.MIN_VALUE;
            } else if (intString.length() == 1 && Character.toUpperCase(intString.charAt(0)) == 'C') {
                return null;
            }
            int i;
            try {
                i = Integer.parseInt(intString);
            } catch (NumberFormatException e) {
                //TODO: ensure that this message is displayed elsewhere
                System.out.println("Please enter a valid number, or [C] to cancel");
                continue;
            }
            return i;
        }
    }
}
