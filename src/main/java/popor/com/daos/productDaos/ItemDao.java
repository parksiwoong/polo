package popor.com.daos.productDaos;


import org.springframework.stereotype.Repository;
import popor.com.vos.item.AddVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ItemDao {
    public void insertItem(Connection connection, AddVo addVo) throws
            SQLException {
        String query = "" +
                "INSERT INTO `tldnd8989`.`popor_items` (`item_name`, `item_price`,`item_color`,`item_size`)\n" +
                "VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, addVo.getName());
            preparedStatement.setInt(   2, addVo.getPrice());
            preparedStatement.setString(3, addVo.getColor());
            preparedStatement.setString(4, addVo.getSize());
            preparedStatement.execute();
        }
    }

    //---------------------------------------
//    public void insertImage(Connection connection, String imageData) throws SQLException {
//        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
//                "insert into `tldnd8989`.`popor_images`(`image_data`)\n" +
//                "values (?)")) {
//            preparedStatement.setString(1, imageData);
//            preparedStatement.execute();
//        }
//    }
}