package popor.com.vos.users_members;


public class SendMailVo {
    private final String title;
    private final String content;
    private final String to;

    public SendMailVo(String title, String content, String to) {
        this.title = title;
        this.content = content;
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTo() {
        return to;
    }
}
