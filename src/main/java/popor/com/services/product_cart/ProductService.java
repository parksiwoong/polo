package popor.com.services.product_cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import popor.com.daos.productCart.ProductDao;

@Service
public class ProductService {
    private final ProductDao productDao;
        @Autowired
    public ProductService(ProductDao productDao){

            this.productDao = productDao;
        }



}
