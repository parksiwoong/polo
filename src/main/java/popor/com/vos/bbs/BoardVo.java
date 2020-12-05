package popor.com.vos.bbs;

import popor.com.interfaces.IBoardIdImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class BoardVo implements IBoardIdImpl {
            private  final String id;
             private  final int page;

        public BoardVo(String id, int page){
            this.id = id;
            this. page = page;
        }
        public BoardVo(String id, String page){
            this.id = id;
            int pageNum;
            try{
                pageNum = Integer.parseInt(page);
            }catch (NumberFormatException ignored){
            pageNum = 1;
        }
        this.page = pageNum;
    }
    @Override
    public String getId() {
        return id;
    }
    public int getPage() {
        return page;
    }




    public int selectTotalArticleCount(Connection connection, BoardVo boardVo) throws
            SQLException {
        int count;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(`article_index`) AS `count` FROM `popor_articles` WHERE `board_id` = ?")) {
            preparedStatement.setString(1, boardVo.getId());
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }

}
