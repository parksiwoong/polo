package popor.com.vos.users;

public class UserResetVo {
    private final String email;
    private final String contact;


    public UserResetVo(String email, String contact) {
        this.email = email;
        this.contact = contact;
    }


    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

}
