package popor.com.controllers.productcart_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import popor.com.services.product_cart.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;}


    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public String shopGet(HttpServletRequest request, HttpServletResponse response) {
        return "productCart/product";
    }


    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String productGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        return "productCart/product";
    }


    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String productPost(HttpServletRequest request, HttpServletResponse response,
           @RequestParam(name = "number", defaultValue = "")int number,
           @RequestParam(name = "name", defaultValue = "") String name,
           @RequestParam(name = "price", defaultValue = "") String price,
           @RequestParam(name = "url", defaultValue = "") String url,
           @RequestParam(name = "text", defaultValue = "") String text)
        throws SQLException, IOException{

        return "cartlist"; }

}

