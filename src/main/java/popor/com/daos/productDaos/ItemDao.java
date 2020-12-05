package popor.com.daos.productDaos;


import org.springframework.stereotype.Repository;
import popor.com.vos.item.AddVo;
import popor.com.vos.item.ItemVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemDao {

    public long totalCount(Connection connection) {
        String query = "SELECT count(*) " +
                "FROM `popor_items`";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.execute();

            try(ResultSet rs = statement.getResultSet()){
                rs.next();

                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ItemVo> list(Connection connection, int offset, int limit) {
        String query = "SELECT * " +
                "FROM `popor_items` " +
                "ORDER BY item_index DESC " +
                "LIMIT " + offset + ", " + limit;
        List<ItemVo> list = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.execute();

            try(ResultSet rs = statement.getResultSet()){
                while(rs.next()) {
                    ItemVo itemVo = new ItemVo();
                    itemVo.setIndex(rs.getInt("item_index"));
                    itemVo.setName(rs.getString("item_name"));
                    itemVo.setPrice(rs.getInt("item_price"));
                    itemVo.setColor(rs.getString("item_color"));
                    itemVo.setSize(rs.getString("item_size"));
                    itemVo.setFileName(rs.getString("item_fileName"));

                    list.add(itemVo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    
    public long insertItem(Connection connection, AddVo addVo, String fileName) throws
            SQLException {
        String query = "" +
                "INSERT INTO `popor_items` (`item_name`, `item_price`,`item_color`,`item_size`,`item_fileName`)\n" +
                "VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, addVo.getName());
            preparedStatement.setInt(   2, addVo.getPrice());
            preparedStatement.setString(3, addVo.getColor());
            preparedStatement.setString(4, addVo.getSize());
            preparedStatement.setString(5, fileName);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }

    //---------------------------------------
//    public void insertImage(Connection connection, String imageData) throws SQLException {
//        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
//                "insert into `popor_images`(`image_data`)\n" +
//                "values (?)")) {
//            preparedStatement.setString(1, imageData);
//            preparedStatement.execute();
//        }
//    }
}