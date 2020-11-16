package popor.com.vos;

public class ProductVo {
    private final int product_number;
    private final String product_name;
    private final int price;
    private final String url;
    private final String description;

    public ProductVo(int product_number, String product_name, int price, String url, String description) {
        this.product_number = product_number;
        this.product_name = product_name;
        this.price = price;
        this.url = url;
        this.description = description;
    }
    public int getProduct_number() {
        return product_number;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

}
