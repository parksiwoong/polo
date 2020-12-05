package popor.com.daos.productDaos;


import org.springframework.stereotype.Repository;
import popor.com.vos.basket.AddVo;
import popor.com.vos.basket.BasketListVo;
import popor.com.vos.basket.BasketVo;
import popor.com.vos.item.ItemVo;
import popor.com.vos.users.UserVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BasketDao {

    public List<BasketListVo> list(Connection connection, int userIndex) {
        String query = "SELECT * "+
                "FROM baskets b "+
                "INNER JOIN popor_items i "+
                "ON b.item_index = i.item_index "+
                "WHERE b.user_index = " + userIndex;
        List<BasketListVo> list = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.execute();

            try(ResultSet rs = statement.getResultSet()){
                while(rs.next()) {
                    BasketListVo basketListVo = new BasketListVo();
                    basketListVo.setIndex(rs.getInt("basket_index"));
                    basketListVo.setDatetime(rs.getDate("basket_datetime"));
                    basketListVo.setCount(rs.getInt("basket_count"));

                    ItemVo itemVo = new ItemVo();
                    itemVo.setIndex(rs.getInt("item_index"));
                    itemVo.setName(rs.getString("item_name"));
                    itemVo.setPrice(rs.getInt("item_price"));
                    itemVo.setColor(rs.getString("item_color"));
                    itemVo.setSize(rs.getString("item_size"));
                    itemVo.setFileName(rs.getString("item_fileName"));
                    basketListVo.setItemVo(itemVo);

                    list.add(basketListVo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void insertBasket(Connection connection, UserVo userVo, AddVo addVo) throws
            SQLException {
        String query = "" +
                "INSERT INTO `baskets` (`user_index`,\n" +
                "                                   `item_index`,\n" +
                "                                   `basket_count`)\n" +
                "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userVo.getIndex());
            preparedStatement.setInt(2, addVo.getItemIndex());
            preparedStatement.setInt(3, addVo.getCount());
            preparedStatement.execute();
        }
    }

    public void updateBasket(Connection connection, UserVo userVo, AddVo addVo) throws
            SQLException {
        String query = "" +
                "UPDATE `baskets`\n" +
                "SET `basket_count`    = `basket_count` + ?,\n" +
                "    `basket_datetime` = CURRENT_TIMESTAMP()\n" +
                "WHERE `user_index` = ?\n" +
                "  AND `item_index` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, addVo.getCount());
            preparedStatement.setInt(2, userVo.getIndex());
            preparedStatement.setInt(3, addVo.getItemIndex());
            preparedStatement.execute();
        }
    }

    public ArrayList<BasketVo> selectBasket(Connection connection, UserVo userVo) throws
            SQLException {
        ArrayList<BasketVo> baskets = new ArrayList<>();
        String query = "" +
                "SELECT `item`.`item_name`              AS `itemName`,\n" +
                "       `item`.`item_price`             AS `itemPrice`,\n" +
                "       `color`.`color_name`            AS `colorName`,\n" +
                "       `color`.`color_price_variation` AS `colorVariation`,\n" +
                "       `size`.`size_name`              AS `sizeName`,\n" +
                "       `size`.`size_price_variation`   AS `sizeVariation`,\n" +
                "       `basket`.`basket_datetime`      AS `basketDateTime`,\n" +
                "       `basket`.`basket_count`         AS `basketCount`\n" +
                "FROM `baskets` AS `basket`\n" +
                "         INNER JOIN `items` AS `item` ON `item`.`item_index` = `basket`.`item_index`\n" +
                "         INNER JOIN `item_colors` AS `color` ON `color`.`item_index` = `basket`.`item_index`\n" +
                "         INNER JOIN `item_sizes` AS `size` ON `size`.`item_index` = `basket`.`item_index`\n" +
                "WHERE `user_index` = ?\n" +
                "GROUP BY `basket`.`basket_index`";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userVo.getIndex());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    BasketVo basketVo = new BasketVo(
                            resultSet.getString("itemName"),
                            resultSet.getInt("itemPrice"),
                            resultSet.getString("colorName"),
                            resultSet.getInt("colorVariation"),
                            resultSet.getString("sizeName"),
                            resultSet.getInt("sizeVariation"),
                            resultSet.getDate("basketDateTime"),
                            resultSet.getInt("basketCount")
                    );
                    baskets.add(basketVo);
                }
            }
        }
        return baskets;
    }

    public int selectBasket(Connection connection, UserVo userVo, AddVo addVo) throws
            SQLException {
        int count;
        String query = "" +
                "SELECT COUNT(`basket_index`) AS `count`\n" +
                "FROM `baskets`\n" +
                "WHERE `user_index` = ?\n" +
                "  AND `item_index` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userVo.getIndex());
            preparedStatement.setInt(2, addVo.getItemIndex());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }

    public void delete(Connection connection, Integer basketIndex) {
        String query = "" +
                "DELETE " +
                "FROM `baskets`\n" +
                "WHERE `basket_index` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, basketIndex);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
