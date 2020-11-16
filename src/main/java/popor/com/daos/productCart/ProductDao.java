package popor.com.daos.productCart;

import org.springframework.stereotype.Repository;
import popor.com.vos.ProductVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDao {
    // 상품정보 db랑 연동
    public ProductVo productVo(Connection connection, ProductVo productVo)
            throws SQLException {
                productVo = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "SELECT `popor_product_id`        AS `proNumber`, " +
                "       `popor_product_name`      AS `proName`, " +
                "       `popor_price`             AS `proPrice`, " +
                "       `popor_url`               AS `proUrl`, " +
                "       `popor_description`       AS `proText`" +
                "FROM `tldnd8989`.`popor_product` " +
                "LIMIT 1")) {
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    productVo = new ProductVo(
                            resultSet.getInt("proNumber"),
                            resultSet.getString("proName"),
                            resultSet.getInt("proPrice"),
                            resultSet.getString("proUrl"),
                            resultSet.getString("proText"));

                }
            }
        }
        return productVo;
    }

     public  List<ProductDao> listProduct() {


    return listProduct(); }
}
//

//    public ProductVo selectProductVo(Connection connection, ProductVo productVo)
//            throws SQLException {
//        ProductVo selectProductVo = productVo;
//            try(PreparedStatement preparedStatement = connection.prepareStatement("" +
//                    "SELECT `product_number` AS `proNum` " +
//                    "FROM `tldnd8989`.`popor_product` " +
//                    "WHERE `board_id` = ?")) {
//                preparedStatement.setString(1, boardIdImpl.getId());
//                preparedStatement.executeQuery();
//                try (ResultSet resultSet = preparedStatement.getResultSet()) {
//                    while (resultSet.next()) {
//                        boardLevelVo = new BoardLevelVo(
//                                resultSet.getInt("boardReadLevel"),
//                                resultSet.getInt("boardWriteLevel")
//                        );
//                    }
//                }
//            }
//        return selectProductVo;
//    }



//        Connection con = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            con = ConnectionFactory.getConnection();
//
//            String query = "insert into "
//                    + "product(code, name, price) "
//                    + "values(?, ?, ?)";
//
//            pstmt = con.prepareStatement(query);
//
//            int index = 1;
//            pstmt.setString(index++, Integer.toString(list.size()+1));
//            pstmt.setString(index++, product.getName());
//            pstmt.setInt(index++, product.getPrice());
//            pstmt.executeUpdate();
//
//        } finally {
//            ConnectionFactory.close(pstmt, con);
//        }
//    }

