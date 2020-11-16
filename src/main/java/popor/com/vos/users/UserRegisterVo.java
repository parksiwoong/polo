package popor.com.vos.users;

public class UserRegisterVo {
    private final String email;
    private final String password;
    private final String name;
    private final String nickname;
    private final String contact;

    public UserRegisterVo(String email, String password, String name, String nickname, String contact) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.contact = contact;

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {return nickname;}

    public String getContact() {
        return contact;
    }
}