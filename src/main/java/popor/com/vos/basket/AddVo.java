package popor.com.vos.basket;


import popor.com.utility.Converter;

public class AddVo {
    private final int itemIndex;
    private final int colorIndex;
    private final int sizeIndex;
    private final int count;

    public AddVo(String itemIndex, String colorIndex, String sizeIndex, String count) {
        this.itemIndex = Converter.toInt(itemIndex, -1);
        this.colorIndex = Converter.toInt(colorIndex, -1);
        this.sizeIndex = Converter.toInt(sizeIndex, -1);
        this.count = Converter.toInt(count, -1);
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public int getColorIndex() {
        return colorIndex;
    }

    public int getSizeIndex() {
        return sizeIndex;
    }

    public int getCount() {
        return count;
    }
}