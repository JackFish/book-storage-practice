package kr.bookstorage.email;

import org.springframework.mail.SimpleMailMessage;

/**
 * Created by ohjic on 2017-06-08.
 */
public interface EmailService {
    void test();
    void sendSimpleMessage(String to,
                           String subject,
                           String text);

    void sendSimpleMessageUsingTemplate(String to,
                                        String subject,
                                        SimpleMailMessage template,
                                        String ...templateArgs);

    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);
}
