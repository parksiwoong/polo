package popor.com.utility;


import popor.com.enums.users_members.EmailFindResult;

public class EmailFindResultContainer {
    private final EmailFindResult emailFindResult;
    private final String email;

    public EmailFindResultContainer(EmailFindResult emailFindResult) {
        this(emailFindResult, null);
    }

    public EmailFindResultContainer(EmailFindResult emailFindResult, String email) {
        this.emailFindResult = emailFindResult;
        this.email = email;
    }

    public EmailFindResult getFindEmailResult() {
        return emailFindResult;
    }

    public String getEmail() {
        return email;
    }
}
