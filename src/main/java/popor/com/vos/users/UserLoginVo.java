package popor.com.vos.users;

import javax.servlet.http.HttpSession;

public class UserLoginVo {
    private final String email;
    private final String password;
    private final HttpSession session;

    public UserLoginVo(String email, String password, HttpSession session) {
        this.email = email;
        this.password = password;
        this.session = session;
    }


    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public HttpSession getSession() {
        return this.session;
    }
}
