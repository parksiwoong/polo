package popor.com.vos;

public class CommentVo {
    private final int articleId;
    private final String text;

    public CommentVo(String articleId, String text) {
        int articleIdNum;
        try {
            articleIdNum = Integer.parseInt(articleId);
        } catch (NumberFormatException ignored) {
            articleIdNum = -1;
        }
        this.articleId = articleIdNum;
        this.text = text;//
    }

    public int getArticleId() {
        return articleId;
    }

    public String getText() {
        return text;
    }
}
