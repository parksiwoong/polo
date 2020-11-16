package popor.com.controllers.main_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import popor.com.services.bbs_services.BoardService;
import popor.com.services.userServices.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    private final UserService userService;
    private final BoardService boardService;

    @Autowired
    public MainController(UserService userService, BoardService boardService) {
        this.userService = userService;
        this.boardService = boardService;
    }


    @RequestMapping(value = "/polo", method = RequestMethod.GET)
    public String main(HttpServletRequest request, HttpServletResponse response) {
        return "main/polo";
    }

    @RequestMapping(value = "/kakaomap", method = RequestMethod.GET)
    public String mapGet(HttpServletRequest request, HttpServletResponse response) {return "kakao/kakaomap"; }


    @ExceptionHandler
    public void hendleException(HttpServletResponse response, Exception exception) {
        try {
            response.getWriter().print(exception.getMessage());
        } catch (Exception ignored) {
        }
    }

}
