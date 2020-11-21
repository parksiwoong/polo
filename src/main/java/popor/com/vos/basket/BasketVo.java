package popor.com.vos.basket;

import java.util.Date;

public class BasketVo {
    private final String name;
    private final int price;
    private final String colorName;
    private final int colorVariation;
    private final String sizeName;
    private final int sizeVariation;
    private final Date dateTime;
    private final int count;

    public BasketVo(String name, int price, String colorName, int colorVariation, String sizeName, int sizeVariation, Date dateTime, int count) {
        this.name = name;
        this.price = price;
        this.colorName = colorName;
        this.colorVariation = colorVariation;
        this.sizeName = sizeName;
        this.sizeVariation = sizeVariation;
        this.dateTime = dateTime;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getColorName() {
        return colorName;
    }

    public int getColorVariation() {
        return colorVariation;
    }

    public String getSizeName() {
        return sizeName;
    }

    public int getSizeVariation() {
        return sizeVariation;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public int getCount() {
        return count;
    }
}