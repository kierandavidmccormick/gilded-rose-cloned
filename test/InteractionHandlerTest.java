import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class InteractionHandlerTest {

    InteractionHandler interactionHandler;
    GildedRose gildedRose;

    @BeforeEach
    public void beforeEach() {
        gildedRose = new GildedRose();
        gildedRose.initItems();
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
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
        ItemData backstagePasses = ItemLibrary.getNamedItemData("Backstage Passes to a boring concert", 2, 19);

        assertEquals(sulfuras, gildedRose.getItems().get(0));
        assertEquals(conjuredAgedBrie, gildedRose.getItems().get(1));
        assertEquals(backstagePasses, gildedRose.getItems().get(2));
    }

    @Test
    public void removeItemTest() {
        interactionHandler = new InteractionHandler(new Scanner(
                "r\n1\nr\n0\nr\n2\ne\n"
        ), gildedRose);
        ItemData sulfuras = gildedRose.getItems().get(0);
        ItemData conjuredAgedBrie = gildedRose.getItems().get(1);
        ItemData backstagePasses = gildedRose.getItems().get(2);

        assertTrue(gildedRose.getItems().contains(sulfuras));
        assertTrue(gildedRose.getItems().contains(conjuredAgedBrie));
        assertTrue(gildedRose.getItems().contains(backstagePasses));

        interactionHandler.determineInteraction();

        assertTrue(gildedRose.getItems().contains(sulfuras));
        assertFalse(gildedRose.getItems().contains(conjuredAgedBrie));
        assertTrue(gildedRose.getItems().contains(backstagePasses));

        interactionHandler.determineInteraction();

        assertFalse(gildedRose.getItems().contains(sulfuras));
        assertFalse(gildedRose.getItems().contains(conjuredAgedBrie));
        assertTrue(gildedRose.getItems().contains(backstagePasses));

        interactionHandler.determineInteraction();

        assertFalse(gildedRose.getItems().contains(sulfuras));
        assertFalse(gildedRose.getItems().contains(conjuredAgedBrie));
        assertFalse(gildedRose.getItems().contains(backstagePasses));
    }

    @Test
    public void cancelRemoveItemTest() {
        interactionHandler = new InteractionHandler(new Scanner(
                "r\nc\ne\n"
        ), gildedRose);
        gildedRose.interact();
        assertEquals(3, gildedRose.getItems().size());

    }

    @Test
    public void addItemTest() {
        interactionHandler = new InteractionHandler(new Scanner(
                "a\nThe Item\n14\n45\n2\nINT_MAX\n1\n-2\n0\nINT_MIN\nINTEGER.MINVALUE\n50\n0\ne\n"
        ), gildedRose);
        gildedRose.interact();
        ItemData shouldMatch = null;
        try {
            shouldMatch = new ItemData("The Item", 14, 45, 50, 0, 2, Integer.MAX_VALUE, 1, -2, 0, Integer.MIN_VALUE, Integer.MIN_VALUE);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assertEquals(shouldMatch, gildedRose.getItems().get(3));
    }

    @Test
    public void addItemTestInvalidDateRange() {
        interactionHandler = new InteractionHandler(new Scanner(
                "a\nThe Item\n1\n3\n-2\nminimum int\n1\nint max\nint min\n0\n3\n3\ne\n"
        ), gildedRose);
        gildedRose.interact();
        ItemData shouldMatch = null;
        try {
            shouldMatch = new ItemData("The Item", 1, 3, 3, 3, 1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assertEquals(shouldMatch, gildedRose.getItems().get(3));
    }

    @Test
    public void cancelAddItemTest() {
        interactionHandler = new InteractionHandler(new Scanner(
                "a\nThe Item\n2\n5\n2\n1\nc\n2\n3\nc\n4\n5\n6\n7\nc\nc\n" + "a\nThe Item\n14\n45\n2\nINT_MAX\n1\n-2\n0\nINT_MIN\nINTEGER.MINVALUE\n50\n0\ne\n"
        ), gildedRose);
        interactionHandler.determineInteraction();
        assertEquals(3, gildedRose.getItems().size());
        ItemData shouldMatch = null;
        try {
            shouldMatch = new ItemData("The Item", 14, 45, 50, 0, 2, Integer.MAX_VALUE, 1, -2, 0, Integer.MIN_VALUE, Integer.MIN_VALUE);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        interactionHandler.determineInteraction();
        assertEquals(shouldMatch, gildedRose.getItems().get(3));
    }

    @Test
    public void getIntTest() {
        interactionHandler = new InteractionHandler(new Scanner(
                "4\n-7\n99999\nINT_MAX\nInteger.MIN_VALUE\nmaximum integer value\nmaximum-value\naaaaa\n14\ne\n"
        ), gildedRose);
        assertEquals(4, interactionHandler.getInt());
        assertEquals(-7, interactionHandler.getInt());
        assertEquals(99999, interactionHandler.getInt());
        assertEquals(Integer.MAX_VALUE, interactionHandler.getInt());
        assertEquals(Integer.MIN_VALUE, interactionHandler.getInt());
        assertEquals(Integer.MAX_VALUE, interactionHandler.getInt());
        assertEquals(Integer.MAX_VALUE, interactionHandler.getInt());
        assertEquals(14, interactionHandler.getInt());
    }
}
