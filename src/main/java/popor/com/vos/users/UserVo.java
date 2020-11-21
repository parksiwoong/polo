package popor.com.vos.users;

public class UserVo {
    private final int index;
    private final String email;
    private final String password;
    private final String name;
    private final String nickname;
    private final String contact;
    private final int level;
    private static final String EMAIL_REGEX = "^(?=.{4,100}.$)([0-9a-zA-Z][0-9a-zA-Z\\-_.]*[0-9a-zA-Z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$";
    private static final String PASSWORD_REGEX = "^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?]{4,100})$";



    public UserVo(int index, String email, String password, String name, String nickname, String contact, int level){
        this.index = index;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.contact = contact;
        this.level = level;

    }


    public int getIndex() {
        return this.index;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getContact() {
        return this.contact;
    }

    public int getLevel() {
        return this.level;
    }

}



