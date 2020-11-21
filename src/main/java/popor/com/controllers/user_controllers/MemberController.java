package popor.com.controllers.user_controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import popor.com.enums.users_members.EmailFindResult;
import popor.com.enums.users_members.FindPasswordResult;
import popor.com.services.userServices.UserService;
import popor.com.utility.EmailFindResultContainer;
import popor.com.vos.ResetPasswordVo;
import popor.com.vos.users_members.FindEmailVo;
import popor.com.vos.users_members.FindPasswordVo;
import popor.com.enums.users_members.ResetPasswordResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller
@RequestMapping
public class MemberController {
    private UserService userService;

    @Autowired
    public MemberController(UserService userService) {
        this.userService = userService;
    }
//    @RequestMapping(value = "find_id", method = RequestMethod.GET)
//    public String


    @RequestMapping(value = "/select_email", method = RequestMethod.GET)
    public String idResetGet(HttpServletRequest request, HttpServletResponse response){
        return "users_members/select_email";
    }

    @RequestMapping(value = "/select_email",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findEmailPost(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name = "name", defaultValue = "") String name,
                                @RequestParam(name = "contact", defaultValue = "") String contact)
            throws SQLException {
        FindEmailVo findEmailVo = new FindEmailVo(name, contact);
        EmailFindResultContainer emailFindResultContainer = this.userService.findEmail(findEmailVo);
//        response.getWriter().print(emailFindResultContainer.getFindEmailResult().name());
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("result", emailFindResultContainer.getFindEmailResult().name().toLowerCase());
        if (emailFindResultContainer.getFindEmailResult() == EmailFindResult.SUCCESS) {
            jsonResponse.put("email", emailFindResultContainer.getEmail());
        }
        return jsonResponse.toString(4);
//        request.getSession().setAttribute("result", emailFindResultContainer.getFindEmailResult().name());
    }

    @RequestMapping(value = "/find_password", method = RequestMethod.GET)
    public String findPasswordGet(HttpServletRequest request, HttpServletResponse response) {
        return "users_members/find_password";
    }

    @RequestMapping(value = "/find_password", method = RequestMethod.POST)
    public String findPasswordPost(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(name = "email", defaultValue = "") String email,
                                   @RequestParam(name = "name", defaultValue = "") String name)
            throws SQLException {
        FindPasswordVo findPasswordVo = new FindPasswordVo(email,name);
        FindPasswordResult findPasswordResult =this.userService.findPassword(findPasswordVo);
        return String.format("redirect:/find_password?result=%s", findPasswordResult.name().toLowerCase());
    }
    @RequestMapping(value = "/email_sent", method = RequestMethod.GET)
    public String emailSentGet(HttpServletRequest request, HttpServletResponse response) {
        return "users_members/email_sent";
    }

    @RequestMapping(value = "/resets_password", method = RequestMethod.GET)
    public String resetPasswordGet(HttpServletRequest request, HttpServletResponse response) {
        return "users_members/resets_password";
    }
    @RequestMapping(value = "/resets_password", method = RequestMethod.POST)
    public String resetPasswordPost(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(name = "password", defaultValue = "") String password,
                                    @RequestParam(name = "key", defaultValue = "") String key) throws
            SQLException {
        ResetPasswordVo resetPasswordVo = new ResetPasswordVo(password, key);
        ResetPasswordResult resetPasswordResult = this.userService.resetPassword(resetPasswordVo);
        return String.format("redirect:/resets_password?result=%s", resetPasswordResult.name().toLowerCase());
    }
    @RequestMapping(value = "/reset_success", method = RequestMethod.GET)
    public String resetSuccessGet(HttpServletRequest request, HttpServletResponse response) { return "users_members/reset_success";}

}


