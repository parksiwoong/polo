package popor.com.controllers.productcart_controllers;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import popor.com.enums.item.AddResult;
import popor.com.services.product_services.ItemService;
import popor.com.utility.Constant;
import popor.com.utility.Converter;
import popor.com.vos.item.AddColorVo;
import popor.com.vos.item.AddSizeVo;
import popor.com.vos.item.AddVo;
import popor.com.vos.users.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


//@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST},
//        produces = MediaType.APPLICATION_JSON_VALUE)

@Controller
public class ItemController {
//extends StandardRestController
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(HttpServletRequest request, HttpServletResponse response) {
        return "product_cart/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addPost(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "name", defaultValue = "") String name,
                          @RequestParam(value = "price", defaultValue = "") String price,
                          @RequestParam(value = "color", defaultValue = "") String color,
                          @RequestParam(value = "size", defaultValue = "") String size) throws
 //                      @RequestParam(value = "image", defaultValue = "") MultipartFile image)
            SQLException, IOException {
        AddVo addVo = new AddVo(name, price,color,size);
        UserVo userVo = Converter.getUserVo(request.getSession());
        AddResult addResult = this.itemService.add(userVo, addVo);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, addResult.name().toLowerCase());
        return jsonResponse.toString(4);
    }


}