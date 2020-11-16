package popor.com.daos.userDaos;

import org.springframework.stereotype.Repository;
import popor.com.vos.ResetPasswordVo;
import popor.com.vos.users.UserVo;
import popor.com.vos.users_members.FindPasswordVo;
import popor.com.vos.users_members.FindIdVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class User_MemberDao {

    public UserVo selectUser(Connection connection, FindIdVo findIdVo) throws
            SQLException {
        UserVo userVo =null;
              try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "select `user_index` as `userIndex`," +
                "   `user_email` as `userEmail`" +
                "from `tldnd8989`.`popor_users`" +
                "where `user_name` =?" +
                "and `user_contact`=?" +
                "limit 1")) {
            preparedStatement.setString(1, findIdVo.getName());
            preparedStatement.setString(2, findIdVo.getContact());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next())
              userVo = new UserVo(
                        resultSet.getInt("userIndex"),
                        resultSet.getString("user")
                        );
            }
        }return userVo;
    }



    public UserVo selectUser(Connection connection, FindPasswordVo findPasswordVo) throws
            SQLException {
        UserVo userVo = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("" +
                        "select `user_index`    as `userIndex`,\n" +
                        "       `user_email`    as `userEmail`,\n" +
                        "       `user_password` as `userPassword`,\n" +
                        "       `user_name`     as `userName`\n" +
                        "from `tldnd8989`.`popor_users`\n" +
                        "where `user_email` = ?\n" +
                        "  and `user_name` = ?\n" +
                        "LIMIT 1")) {
            preparedStatement.setString(1, findPasswordVo.getEmail());
            preparedStatement.setString(2, findPasswordVo.getName());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userVo = new UserVo(
                            resultSet.getInt("userIndex"),
                            resultSet.getString("userEmail"),
                            resultSet.getString("userPassword"),
                            resultSet.getString("userName")
                    );
                }
            }
        }
        return userVo;
    }

    public void insertPasswordkey(Connection connection, FindPasswordVo findPasswordVo, String key)
            throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "INSERT INTO `tldnd8989`.`popor_password_keys` (`user_email`, \n" +
                "                                               `key_value`, \n" +
                "                                               `key_valid_until`)\n" +
                "VALUES (?, ?, DATE_ADD(NOW(), INTERVAL 10 MINUTE))")) {
            preparedStatement.setString(1, findPasswordVo.getEmail());
            preparedStatement.setString(2, key);
            preparedStatement.execute();
        }
    }

    public String selectPasswordEmail(Connection connection, ResetPasswordVo resetPasswordVo) throws SQLException {
        String email = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "select `user_email` from `tldnd8989`.`popor_password_keys` where `key_index` =? and `key_valid_until` > now()")) {
            preparedStatement.setString(1, resetPasswordVo.getKey());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    email = resultSet.getString("userEamil");
                }
            }
        }
        return email;
    }

    public void updatePassword(Connection connection, ResetPasswordVo resetPasswordVo, String email) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "UPDATE `tldnd8989`.`popor_users` SET `user_password` = ? WHERE `user_email` = ?")) {
            preparedStatement.setString(1, resetPasswordVo.getPassword());
            preparedStatement.setString(2, email);
            preparedStatement.execute();
        }
    }

}
