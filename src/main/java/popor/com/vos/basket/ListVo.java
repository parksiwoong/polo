package popor.com.vos.basket;

import popor.com.interfaces.IBoardIdImpl;
import popor.com.vos.bbs.BoardVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListVo implements IBoardIdImpl {
    private  final String id;
    private  final int page;

    public ListVo(String id, int page){
        this.id = id;
        this. page = page;
    }
    public ListVo(String id, String page){
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




    public int selectTotalArticleCount(Connection connection, ListVo listVo) throws
            SQLException {
        int count;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(`article_index`) AS `count` FROM `popor_items` WHERE `board_id` = ?")) {
            preparedStatement.setString(1, listVo.getId());
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }

}
