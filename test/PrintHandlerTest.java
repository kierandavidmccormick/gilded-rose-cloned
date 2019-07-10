import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PrintHandlerTest {


    @Test
    public void getHeaderTest() {
        PrintHandler print = new PrintHandler(false, 20);
        assertEquals("*    Gilded Rose   *\n--------------------\n", print.getHeader().toString());
        print.setScreenWidth(3);
        assertEquals("* Gilded Rose *\n---\n", print.getHeader().toString());
    }

    @Test
    public void genCharsTest() {
        PrintHandler print = new PrintHandler(false, 120);
        assertEquals("aaaaaaaaaa", print.genChars('a', 10).toString());
        assertEquals("", print.genChars('g', 0).toString());
    }

    @Test
    public void setStringWidthTest() {
        PrintHandler print = new PrintHandler(false, 120);
        assertEquals("123456789...", print.setStringWidth("123456789 123456789 123456789 ", 12).toString());
        assertEquals("123       ", print.setStringWidth("123", 10).toString());
        assertEquals("123456", print.setStringWidth("123456", 6).toString());
        assertEquals("12", print.setStringWidth("123456", 2).toString());
        assertEquals("12", print.setStringWidth("12", 2).toString());
        assertEquals("", print.setStringWidth("123456", 0).toString());
        assertEquals("      ", print.setStringWidth("", 6).toString());
        assertEquals("", print.setStringWidth("", 0).toString());
    }
}
