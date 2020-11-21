package popor.com.daos.productDaos;


import org.springframework.stereotype.Repository;
import popor.com.vos.item.AddColorVo;
import popor.com.vos.item.AddSizeVo;
import popor.com.vos.item.AddVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ItemDao {
    public void insertItem(Connection connection, AddVo addVo) throws
            SQLException {
        String query = "" +
                "INSERT INTO `tldnd8989`.`items` (`item_name`, `item_price`)\n" +
                "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, addVo.getName());
            preparedStatement.setInt(2, addVo.getPrice());
            preparedStatement.execute();
        }
    }

    public void insertItemColor(Connection connection, AddColorVo addColorVo) throws
            SQLException {
        String query = "" +
                "INSERT INTO `tldnd8989`.`item_colors` (`item_index`, `color_name`, `color_price_variation`)\n" +
                "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, addColorVo.getItemIndex());
            preparedStatement.setString(2, addColorVo.getColorName());
            preparedStatement.setInt(3, addColorVo.getVariation());
            preparedStatement.execute();
        }
    }

    public void insertItemSize(Connection connection, AddSizeVo addSizeVo) throws
            SQLException {
        String query = "" +
                "INSERT INTO `tldnd8989`.`item_sizes` (`item_index`, `size_name`, `size_price_variation`)\n" +
                "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, addSizeVo.getItemIndex());
            preparedStatement.setString(2, addSizeVo.getSizeName());
            preparedStatement.setInt(3, addSizeVo.getVariation());
            preparedStatement.execute();
        }
    }
}