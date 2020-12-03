package popor.com.controllers.productcart_controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import popor.com.enums.productbasket.AddResult;
import popor.com.enums.productbasket.GetResult;
import popor.com.services.product_services.BasketService;
import popor.com.utility.BasketGetResultContainer;
import popor.com.utility.Constant;

import popor.com.utility.Converter;
import popor.com.vos.basket.AddVo;
import popor.com.vos.basket.BasketListVo;
import popor.com.vos.basket.BasketVo;
import popor.com.vos.users.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("basket")
public class BasketController {
    private final BasketService basketService;


    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(
            HttpServletRequest request,
            Model model
    ) {
        UserVo userVo = Converter.getUserVo(request.getSession());

        if(userVo == null)
            return "basket/needLogin";

        List<BasketListVo> basketListVoList = basketService.list(userVo.getIndex());

        model.addAttribute("userVo", userVo);
        model.addAttribute("basketListVoList", basketListVoList);

        return "basket/list";
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public String add(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(name = "itemIndex", defaultValue = "") String itemIndex,
                      @RequestParam(name = "count", defaultValue = "") String count) throws
            SQLException {
        UserVo userVo = Converter.getUserVo(request.getSession());
        AddVo addVo = new AddVo(itemIndex, count);
        AddResult addResult = this.basketService.add(userVo, addVo);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, addResult.name().toLowerCase());
        return jsonResponse.toString(4);
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(name = "basketIndex", required = false) Integer basketIndex) {
        UserVo userVo = Converter.getUserVo(request.getSession());
        AddResult addResult;

        if(userVo == null){
            addResult = AddResult.NOT_ALLOWED;
        }else if(basketIndex == null) {
            addResult = AddResult.INVALID_INPUT;
        }else{
            try {
                basketService.delete(basketIndex);
                addResult = AddResult.SUCCESS;
            }catch (Throwable e){
                addResult = AddResult.FAIL;
            }
        }

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, addResult.name().toLowerCase());
        return jsonResponse.toString(4);
    }

    @RequestMapping(value = "deleteJsp")
    public String deleteJsp(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(name = "basketIndex") int basketIndex) {
        UserVo userVo = Converter.getUserVo(request.getSession());

        if(userVo == null)
            return "basket/needLogin";

        basketService.delete(basketIndex);

        return "redirect:/basket/list";
    }

    @RequestMapping(value = "get")
    @ResponseBody
    public String get(HttpServletRequest request, HttpServletResponse response) throws
            SQLException {
        UserVo userVo = Converter.getUserVo(request.getSession());
        BasketGetResultContainer basketGetResultContainer = this.basketService.get(userVo);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, basketGetResultContainer.getGetResult().name().toLowerCase());
        if (basketGetResultContainer.getGetResult() == GetResult.SUCCESS) {
            JSONArray jsonBaskets = new JSONArray();
            for(BasketVo basket : basketGetResultContainer.getBaskets()) {
                JSONObject jsonBasket = new JSONObject();
                jsonBasket.put("itemName", basket.getName());
                jsonBasket.put("itemPrice", basket.getPrice());
                jsonBasket.put("colorName", basket.getColorName());
                jsonBasket.put("sizeName", basket.getSizeName());
                jsonBasket.put("dateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(basket.getDateTime()));
                jsonBasket.put("count", basket.getCount());
                jsonBaskets.put(jsonBasket);
            }
            jsonResponse.put("baskets", jsonBaskets);
        }
        return jsonResponse.toString(4);
    }
}