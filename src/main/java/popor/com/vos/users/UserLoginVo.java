package popor.com.vos.users;

import javax.servlet.http.HttpServletRequest;
import popor.com.utility.Sha512;
public class UserLoginVo {
    private final String email;
    private final String password;
    private final String hashedPassword;
    private final HttpServletRequest request;


    public UserLoginVo(String email, String password, HttpServletRequest request) {
        this.email = email;
        this.password = password;
        this.hashedPassword = Sha512.hash(this.password);
        this.request = request;

    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public HttpServletRequest getRequest() { return request; }

}
