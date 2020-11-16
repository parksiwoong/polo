package popor.com.daos.bbsDaos;

import org.springframework.stereotype.Repository;
import popor.com.interfaces.IBoardIdImpl;
import popor.com.vos.*;
import popor.com.vos.bbs.ArticleVo;
import popor.com.vos.bbs.BoardLevelVo;
import popor.com.vos.bbs.BoardVo;
import popor.com.vos.bbs.BoardWriteVo;
import popor.com.vos.users.UserVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Repository
public class BoardDao {
    public int selectBoardCount(Connection connection, BoardVo boardVo) throws
            SQLException {
        int count;
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "SELECT COUNT(`board_index`) AS `count` FROM `tldnd8989`.`popor_boards` WHERE `board_id` = ?")) {
            preparedStatement.setString(1, boardVo.getId());
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }

    public BoardLevelVo selectBoardLevel(Connection connection, IBoardIdImpl boardIdImpl) throws
            SQLException {
        BoardLevelVo boardLevelVo = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "SELECT `board_read_level` AS `boardReadLevel`, `board_write_level` AS `boardWriteLevel` " +
                "FROM `tldnd8989`.`popor_boards` " +
                "WHERE `board_id` = ?")) {
            preparedStatement.setString(1, boardIdImpl.getId());
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    boardLevelVo = new BoardLevelVo(
                            resultSet.getInt("boardReadLevel"),
                            resultSet.getInt("boardWriteLevel")
                    );
                }
            }
        }
        return boardLevelVo;
    }

    public ArrayList<ArticleVo> selectArticles(Connection connection, BoardVo boardVo) throws
            SQLException {
        ArrayList<ArticleVo> articles = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "SELECT `article`.`article_index`      AS `articleIndex`, " +
                "       `user`.`user_nickname`         AS `userNickname`, " +
                "       `article`.`article_title`      AS `articleTitle`, " +
                "       `article`.`article_content`    AS `articleContent`, " +
                "       `article`.`article_written_at` AS `articleWrittenAt`, " +
                "       `article`.`article_hit`        AS `articleHit` " +
                "FROM `tldnd8989`.`popor_articles` AS `article` " +
                "         INNER JOIN `tldnd8989`.`popor_users` AS `user` ON `article`.`user_email` = `user`.`user_email` " +
                "WHERE `board_id` = ? " +     //nqa 넘겨받은 아이디
                "ORDER BY `article_index` DESC " +
                "LIMIT ?, 10")) {
            preparedStatement.setString(1, boardVo.getId()); //여기서 무슨게시판인지 받은 아이디
            preparedStatement.setInt(2, (boardVo.getPage() - 1) * 10);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    int articleIndex = resultSet.getInt("articleIndex");
                    int commentCount = this.selectCommentCount(connection, articleIndex);
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("articleWrittenAt"));
                    ArticleVo articleVo = new ArticleVo(
                            articleIndex,
                            resultSet.getString("articleTitle"),
                            resultSet.getString("articleContent"),
                            resultSet.getString("userNickname"),
                            date,
                            resultSet.getInt("articleHit"),
                            commentCount);
                    articles.add(articleVo);
                }
            }
        }
        return articles;

    }

    public ArrayList<ArticleVo> selectArticles(Connection connection, SearchVo searchVo) throws
            SQLException {
        ArrayList<ArticleVo> articles = new ArrayList<>();
        String query;
        if (searchVo.getWhat().equals("title")) {
            // 제목 기준 검색
            query = "" +
                    "SELECT `Article`.`article_index`      AS `articleIndex`, " +
                    "       `user`.`user_nickname`         AS `userNickname`, " +
                    "       `article`.`article_title`      AS `articleTitle`, " +
                    "       `article`.`article_content`    AS `articleContent`, " +
                    "       `article`.`article_written_at` AS `articleWrittenAt`, " +
                    "       `article`.`article_hit`        AS `articleHit` " +
                    "FROM `tldnd8989`.`articles` AS `article` " +
                    "         INNER JOIN `tlndd8989`.`popor_users` AS `user` ON `article`.`user_email` = `user`.`user_email` " +
                    "WHERE `board_id` = ? " +
                    "  AND REPLACE(`article_title`, ' ', '') LIKE '%" + searchVo.getKeyword() + "%' " +
                    "ORDER BY `article_index` DESC " +
                    "LIMIT ?, 10";
        } else if (searchVo.getWhat().equals("title_content")) {
            // 제목 + 내용 기준 검색
            query = "" +
                    "SELECT `article`.`article_index`      AS `articleIndex`, " +
                    "       `user`.`user_nickname`         AS `userNickname`, " +
                    "       `article`.`article_title`      AS `articleTitle`, " +
                    "       `article`.`article_content`    AS `articleContent`, " +
                    "       `article`.`article_written_at` AS `articleWrittenAt`, " +
                    "       `article`.`article_hit`        AS `articleHit` " +
                    "FROM `tldnd8989`.`popor_articles` AS `article` " +
                    "         INNER JOIN `tldnd8989`.`popor_users` AS `user` ON `article`.`user_email` = `user`.`user_email` " +
                    "WHERE `board_id` = ? " +
                    "  AND (REPLACE(`article_title`, ' ', '') LIKE '%" + searchVo.getKeyword() + "%' OR " +
                    "       REPLACE(`article_content`, ' ', '') LIKE '%" + searchVo.getKeyword() + "%') " +
                    "ORDER BY `article_index` DESC " +
                    "LIMIT ?, 10";
        } else {
            // 작성자(닉네임) 기준 검색
            query = "" +
                    "SELECT `article`.`article_index`      AS `articleIndex`, " +
                    "       `user`.`user_nickname`         AS `userNickname`, " +
                    "       `article`.`article_title`      AS `articleTitle`, " +
                    "       `article`.`article_content`    AS `articleContent`, " +
                    "       `article`.`article_written_at` AS `articleWrittenAt`, " +
                    "       `article`.`article_hit`        AS `articleHit` " +
                    "FROM `tldnd8989`.`popor_articles` AS `article` " +
                    "         INNER JOIN `tldnd8989`.`popor_users` AS `user` ON `article`.`user_email` = `user`.`user_email` " +
                    "WHERE `board_id` = ? " +
                    "  AND `user`.`user_nickname` = '" + searchVo.getKeyword() + "' " +
                    "ORDER BY `article_index` DESC " +
                    "LIMIT ?, 10";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, searchVo.getId());
            preparedStatement.setInt(2, (searchVo.getPage() - 1) * 10);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    int articleIndex = resultSet.getInt("articleIndex");
                    int commentCount = this.selectCommentCount(connection, articleIndex);
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("articleWrittenAt"));
                    ArticleVo articleVo = new ArticleVo(
                            articleIndex,
                            resultSet.getString("articleTitle"),
                            resultSet.getString("articleContent"),
                            resultSet.getString("userNickname"),
                            date,
                            resultSet.getInt("articleHit"),
                            commentCount);
                    articles.add(articleVo);
                }
            }
        }
        return articles;
    }


    public int selectSearchArticleCount(Connection connection, SearchVo searchVo) throws
            SQLException {
        int count;
        String query;
        if (searchVo.getWhat().equals("title")) {
            // 제목 기준 검색
            query = "" +
                    "SELECT COUNT(`article_index`) AS `count` " +
                    "FROM `tldnd8989`.`popor_articles` AS `article` " +
                    "WHERE `board_id` = ? " +
                    "  AND REPLACE(`article_title`, ' ', '') LIKE '%" + searchVo.getKeyword() + "%' ";
        } else if (searchVo.getWhat().equals("title_content")) {
            // 제목 + 내용 기준 검색
            query = "" +
                    "SELECT COUNT(`article_index`) AS `count` " +
                    "FROM `tldnd8989`.`popor_articles` AS `article` " +
                    "WHERE `board_id` = ? " +
                    "  AND (REPLACE(`article_title`, ' ', '') LIKE '%" + searchVo.getKeyword() + "%' OR " +
                    "       REPLACE(`article_content`, ' ', '') LIKE '%" + searchVo.getKeyword() + "%') ";
        } else {
            // 작성자(닉네임) 기준 검색
            query = "" +
                    "SELECT COUNT(`article_index`) AS `count` " +
                    "FROM `tldnd8989`.`popor_articles` AS `article` " +
                    "         INNER JOIN `tldnd8989`.`popor_users` AS `user` ON `article`.`user_email` = `user`.`user_email` " +
                    "WHERE `board_id` = ? " +
                    "  AND `user`.`user_nickname` = '" + searchVo.getKeyword() + "' ";
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, searchVo.getId());
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count; // 1이면 중복되니 0이여야 만들어지게끔 쓰려는거
    }

    public int selectTotalArticleCount(Connection connection, BoardVo boardVo) throws
            SQLException {
        int count;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(`article_index`) AS `count` FROM `tldnd8989`.`popor_articles` WHERE `board_id` = ?")) {
            preparedStatement.setString(1, boardVo.getId());
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }

    public void insertArticle(Connection connection, UserVo userVo, BoardWriteVo boardWriteVo) throws
            SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "INSERT INTO `tldnd8989`.`popor_articles` (`user_email`, " +
                                                            "`board_id`, " +
                                                            "`article_title`, " +
                                                            "`article_content`) " +
                                                            "VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, userVo.getEmail());
            preparedStatement.setString(2, boardWriteVo.getId());
            preparedStatement.setString(3, boardWriteVo.getTitle());
            preparedStatement.setString(4, boardWriteVo.getText());
            preparedStatement.execute();
        }
    }

    public ArticleVo selectArticle(Connection connection, int articleIndex) throws
            SQLException {
        ArticleVo articleVo = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "SELECT `article`.`article_index`      AS `articleIndex`, " +
                "       `user`.`user_nickname`         AS `userNickname`, " +
                "       `article`.`article_title`      AS `articleTitle`, " +
                "       `article`.`article_content`    AS `articleContent`, " +
                "       `article`.`article_written_at` AS `articleWrittenAt`, " +
                "       `article`.`article_hit`        AS `articleHit` " +
                "FROM `tldnd8989`.`popor_articles` AS `article` " +
                "         INNER JOIN `tldnd8989`.`popor_users` AS `user` ON `article`.`user_email` = `user`.`user_email` " +
                "WHERE `article_index` = ? ")) {
            preparedStatement.setInt(1, articleIndex);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    int commentCount = this.selectCommentCount(connection, articleIndex);
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("articleWrittenAt"));
                    articleVo = new ArticleVo(
                            resultSet.getInt("articleIndex"),
                            resultSet.getString("articleTitle"),
                            resultSet.getString("articleContent"),
                            resultSet.getString("userNickname"),
                            date,
                            resultSet.getInt("articleHit"),
                            commentCount);
                }
            }
        }
        return articleVo;
    }

    public void insertComment(Connection connection, UserVo userVo, CommentVo commentVo) throws
            SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `tldnd8989`.`popor_comments` (article_index, user_email, comment_text) VALUES(?, ?, ?)")) {
            preparedStatement.setInt(1, commentVo.getArticleId());
            preparedStatement.setString(2, userVo.getEmail());
            preparedStatement.setString(3, commentVo.getText());
            preparedStatement.execute();
        }
    }

    public ArrayList<CommentItemVo> selectComments(Connection connection, int articleIndex)
            throws SQLException {
        ArrayList<CommentItemVo> comments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "SELECT `user`.`user_nickname`         AS `userNickname`,\n" +
                "       `comment`.`comment_text`       AS `commentText`,\n" +
                "       `comment`.`comment_written_at` AS `commentWrittenAt`\n" +
                "FROM `tldnd8989`.`popor_comments` AS `comment`\n" +
                "         INNER JOIN `tldnd8989`.`popor_users` AS `user` ON `comment`.`user_email` = `user`.`user_email`\n" +
                "WHERE `comment`.`article_index` = ?\n" +
                "ORDER BY `comment`.`comment_index`")) {
            preparedStatement.setInt(1, articleIndex);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("commentWrittenAt"));
                    CommentItemVo commentItemVo = new CommentItemVo(
                            resultSet.getString("userNickname"),
                            resultSet.getString("commentText"),
                            date
                    );
                    comments.add(commentItemVo);
                }
            }
        }
        return comments;
    }

    private int selectCommentCount(Connection connection, int articleIndex) throws
            SQLException {
        int count;
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "SELECT COUNT(`article_index`) AS `count`\n" +
                "FROM `tldnd8989`.`popor_comments`\n" +
                "WHERE `article_index` = ?")) {
            preparedStatement.setInt(1, articleIndex);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }

    public void deleteArticle(Connection connection, int articleId) throws
            SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `tldnd8989`.`popor_articles` WHERE `article_index` = ?")) {
            preparedStatement.setInt(1, articleId);
            preparedStatement.execute();
        }
    }

//----------------------------------------------------------------------------------------------------------------------- 이미지 DAO

    public void insertImage(Connection connection, String imageData) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "insert into `tldnd8989`.`popor_images`(`image_data`)\n" +
                "values (?)")) {
            preparedStatement.setString(1, imageData);
            preparedStatement.execute();
        }
    }

    public int selectLastIndex(Connection connection) throws SQLException {
        int index = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "select last_insert_id() as `index`")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    index = resultSet.getInt("index");
                }
            }
        }
        return index;
    }

    public String selectImage(Connection connection, int id) throws SQLException {
        String imageData = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "select `image_data` as `imageData` from `tldnd8989`.`popor_images` where `image_index` = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    imageData = resultSet.getString("imageData");
                }
            }
        }
        return imageData;
    }
}





