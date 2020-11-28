package popor.com.vos.item;


import popor.com.utility.Converter;

public class AddVo {
    private final String name;
    private final int price;
    private final String color;
    private final String size;

    public AddVo(String name, String priceText, String color,String size) {
        this.name = name;
        this.price = Converter.toInt(priceText, -1);
        this.color = color;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }
}