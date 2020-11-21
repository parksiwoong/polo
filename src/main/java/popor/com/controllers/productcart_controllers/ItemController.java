package popor.com.controllers.productcart_controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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

@RestController
@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST},
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController extends StandardRestController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "add")
    public String add(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(value = "name", defaultValue = "") String name,
                      @RequestParam(value = "price", defaultValue = "") String price,
                      @RequestParam(name = "image", defaultValue = "") MultipartFile image) throws
            SQLException, IOException {
        AddVo addVo = new AddVo(name, price);
        UserVo userVo = Converter.getUserVo(request.getSession());
        AddResult addResult = this.itemService.add(userVo, addVo);
        String imageData = Converter.imageToString(image);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, addResult.name().toLowerCase());
        return jsonResponse.toString(4);
    }

    @RequestMapping(value = "add-color")
    public String addColor(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "item_id", defaultValue = "") String itemIndex,
                           @RequestParam(value = "name", defaultValue = "") String name,
                           @RequestParam(value = "variation", defaultValue = "0") String variation) throws
            SQLException {
        AddColorVo addColorVo = new AddColorVo(itemIndex, name, variation);
        UserVo userVo = Converter.getUserVo(request.getSession());

        AddResult addResult = this.itemService.addColor(userVo, addColorVo);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, addResult.name().toLowerCase());
        return jsonResponse.toString(4);
    }

    @RequestMapping(value = "add-size")
    public String addSize(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "item_id", defaultValue = "") String itemIndex,
                          @RequestParam(value = "name", defaultValue = "") String name,
                          @RequestParam(value = "variation", defaultValue = "0") String variation) throws
            SQLException {
        AddSizeVo addSizeVo = new AddSizeVo(itemIndex, name, variation);
        UserVo userVo = Converter.getUserVo(request.getSession());

        AddResult addResult = this.itemService.addSize(userVo, addSizeVo);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, addResult.name().toLowerCase());
        return jsonResponse.toString(4);
    }
}