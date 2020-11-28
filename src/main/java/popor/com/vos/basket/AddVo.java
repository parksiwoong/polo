package popor.com.vos.basket;


import popor.com.utility.Converter;

public class AddVo {
    private final int itemIndex;
    private final int count;

    public AddVo(String itemIndex, String count) {
        this.itemIndex = Converter.toInt(itemIndex, -1);
          this.count = Converter.toInt(count, -1);
    }

    public int getItemIndex() {
        return itemIndex;
    }



    public int getCount() {
        return count;
    }
}