package popor.com.vos;

import popor.com.utility.Sha512;

public class ResetPasswordVo {
    private final String password;
    private final String hashedPassword;
    private final String key;

    public ResetPasswordVo(String password, String key) {
        this.password = password;
        this.hashedPassword = Sha512.hash(this.password);
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getKey() {
        return key;
    }
}
