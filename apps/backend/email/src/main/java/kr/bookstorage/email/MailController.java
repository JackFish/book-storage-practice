package kr.bookstorage.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ohjic on 2017-06-08.
 */
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    public EmailServiceImpl emailService;

    @Value("${bookstorage.upload.email-path}")
    private String attachmentPath;

    @Autowired
    public SimpleMailMessage template;

    private static final Map<String, Map<String, String>> labels;

    static {
        labels = new HashMap<>();

        //Simple email
        Map<String, String> props = new HashMap<>();
        props.put("headerText", "Send Simple Email");
        props.put("messageLabel", "Message");
        props.put("additionalInfo", "");
        labels.put("send", props);

        //Email with template
        props = new HashMap<>();
        props.put("headerText", "Send Email Using Template");
        props.put("messageLabel", "Template Parameter");
        props.put("additionalInfo",
                "The parameter value will be added to the following message template:<br>" +
                        "<b>This is the test email template for your email:<br>'Template Parameter'</b>"
        );
        labels.put("sendTemplate", props);

        //Email with attachment
        props = new HashMap<>();
        props.put("headerText", "Send Email With Attachment");
        props.put("messageLabel", "Message");
        props.put("additionalInfo", "To make sure that you send an attachment with this email, change the value for the 'attachment.invoice' in the application.properties file to the path to the attachment.");
        labels.put("sendAttachment", props);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
        emailService.test();
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void createMail() {
        Map<String, String> props = labels.get("send");

        MailObject mailObject = new MailObject();
        mailObject.setTo("rnjstjdqhd39@naver.com");
        mailObject.setSubject("TSET");
        mailObject.setText("TSETTEST");

        emailService.sendSimpleMessage(mailObject.getTo(),
                mailObject.getSubject(), mailObject.getText());
    }

    @RequestMapping(value = "/sendTemplate", method = RequestMethod.GET)
    public void createMailWithTemplate() {
        Map<String, String> props = labels.get("sendTemplate");

        MailObject mailObject = new MailObject();

        emailService.sendSimpleMessageUsingTemplate(mailObject.getTo(),
                mailObject.getSubject(),
                template,
                mailObject.getText());
    }

    @RequestMapping(value = "/sendAttachment", method = RequestMethod.GET)
    public void createMailWithAttachment() {
        Map<String, String> props = labels.get("sendAttachment");

        MailObject mailObject = new MailObject();

        emailService.sendMessageWithAttachment(
                mailObject.getTo(),
                mailObject.getSubject(),
                mailObject.getText(),
                attachmentPath
        );
    }
}
