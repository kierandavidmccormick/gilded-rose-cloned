import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GildedRose gildedRose = new GildedRose();
        gildedRose.initItems();
        new InteractionHandler(new Scanner(System.in), gildedRose);
        gildedRose.printItems();
        gildedRose.interact();
    }
}
