package popor.com.vos.users;

public class UserVo {
    private final int index;
    private final String email;
    private  String password;
    private  String name;
    private  String nickname;
    private  String contact;
    private int level;


    public UserVo(int index, String email, String password, String name) {
        this.index = index;
        this.email = email;
        this.password = password;
        this.name = name;
    }


    public UserVo(int index, String email, String password, String name, String nickname, String contact, int level){
        this.index = index;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.contact = contact;
        this.level = level;

    }

    public UserVo(int index, String email){
        this.index= index;
        this.email = email;
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

