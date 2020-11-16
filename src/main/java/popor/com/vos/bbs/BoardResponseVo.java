package popor.com.vos.bbs;

import popor.com.enums.bbs.BoardResponseResult;

import java.util.ArrayList;

public class BoardResponseVo {
    private final BoardResponseResult boardResponseResult;
    private final ArrayList<ArticleVo> articles;
    private final int requestPage; // 요청한 페이지 번호
    private final int maxPage;     // 게시글 개수에 의거한 최대 페이지 번호
    private final int startPage;   // 화면에 표시할 첫 페이지 번호
    private final int endPage;     // 화면에 표시할 끝 페이지 번호
    private final boolean isSearchResult;

    public BoardResponseVo(BoardResponseResult boardResponseResult, ArrayList<ArticleVo> articles, int requestPage, int maxPage, int startPage, int endPage, boolean isSearchResult) {
        this.boardResponseResult = boardResponseResult;
        this.articles = articles;
        this.requestPage = requestPage;
        this.maxPage = maxPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.isSearchResult = isSearchResult;
    }

    public BoardResponseResult getBoardResponseResult() {
        return boardResponseResult;
    }

    public ArrayList<ArticleVo> getArticles() {
        return articles;
    }

    public int getRequestPage() {
        return requestPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public boolean isSearchResult() {
        return isSearchResult;
    }
}
