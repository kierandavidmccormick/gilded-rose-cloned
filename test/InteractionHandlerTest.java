import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

public class InteractionHandlerTest {

    InteractionHandler interactionHandler;
    GildedRose gildedRose;

    @BeforeEach
    public void beforeEach() {
        gildedRose = spy(GildedRose.class);
        //doNothing().when(gildedRose).printItems();
        gildedRose.initItems();

    }

    @Test
    public void advanceDayTest() {
        interactionHandler = new InteractionHandler(new Scanner(
                "d\nd\nd\ne\n"
        ), gildedRose);
        interactionHandler.determineInteraction();
        interactionHandler.determineInteraction();
        interactionHandler.determineInteraction();

        ItemData sulfuras = ItemLibrary.getNamedItemData("Sulfuras, Hand of Ragnaros", 10, 80);
        ItemData conjuredAgedBrie = ItemLibrary.getNamedItemData("Conjured Aged Brie", 0, 13);
        ItemData backstagePasses = ItemLibrary.getNamedItemData("Backstage Passes to a boring concert", 2, 22);

        assertEquals(sulfuras, gildedRose.getItems().get(0));
        assertEquals(conjuredAgedBrie, gildedRose.getItems().get(1));
        assertEquals(backstagePasses, gildedRose.getItems().get(2));
    }

    @Test
    public void removeItemTest() {
        interactionHandler = new InteractionHandler(new Scanner(
                "r\n1\nr\n0\nr\n2\n"
        ), gildedRose);
        ItemData sulfuras = gildedRose.getItems().get(0);
        ItemData conjuredAgedBrie = gildedRose.getItems().get(1);
        ItemData backstagePasses = gildedRose.getItems().get(2);

        assertTrue(gildedRose.getItems().contains(sulfuras));
        assertTrue(gildedRose.getItems().contains(conjuredAgedBrie));
        assertTrue(gildedRose.getItems().contains(backstagePasses));

        gildedRose.interact();

        assertTrue(gildedRose.getItems().contains(sulfuras));
        assertFalse(gildedRose.getItems().contains(conjuredAgedBrie));
        assertTrue(gildedRose.getItems().contains(backstagePasses));

        gildedRose.interact();

        assertFalse(gildedRose.getItems().contains(sulfuras));
        assertFalse(gildedRose.getItems().contains(conjuredAgedBrie));
        assertTrue(gildedRose.getItems().contains(backstagePasses));

        gildedRose.interact();

        assertFalse(gildedRose.getItems().contains(sulfuras));
        assertFalse(gildedRose.getItems().contains(conjuredAgedBrie));
        assertFalse(gildedRose.getItems().contains(backstagePasses));
    }

    @Test
    public void addItemTest() {
        interactionHandler = new InteractionHandler(new Scanner(
                "a\nThe Item\n14\n45\n2\n99999\n1\n-2\n0\n-99999\n-99999\n50\n0\n"
        ), gildedRose);
        gildedRose.interact();
        ItemData shouldMatch = null;
        try {
            shouldMatch = new ItemData("The Item", 14, 45, 50, 0, 2, 99999, 1, -2, 0, -99999, -99999);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assertEquals(shouldMatch, gildedRose.getItems().get(3));
    }
}
