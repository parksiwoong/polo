package popor.com.vos.item;


import popor.com.utility.Converter;

public class AddVo {
    private final String name;
    private final int price;

    public AddVo(String name, String priceText) {
        this.name = name;
        this.price = Converter.toInt(priceText, -1);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}