package popor.com.vos.users_members;

public class FindEmailVo {

    private final String name;
    private final String contact;

    public FindEmailVo(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}


