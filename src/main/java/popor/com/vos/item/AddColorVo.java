package popor.com.vos.item;


import popor.com.utility.Converter;

public class AddColorVo {
    private final int itemIndex;
    private final String colorName;
    private final int variation;

    public AddColorVo(String itemIndexText, String colorName, String variationText) {
        this.itemIndex = Converter.toInt(itemIndexText, -1);
        this.colorName = colorName;
        this.variation = Converter.toInt(variationText, -1);
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public String getColorName() {
        return colorName;
    }

    public int getVariation() {
        return variation;
    }
}