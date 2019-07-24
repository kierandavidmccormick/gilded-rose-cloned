import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GildedRose gildedRose = new GildedRose();
        gildedRose.initItems();
        InteractionHandler interactionHandler = new InteractionHandler(new Scanner(System.in), gildedRose);
        gildedRose.printItems();
        while (!interactionHandler.determineInteraction()) {
        }
    }
}
