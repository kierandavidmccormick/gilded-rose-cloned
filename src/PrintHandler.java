import java.util.Collection;
import org.apache.commons.lang3.StringUtils;

public class PrintHandler {
    public boolean isShowItemIds() {
        return showItemIds;
    }

    public void setShowItemIds(boolean showItemIds) {
        this.showItemIds = showItemIds;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public PrintHandler(boolean showItemIds, int screenWidth) {
        this.showItemIds = showItemIds;
        this.screenWidth = screenWidth;
    }

    public boolean showItemIds;
    public int screenWidth;

    final int ID_COLUMN_WIDTH = 10;
    final int NAME_COLUMN_WIDTH = 40;
    final int QUALITY_COLUMN_WIDTH = 8;
    final int SELLBY_COLUMN_WIDTH = 8;
    final int COLUMN_SPACING = 4;

    public String getPrintString(Collection<ItemData> items) {
        StringBuilder printString = new StringBuilder();
        printString.append(getHeader());
        printString.append(getBody(items));
        return printString.toString();
    }

    public StringBuilder getHeader() {
        StringBuilder header = new StringBuilder();
        String headerString = "Gilded Rose";
        String leaderString = "*";
        double upperGapSpaces = (screenWidth - headerString.length() - leaderString.length() * 2) / 2.0;

        header.append(leaderString);
        header.append(genChars(' ', upperGapSpaces > 1 ? (int)Math.ceil(upperGapSpaces) : 1));
        header.append(headerString);
        header.append(genChars(' ', upperGapSpaces > 1 ? (int)Math.floor(upperGapSpaces) : 1));
        header.append(leaderString);
        header.append('\n');

        header.append(genChars('-', screenWidth));
        header.append('\n');

        if (showItemIds) {
            header.append(setStringWidth("Item ID:", ID_COLUMN_WIDTH)).append(genChars(' ', COLUMN_SPACING));
        }
        header.append(setStringWidth("NAME:", NAME_COLUMN_WIDTH)).append(genChars(' ', COLUMN_SPACING));
        header.append(setStringWidth("Quality:", QUALITY_COLUMN_WIDTH)).append(genChars(' ', COLUMN_SPACING));
        header.append(setStringWidth("Sell In:", SELLBY_COLUMN_WIDTH)).append(genChars(' ', COLUMN_SPACING));
        header.append("\n\n");

        return header;
    }

    public StringBuilder getBody(Collection<ItemData> items) {
        StringBuilder body = new StringBuilder();
        for (ItemData item : items) {
            body.append(formatItemData(item));
        }
        if (items.size() == 0) {
            body.append("No items in inventory\n");
        }
        body.append("\n");
        return body;
    }

    public StringBuilder formatItemData(ItemData item) {
        StringBuilder itemString = new StringBuilder();
        if (showItemIds) {
            itemString.append(setStringWidth(Integer.toString(item.getItemId()), ID_COLUMN_WIDTH)).append(genChars(' ', COLUMN_SPACING));
        }
        itemString.append(setStringWidth(item.getItem().name, NAME_COLUMN_WIDTH)).append(genChars(' ', COLUMN_SPACING))
                    .append(setStringWidth(Integer.toString(item.getItem().quality), QUALITY_COLUMN_WIDTH)).append(genChars(' ', COLUMN_SPACING))
                    .append(setStringWidth(Integer.toString(item.getItem().sellIn), SELLBY_COLUMN_WIDTH)).append(genChars(' ', COLUMN_SPACING))
                    .append("\n");

        return itemString;
    }

    public StringBuilder genChars(char c, int count) {
        StringBuilder chars = new StringBuilder();
        for (int i = 0; i < count; i++){
            chars.append(c);
        }
        return chars;
    }

    public StringBuilder setStringWidth(String s, int width) {
        StringBuilder outString = new StringBuilder();
        if (s.length() > width) {
            if (width > 4) {
                outString.append(StringUtils.abbreviate(s, width));
            } else if (width > 1) {
                outString.append(s.substring(0, width));
            }
        } else {
            outString.append(s).append(genChars(' ', width - s.length()));
        }
        return outString;
    }
}
