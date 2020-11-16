package popor.com.vos.users_members;

import javax.servlet.http.HttpSession;

public class FindIdVo {
    private final String name;
    private final String contact;
    private final HttpSession session;

    public FindIdVo(String name, String contact, HttpSession session) {
        this.name = name;
        this.contact = contact;
        this.session = session;
    }

    public HttpSession getSession() {return session;}

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}


