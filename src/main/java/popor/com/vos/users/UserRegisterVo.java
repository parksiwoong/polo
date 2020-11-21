package popor.com.vos.users;

import popor.com.utility.Sha512;

public class UserRegisterVo {
    private final String email;
    private final String password;
    private final String hashedPassword;
    private final String name;
    private final String nickname;
    private final String contact;
    private final boolean isNormalized;
    private static final String EMAIL_REGEX = "^(?=.{4,100}.$)([0-9a-zA-Z][0-9a-zA-Z\\-_.]*[0-9a-zA-Z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$";

    public UserRegisterVo(String email, String password, String name, String nickname, String contact) {
        if (email.matches(UserRegisterVo.EMAIL_REGEX)){
            this.isNormalized=true;
            this.email = email;
            this.password = password;
            this.hashedPassword = Sha512.hash(this.password);
            this.name = name;
            this.nickname = nickname;
            this.contact = contact;

        }else {
            this.email = null;
            this.password = null;;
            this.hashedPassword = null;
            this.name = null;
            this.nickname = null;
            this.contact = null;
            this.isNormalized=false;

        }


    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {return nickname;}

    public String getContact() {
        return contact;
    }

    public boolean isNormalized() { return isNormalized; }
}