package popor.com.vos.bbs;

import popor.com.enums.bbs.BoardReadResult;
import popor.com.vos.CommentItemVo;

import java.util.ArrayList;

public class BoardReadResponseVo {

    private final BoardReadResult boardReadResult;  // 읽기 결과
    private final String title;                     // 제목
    private final String text;                      // 내용
    private final String writer;                    // 작성자
    private final ArrayList<CommentItemVo> comments;// 댓글(들)

    public BoardReadResponseVo(BoardReadResult boardReadResult, String title, String text, String writer, ArrayList<CommentItemVo> comments) {
        this.boardReadResult = boardReadResult;
        this.title = title;
        this.text = text;
        this.writer = writer;
        this.comments = comments;
    }

    public BoardReadResult getBoardReadResult() {
        return boardReadResult;
    }

    public String getTitle() {
        return title;
    }

    public String getText() { return text; }

    public String getWriter() {
        return writer;
    }

    public ArrayList<CommentItemVo> getComments() {
        return comments;
    }
}
