package popor.com.vos.item;


import popor.com.utility.Converter;

public class AddSizeVo {
    private final int itemIndex;
    private final String sizeName;
    private final int variation;

    public AddSizeVo(String itemIndexText, String sizeName, String variationText) {
        this.itemIndex = Converter.toInt(itemIndexText, -1);
        this.sizeName = sizeName;
        this.variation = Converter.toInt(variationText, Integer.MAX_VALUE);
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public String getSizeName() {
        return sizeName;
    }

    public int getVariation() {
        return variation;
    }
}