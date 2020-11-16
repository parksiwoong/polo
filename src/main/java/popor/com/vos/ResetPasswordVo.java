package popor.com.vos;

public class ResetPasswordVo {
    private final String password;
    private final String key;

    public ResetPasswordVo(String password, String key) {
        this.password = password;
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public String getKey() {
        return key;
    }
}
