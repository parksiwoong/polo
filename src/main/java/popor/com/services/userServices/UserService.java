package popor.com.services.userServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import popor.com.daos.userDaos.UserDao;
import popor.com.daos.userDaos.User_MemberDao;
import popor.com.enums.UserLoginResult;
import popor.com.enums.UserRegisterResult;
import popor.com.enums.users_members.EmailFindResult;
import popor.com.enums.users_members.FindPasswordResult;
import popor.com.utility.Converter;
import popor.com.utility.EmailFindResultContainer;
import popor.com.utility.Sha512;
import popor.com.vos.ResetPasswordVo;
import popor.com.vos.users.UserLoginVo;
import popor.com.vos.users.UserRegisterVo;
import popor.com.vos.users.UserVo;
import popor.com.vos.users_members.FindEmailVo;
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
    private static final String EMAIL_REGEX = "^(?=.{4,100}.$)([0-9a-zA-Z][0-9a-zA-Z\\-_.]*[0-9a-zA-Z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$";
    private static final String PASSWORD_REGEX = "^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?]{4,100})$";

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
        if (!userLoginVo.getEmail().matches(EMAIL_REGEX) ||
                !userLoginVo.getPassword().matches(PASSWORD_REGEX)) {
            userLoginResult = UserLoginResult.NORMALIZATION_FAILURE;
        } else {
            try (Connection connection = this.dataSource.getConnection()) {
                UserVo userVo = this.userDao.selectUser(connection, userLoginVo);
                if (userVo == null) {
                    userLoginResult = UserLoginResult.FAILURE;
                } else {
//                    userLoginVo.getRequest().setAttribute("UserVo", userVo);
                    Converter.setUserVo(userLoginVo.getRequest(), userVo);
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

            if (!userRegisterVo.isNormalized()) {
                userRegisterResult = UserRegisterResult.EMAIL_NO;
            } else if (this.userDao.selectEmailCount(connection, userRegisterVo.getEmail()) > 0) {
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

    public EmailFindResultContainer findEmail(FindEmailVo findEmailVo)
            throws SQLException {
        if (findEmailVo.getName().equals("") || findEmailVo.getContact().equals("")) {
            return new EmailFindResultContainer(EmailFindResult.NORMALIZATION_FAILURE);
        } else {
            try (Connection connection = this.dataSource.getConnection()) {
                String email = this.userDao.selectEmail(connection, findEmailVo);
                if (email == null) {
                    return new EmailFindResultContainer(EmailFindResult.FAILURE);
                } else {
                    return new EmailFindResultContainer(EmailFindResult.SUCCESS, email);
                }
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
                        String.format("<a href=\"http://127.0.0.1:81/resets_password?key=%s\" target=\"_blank\">http를 복사해서 주소에 넣으시오</a>",
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
            if (resetPasswordVo.getPassword() == null) {
                return ResetPasswordResult.KEY_NOT_FOUND;
            }
            this.user_memberDao.updatePassword(connection, resetPasswordVo, email);
            return ResetPasswordResult.SUCCESS;
        }

    }


}


