package popor.com.vos.bbs;

import popor.com.interfaces.IBoardIdImpl;

public class BoardReadVo implements IBoardIdImpl {
    private final int articleId;
    private final String id;
    private final String page;

    public BoardReadVo(String articleId, String id, String page) {
        int articleIdNum;
        try {
            articleIdNum = Integer.parseInt(articleId);
        } catch (NumberFormatException ignored) {
            articleIdNum = -1;
        }
        this.articleId = articleIdNum;
        this.id = id;
        this.page = page;
    }

    public int getArticleId() {
        return articleId;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getPage() {
        return page;
    }
}
