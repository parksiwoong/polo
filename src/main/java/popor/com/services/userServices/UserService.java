package popor.com.services.userServices;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import popor.com.daos.userDaos.UserDao;
import popor.com.daos.userDaos.User_MemberDao;
import popor.com.enums.FindIdResult;
import popor.com.enums.UserLoginResult;
import popor.com.enums.UserRegisterResult;
import popor.com.enums.users_members.FindPasswordResult;
import popor.com.utility.Sha512;
import popor.com.vos.ResetPasswordVo;
import popor.com.vos.users.UserLoginVo;
import popor.com.vos.users.UserRegisterVo;
import popor.com.vos.users.UserVo;
import popor.com.vos.users_members.FindIdVo;
import popor.com.vos.users_members.FindPasswordVo;
import popor.com.enums.users_members.ResetPasswordResult;
import popor.com.vos.users_members.SendMailVo;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {
    private final DataSource dataSource;
    private final UserDao userDao;
    private final User_MemberDao user_memberDao;
    private final MailService mailService;

    @Autowired
    public UserService(DataSource dataSource, UserDao userDao, MailService mailService, User_MemberDao user_memberDao) {
        this.dataSource = dataSource;
        this.userDao = userDao;
        this.mailService = mailService;
        this.user_memberDao = user_memberDao;
    }

    public UserLoginResult login(UserLoginVo userLoginVo)
            throws SQLException {
        UserLoginResult userLoginResult;
        if (userLoginVo.getEmail().equals("") || userLoginVo.getPassword().equals("")) {
            userLoginResult = UserLoginResult.NORMALIZATION_FAILURE;
        } else {
            try (Connection connection = this.dataSource.getConnection()) {
                UserVo userVo = this.userDao.selectUser(connection, userLoginVo);
                if (userVo == null) {
                    userLoginResult = UserLoginResult.FAILURE;
                } else {
                    userLoginVo.getSession().setAttribute("UserVo", userVo);
                    userLoginResult = UserLoginResult.SUCCESS;
                }
            }
        }
        return userLoginResult;
    }

    public UserRegisterResult register(UserRegisterVo userRegisterVo)
            throws SQLException {
        UserRegisterResult userRegisterResult;
        try (Connection connection = this.dataSource.getConnection()) {
            if (this.userDao.selectEmailCount(connection, userRegisterVo.getEmail()) > 0) {
                userRegisterResult = UserRegisterResult.EMAIL_DUPLICATE;
            } else if (this.userDao.selectNicknameCount(connection, userRegisterVo.getNickname()) > 0) {
                userRegisterResult = UserRegisterResult.NICKNAME_DUPLICATE;
            } else if (this.userDao.selectContactCount(connection, userRegisterVo.getContact()) > 0) {
                userRegisterResult = UserRegisterResult.CONTACT_DUPLICATE;
            } else if (this.userDao.selectContactCount(connection, userRegisterVo.getName()) > 0) {
                userRegisterResult = UserRegisterResult.CONTACT_DUPLICATE;
            } else {
                this.userDao.insertUser(connection, userRegisterVo);
                if (this.userDao.selectEmailCount(connection, userRegisterVo.getEmail()) > 0) {
                    userRegisterResult = UserRegisterResult.SUCCESS;
                    System.out.println("service" + userRegisterVo.getEmail() + userRegisterVo.getPassword() + userRegisterVo.getName() + userRegisterVo.getNickname() + userRegisterVo.getContact());

                } else {
                    userRegisterResult = UserRegisterResult.FAILURE;
                }
            }
        }
        return userRegisterResult;
    }

    public FindIdResult findId(FindIdVo findIdVo)throws
            SQLException{
        try(Connection connection = this.dataSource.getConnection()) {
            UserVo userVo = this.user_memberDao.selectUser(connection, findIdVo);
            if(userVo == null){
                return FindIdResult.NOT;
            }else {

                return FindIdResult.OKEY;
            }
        }
    }

    public FindPasswordResult findPassword(FindPasswordVo findPasswordVo) throws
            SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            UserVo userVo = this.user_memberDao.selectUser(connection, findPasswordVo);
            if (userVo == null) {
                return FindPasswordResult.USER_NOT_FOUND;
            } else {
                String key = Sha512.hash(String.format("%s%s%s%f",
                        findPasswordVo.getEmail(),
                        findPasswordVo.getName(),
                        new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()),
                        Math.random()
                ));
                this.user_memberDao.insertPasswordkey(connection, findPasswordVo, key);

                SendMailVo sendMailVo = new SendMailVo(
                        "[사이트 이름] 비밀번호 재설정 링크",
                        String.format("<a href=\"http://127.0.0.1:81/resets_password?key=%s\" target=\"_blank\">재설정</a>",
                                key),
                        findPasswordVo.getEmail());
                this.mailService.send(sendMailVo);
                return FindPasswordResult.EMAIL_SENT;
            }
        }
    }

    public ResetPasswordResult resetPassword(ResetPasswordVo resetPasswordVo) throws
            SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            String email = this.user_memberDao.selectPasswordEmail(connection, resetPasswordVo);
            if (email == null) {
                return ResetPasswordResult.KEY_NOT_FOUND;
            }
            this.user_memberDao.updatePassword(connection, resetPasswordVo, email);
            return ResetPasswordResult.SUCCESS;
        }

    }


}

//    public UserResetResult reset(HttpSession session, UserResetVo userResetVo)
//            throws SQLException {
//        UserResetResult userResetResult;
//        try (Connection connection = this.dataSource.getConnection()) {
//            UserVo userVo = this.userDao.selectUser(connection, userResetVo);
//            if (userVo == null) {
//                userResetResult = UserResetResult.NO_MATCHING_USER;
//            } else {
//                // 있음
//                // 코드 생성x
//                Random random = new Random();
//                String code = String.format("%06d", random.nextInt(999999));
//                this.userDao.insertResetCode(connection, userVo.getIndex(), code);
//                session.setAttribute("userResetIndex", userVo.getIndex());
//                // 인증코드 SMS 전송
//
//                userResetResult = UserResetResult.CODE_SENT;
//            }
//        }
//        return userResetResult;
//    }
//
//    public UserResetResult reset(HttpSession session, String code)
//            throws SQLException, NumberFormatException {
//        UserResetResult userResetResult;
//        try (Connection connection = this.dataSource.getConnection()) {
//            Object userIndexObject = session.getAttribute("userResetIndex");
//            int userIndex = Integer.parseInt(String.valueOf(userIndexObject));
//            int count = this.userDao.selectResetCodeCount(connection, userIndex, code);
//            if (count == 1) {
//                userResetResult = UserResetResult.CODE_GOOD;
//            } else {
//                userResetResult = UserResetResult.CODE_NONO;
//            }
//        }
//        return userResetResult;
//    }

