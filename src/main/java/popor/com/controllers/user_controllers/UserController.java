package popor.com.controllers.user_controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import popor.com.enums.UserLoginResult;
import popor.com.enums.UserRegisterResult;
import popor.com.services.userServices.UserService;
import popor.com.vos.users.UserLoginVo;
import popor.com.vos.users.UserRegisterVo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(HttpServletRequest request, HttpServletResponse response) {
        return "users/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginPost(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(name = "email", defaultValue = "") String email,
                          @RequestParam(name = "password", defaultValue = "") String password)
            throws SQLException, IOException {
        System.out.println("로그인 요청됨 (이메일 : " + email + " / 비밂번호 : " + password + ")");

        UserLoginVo userLoginVo = new UserLoginVo(email, password, request);
        UserLoginResult userLoginResult = this.userService.login(userLoginVo); //로그인 입력했을때 담고있는거
        request.getSession().setAttribute("UserLoginResult", userLoginResult); //리스트[0,1,2]씩으로 만든 성공 실패여부 // request가지고오는,getSession() 세션을 불러와서 setAttribute 저장하는거
        request.getSession().setAttribute("UserLoginVo", userLoginVo);

        response.sendRedirect("polo"); //겟
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutGet(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("UserVo", null);
        return "users/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(HttpServletRequest request, HttpServletResponse response) {
        return "users/register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerPost(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(name = "email", defaultValue = "") String email,
                             @RequestParam(name = "password", defaultValue = "") String password,
                             @RequestParam(name = "name", defaultValue = "") String name,
                             @RequestParam(name = "nickname", defaultValue = "") String nickname,
                             @RequestParam(name = "contact", defaultValue = "") String contact
    )

            throws SQLException, IOException {
        UserRegisterVo userRegisterVo = new UserRegisterVo(email,password,name,nickname,contact);
        if(userRegisterVo.getEmail()==null){
            response.sendRedirect("/register?result=falues");
        }else {
            UserRegisterResult userRegisterResult = this.userService.register(userRegisterVo);
            request.getSession().setAttribute("UserRegisterResult", userRegisterResult);
            request.getSession().setAttribute("UserRegisterVo", userRegisterVo);
            response.sendRedirect("/register?result="+userRegisterResult.name().toLowerCase());
        }

    }


//    @ExceptionHandler
//    public void hendleException(HttpServletResponse response, Exception exception) {
//        try {
//            response.getWriter().print(exception.getMessage());
//        } catch (Exception ignored) {
//        }
//    }

}
