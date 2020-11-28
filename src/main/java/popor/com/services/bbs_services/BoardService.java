package popor.com.services.bbs_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import popor.com.daos.bbsDaos.BoardDao;
import popor.com.enums.bbs.BoardReadResult;
import popor.com.enums.bbs.BoardResponseResult;
import popor.com.enums.bbs.BoardWriteResult;
import popor.com.vos.*;
import popor.com.vos.bbs.*;
import popor.com.vos.users.UserVo;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class BoardService {
    private final BoardDao boardDao;
    private final DataSource dataSource;

    @Autowired
    public BoardService(BoardDao boardDao,  DataSource dateSource){
        this.boardDao = boardDao;
        this.dataSource = dateSource;
    }

    public BoardResponseVo getArticles(HttpSession session, BoardVo boardVo) throws
            SQLException {
        BoardResponseVo boardResponseVo;
        try (Connection connection = this.dataSource.getConnection()) {
            BoardLevelVo boardLevelVo = this.boardDao.selectBoardLevel(connection, boardVo);  //qna 게시판 넘겸받고
            if (boardLevelVo == null) {  //권한에관한 vo
                // id 와 일치하는 게시판 없음
                boardResponseVo = new BoardResponseVo(BoardResponseResult.NO_MATCHING_ID, null, 0, 0, 0, 0, false);
            } else {
                int userLevel = this.getUserLevel(session);
                if (boardLevelVo.getReadLevel() < userLevel) {
                    // 권한 없음
                    boardResponseVo = new BoardResponseVo(BoardResponseResult.NOT_AUTHORIZED, null, 0, 0, 0, 0, false);
                } else {
                    // 읽기 가능
                    ArrayList<ArticleVo> articles = this.boardDao.selectArticles(connection, boardVo);
                    int requestPage = boardVo.getPage();
                    int total = this.boardDao.selectTotalArticleCount(connection, boardVo);
                    int maxPage = total % 10 == 0 ? total / 10 : (int) (Math.floor((double) total / 10) + 1);
                    int startPage = requestPage < 6 ? 1 : requestPage - 5;
                    int endPage = Math.min(maxPage, requestPage + 5);
                    boardResponseVo = new BoardResponseVo(BoardResponseResult.OKAY, articles, requestPage, maxPage, startPage, endPage, false);
                }
            }
        }
        return boardResponseVo;
    }
    public BoardResponseVo search(HttpSession session, SearchVo searchVo) throws
            SQLException {
        BoardResponseVo boardResponseVo;
        try (Connection connection = this.dataSource.getConnection()) {
            BoardLevelVo boardLevelVo = this.boardDao.selectBoardLevel(connection, searchVo);
            if (boardLevelVo == null) {
                // id 와 일치하는 게시판 없음
                boardResponseVo = new BoardResponseVo(BoardResponseResult.NO_MATCHING_ID, null, 0, 0, 0, 0, true);
            } else {
                int userLevel = this.getUserLevel(session);
                if (boardLevelVo.getReadLevel() < userLevel) {
                    // 권한 없음
                    boardResponseVo = new BoardResponseVo(BoardResponseResult.NOT_AUTHORIZED, null, 0, 0, 0, 0, true);
                } else {
                    // 읽기 가능
                    ArrayList<ArticleVo> articles = this.boardDao.selectArticles(connection, searchVo);
                    int requestPage = searchVo.getPage();
                    int total = this.boardDao.selectSearchArticleCount(connection, searchVo);
                    int maxPage = total % 10 == 0 ? total / 10 : (int) (Math.floor((double) total / 10) + 1);
                    int startPage = requestPage < 6 ? 1 : requestPage - 5;
                    int endPage = Math.min(maxPage, requestPage + 5);
                    boardResponseVo = new BoardResponseVo(BoardResponseResult.OKAY, articles, requestPage, maxPage, startPage, endPage, true);
                }
            }
        }
        return boardResponseVo;
    }


    public BoardWriteResult write(UserVo userVo, BoardWriteVo boardWriteVo) throws
            SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            if (userVo == null) {
                userVo = new UserVo(0, "nan", "nan", "비회원", "비회원", "nan", 10);
            }
            BoardLevelVo boardLevelVo = this.boardDao.selectBoardLevel(connection, boardWriteVo);
            if (boardLevelVo == null)
                return BoardWriteResult.NO_MATCHING_ID; // 게시판 없음
            if (userVo.getLevel() > boardLevelVo.getWriteLevel()) {
                return BoardWriteResult.NOT_ALLOWED; // 권한 없음
            }
            this.boardDao.insertArticle(connection, userVo, boardWriteVo);
            return BoardWriteResult.SUCCESS;
        }
    }

    public BoardReadResponseVo read(UserVo userVo, BoardReadVo boardReadVo) throws
            SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            if (userVo == null) {
                userVo = new UserVo(0, "nan", "nan", "비회원", "비회원", "nan",  10);
            }
            BoardLevelVo boardLevelVo = this.boardDao.selectBoardLevel(connection, boardReadVo);
            if (boardLevelVo == null)
                return new BoardReadResponseVo(BoardReadResult.NO_MATCHING_ID, null, null, null, null);
            if (userVo.getLevel() > boardLevelVo.getWriteLevel())
                return new BoardReadResponseVo(BoardReadResult.NOT_ALLOWED, null, null, null, null);

            ArticleVo articleVo = this.boardDao.selectArticle(connection, boardReadVo.getArticleId());
            if (articleVo == null)
                return new BoardReadResponseVo(BoardReadResult.NO_MATCHING_ARTICLE_ID, null, null, null, null);

            ArrayList<CommentItemVo> comments = this.boardDao.selectComments(connection, boardReadVo.getArticleId());
            return new BoardReadResponseVo(BoardReadResult.SUCCESS,
                    articleVo.getTitle(),
                    articleVo.getContent(),
                    articleVo.getWriter(), comments);
        }
    }

    public void writeComment(UserVo userVo, CommentVo commentVo) throws
            SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            this.boardDao.insertComment(connection, userVo, commentVo);
        }
    }

    public void deleteArticle(UserVo userVo, int articleId) throws
            SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            ArticleVo articleVo = this.boardDao.selectArticle(connection, articleId);
            boolean delete;
            if (userVo.getLevel() == 1) {
                delete = true;
            } else if (userVo.getLevel() < 10 && userVo.getNickname().equals(articleVo.getWriter())) {
                delete = true;
            } else {
                delete = false;
            }
            if (delete) {
                this.boardDao.deleteArticle(connection, articleId);
            }
        }
    }
    public int uploadImage(String imageData)throws SQLException{
        try(Connection connection = this.dataSource.getConnection()){
            this.boardDao.insertImage(connection, imageData);
            return  this.boardDao.selectLastIndex(connection);
        }
    }
    public byte[] downloadImage(int id)throws
            SQLException, IOException {
        try(Connection connection = this.dataSource.getConnection()){
            String imageData = this.boardDao.selectImage(connection, id).split(",")[1];
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(imageData);
            BufferedImage image= ImageIO.read(new ByteArrayInputStream(imageBytes));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
    private int getUserLevel(HttpSession session) {
        Object userVoObject = session.getAttribute("UserVo");
        UserVo userVo = null;
        if (userVoObject instanceof UserVo) {
            userVo = (UserVo) userVoObject;
        }

        int userLevel;
        if (userVo == null) {
            userLevel = 10;
        } else {
            userLevel = userVo.getLevel();
        }
        return userLevel;
    }
}