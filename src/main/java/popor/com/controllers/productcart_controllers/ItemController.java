package popor.com.controllers.productcart_controllers;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import popor.com.PoporApplication;
import popor.com.enums.item.AddResult;
import popor.com.services.product_services.ItemService;
import popor.com.utility.Constant;
import popor.com.utility.Converter;
import popor.com.utility.IoUtil;
import popor.com.vos.item.AddVo;
import popor.com.vos.item.ItemVo;
import popor.com.vos.users.UserVo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(required = false) Integer page,
            Model model
    ) {
        int dataPerPage = 10; // 한 페이지에 보여질 데이터 수
        int pageBlockCount = 10; // 페이지 구간 페이지 수
        long totalCount = itemService.totalCount(); // 전체 데이터 수
        int efPage;
        int totalPage; // 전체 페이지 수
        int pageBlockStart; // 페이지 구간 시작 번호
        int pageBlockEnd; // 페이지 구간 종료 번호
        Integer pageBlockPrev; // 페이지 이전 구간 번호
        Integer pageBlockNext; // 페이지 다음 구간 번호
        List<ItemVo> itemVoList; // 현재 페이지 데이터 목록

        if (totalCount > 0) {
            // 1, 1
            // 10, 1
            totalPage = (int) Math.ceil((double) totalCount / dataPerPage);

            if (page == null || page <= 0)
                efPage = 1;
            else if (page > totalPage)
                efPage = totalPage;
            else
                efPage = page;

            pageBlockStart = efPage - (efPage - 1) % pageBlockCount;

            if (pageBlockStart <= 0)
                pageBlockStart = 1;

            pageBlockEnd = pageBlockStart + pageBlockCount - 1;

            if (pageBlockEnd > totalPage)
                pageBlockEnd = totalPage;

            pageBlockPrev = pageBlockStart <= 1 ? null : pageBlockStart - 1;
            pageBlockNext = pageBlockEnd >= totalPage ? null : pageBlockEnd + 1;
            itemVoList = itemService.list(dataPerPage, efPage);
        } else {
            efPage = 1;
            totalPage = 0;
            pageBlockStart = 1;
            pageBlockEnd = 1;
            pageBlockPrev = null;
            pageBlockNext = null;
            itemVoList = new ArrayList<>();
        }

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("page", efPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageBlockStart", pageBlockStart);
        model.addAttribute("pageBlockEnd", pageBlockEnd);
        model.addAttribute("pageBlockPrev", pageBlockPrev);
        model.addAttribute("pageBlockNext", pageBlockNext);
        model.addAttribute("itemVoList", itemVoList);

        return "product_cart/list";
    }

    @RequestMapping(value = "/itemImage", method = RequestMethod.GET)
    public void image(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam int index
    ) throws IOException {
        File file = new File(PoporApplication.dataPath + "\\" + index + ".bin");

        try (FileInputStream inputStream = new FileInputStream(file)) {
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                IoUtil.copy(inputStream, outputStream);
            }
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(HttpServletRequest request, HttpServletResponse response) {
        return "product_cart/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addPost(HttpServletRequest request, HttpServletResponse response,
                          @ModelAttribute AddVo addVo) throws
            SQLException, IOException {
        UserVo userVo = Converter.getUserVo(request.getSession());
        AddResult addResult = this.itemService.add(userVo, addVo);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, addResult.name().toLowerCase());
        return jsonResponse.toString(4);
    }


}