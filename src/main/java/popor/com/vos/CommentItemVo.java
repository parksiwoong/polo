package popor.com.vos;

public class CommentItemVo {
    private final String writer;
    private final String text;
    private final String writtenAt;

    public CommentItemVo(String writer, String text, String writtenAt) {
        this.writer = writer;
        this.text = text;
        this.writtenAt = writtenAt;
    }

    public String getWriter() {
        return writer;
    }

    public String getText() {
        return text;
    }

    public String getWrittenAt() {
        return writtenAt;
    }
}
