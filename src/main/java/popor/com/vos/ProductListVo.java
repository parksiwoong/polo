//
//package popor.com.vos;
//
//import com.mysql.cj.protocol.a.authentication.MysqlNativePasswordPlugin;
//import org.apache.ibatis.session.SqlSession;
//
//import java.util.List;
//
//public class ProductListVo {
//
//    public List<ProductListVo> listProduct() {
//        SqlSession sqlSession = MybatisManager.geInstanse().openSesstion();
//        List<ProductListVo> list
//                = sqlSession.selectList("listProduct.list_listProduct");
//        sqlSession.close();
//        return list;
//     }
//}
