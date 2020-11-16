package popor.com.services.userServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import popor.com.vos.users_members.SendMailVo;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(SendMailVo sendMailVo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendMailVo.getTo());
        message.setFrom("tldnd898952@gmail.com");
        message.setSubject(sendMailVo.getTitle());
        message.setText(sendMailVo.getContent());
        javaMailSender.send(message);

    }
}
