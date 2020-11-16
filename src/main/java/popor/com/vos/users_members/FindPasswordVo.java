package popor.com.vos.users_members;

public class FindPasswordVo {
    private final String email;
    private final String name;

    public FindPasswordVo(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
