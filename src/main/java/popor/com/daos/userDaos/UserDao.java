package popor.com.daos.userDaos;


import org.springframework.stereotype.Repository;
import popor.com.vos.users.UserLoginVo;
import popor.com.vos.users.UserRegisterVo;
import popor.com.vos.users.UserResetVo;
import popor.com.vos.users.UserVo;
import popor.com.vos.users_members.FindEmailVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    public UserVo selectUser(Connection connection, UserLoginVo userLoginVo)
            throws SQLException {
        UserVo userVo = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "SELECT `user_index`    AS `userIndex`, " +
                "       `user_email`    AS `userEmail`, " +
                "       `user_password` AS `userPassword`, " +
                "       `user_name`     AS `userName`, " +
                "       `user_nickname` AS `userNickname`, " +
                "       `user_contact`  AS `userContact`, " +
                "       `user_level`    AS `userLevel` " +
                "FROM `popor_users` " +
                "WHERE `user_email` = ? " +
                "  AND `user_password` = ? " +
                "LIMIT 1")) {
            preparedStatement.setString(1, userLoginVo.getEmail());
            preparedStatement.setString(2, userLoginVo.getHashedPassword());
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    userVo = new UserVo(
                            resultSet.getInt("userIndex"),
                            resultSet.getString("userEmail"),
                            resultSet.getString("userPassword"),
                            resultSet.getString("userName"),
                            resultSet.getString("userNickname"),
                            resultSet.getString("userContact"),
                            resultSet.getInt("userLevel"));
                }
            }
        }
        return userVo;
    }

    public UserVo selectUser(Connection connection, UserResetVo userResetVo)
            throws SQLException {
        UserVo userVo = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "SELECT `user_index`    AS `userIndex`, " +
                "       `user_email`    AS `userEmail`, " +
                "       `user_password` AS `userPassword`, " +
                "       `user_name`     AS `userName`, " +
                "       `user_nickname` AS `userNickname`, " +
                "       `user_contact`  AS `userContact`, " +
                "       `user_level`    AS `userLevel` " +
                "FROM`popor_users` " +
                "WHERE `user_email` = ? " +
                "  AND `user_contact` = ? " +
                "LIMIT 1")) {
            preparedStatement.setString(1, userResetVo.getEmail());
            preparedStatement.setString(2, userResetVo.getContact());
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    userVo = new UserVo(
                            resultSet.getInt("userIndex"),
                            resultSet.getString("userEmail"),
                            resultSet.getString("userPassword"),
                            resultSet.getString("userName"),
                            resultSet.getString("userNickname"),
                            resultSet.getString("userContact"),
                            resultSet.getInt("userLevel"));
                }
            }
        }
        return userVo;
    }

    public String selectEmail(Connection connection, FindEmailVo findEmailVo)
            throws SQLException {
        String email = null;
        String query = "SELECT `user_email` AS `userEmail`\n" +
                "FROM `popor_users`\n" +
                "WHERE `user_name` = ? AND `user_contact` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, findEmailVo.getName());
            preparedStatement.setString(2, findEmailVo.getContact());
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    email = resultSet.getString("userEmail");
                }
            }
        }
        return email;
    }
    public int selectEmailCount(Connection connection, String email)
            throws SQLException {
        int count;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(`user_index`) AS `count` FROM `popor_users` WHERE `user_email` = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }

    public int selectNicknameCount(Connection connection, String nickname)
            throws SQLException {
        int count;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(`user_index`) AS `count` FROM `popor_users` WHERE `user_nickname` = ?")) {
            preparedStatement.setString(1, nickname);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }

    public int selectContactCount(Connection connection, String contact)
            throws SQLException {
        int count;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(`user_index`) AS `count` FROM `popor_users` WHERE `user_contact` = ?")) {
            preparedStatement.setString(1, contact);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }

    public void insertUser(Connection connection, UserRegisterVo userRegisterVo)
            throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "INSERT INTO `popor_users` (user_email, user_password, user_name, user_nickname, user_contact) " +
                "VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, userRegisterVo.getEmail());
            preparedStatement.setString(2, userRegisterVo.getHashedPassword());
            preparedStatement.setString(3, userRegisterVo.getName());
            preparedStatement.setString(4, userRegisterVo.getNickname());
            preparedStatement.setString(5, userRegisterVo.getContact());
            preparedStatement.execute();
            System.out.println("Dao"+userRegisterVo.getEmail()+userRegisterVo.getPassword()+userRegisterVo.getName()+userRegisterVo.getNickname()+userRegisterVo.getContact());
        }
    }

    public void insertResetCode(Connection connection, int userIndex, String code)
            throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `user_reset_codes` (user_index, code, code_expires_at) VALUES(?, ?, DATE_ADD(NOW(), INTERVAL 3 MINUTE))")) {
            preparedStatement.setInt(1, userIndex);
            preparedStatement.setString(2, code);
            preparedStatement.execute();
        }
    }

    public int selectResetCodeCount(Connection connection, int userIndex, String code)
            throws SQLException {
        int count;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(`code_index`) AS `count` FROM `user_reset_codes` WHERE `user_index` = ? AND `code` = ? AND `code_expires_at` > NOW()")) {
            preparedStatement.setInt(1, userIndex);
            preparedStatement.setString(2, code);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }
}





















