package popor.com.vos.bbs;

import popor.com.interfaces.IBoardIdImpl;

public class BoardWriteVo implements IBoardIdImpl {
    private final String id;
    private final String title;
    private final String text;

    public BoardWriteVo(String id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
