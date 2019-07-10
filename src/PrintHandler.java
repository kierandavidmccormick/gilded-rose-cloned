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

        //TODO: column headers

        return header;
    }

    public StringBuilder getBody(Collection<ItemData> items) {
        StringBuilder body = new StringBuilder();
        for (ItemData item : items) {
            body.append(formatItemData(item, 4));
        }
        if (items.size() == 0) {
            body.append("No items in inventory\n");
        }
        return body;
    }

    public StringBuilder formatItemData(ItemData item, int columnSpace) {
        StringBuilder itemString = new StringBuilder();
        if (showItemIds) {
            itemString.append(StringUtils.abbreviate(Integer.toString(item.getItemId()), 10)).append(genChars(' ', columnSpace));
        }
        itemString.append(StringUtils.abbreviate(item.getItem().name, 20)).append(genChars(' ', columnSpace))
                    .append(StringUtils.abbreviate(Integer.toString(item.getItem().quality), 5)).append(genChars(' ', columnSpace))
                    .append(StringUtils.abbreviate(Integer.toString(item.getItem().sellIn), 5)).append(genChars(' ', columnSpace))
                    .append("\n");

        return null;
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
