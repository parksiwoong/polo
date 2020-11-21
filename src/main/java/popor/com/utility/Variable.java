package popor.com.utility;

import popor.com.vos.users.UserVo;

import javax.servlet.http.HttpServletRequest;

public class Variable {
    private Variable(){}

    public static UserVo getUserVo(HttpServletRequest request) {
        Object userVoObject = request.getSession().getAttribute("UserVo");
        UserVo userVo = null;
        if (userVoObject instanceof UserVo) {
            userVo = (UserVo) userVoObject;
        }
        return userVo;
    }

    public static UserVo getAnonymousUserVo() {
        return new UserVo(0, "nan", "nan", "비회원", "비회원", "nan",  10);
    }

    public static boolean isSigned(HttpServletRequest request) {
        return Variable.getUserVo(request) != null;
    }
}
