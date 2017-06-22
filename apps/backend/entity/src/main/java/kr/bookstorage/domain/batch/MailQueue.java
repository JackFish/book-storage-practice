package kr.bookstorage.domain.batch;

import kr.bookstorage.domain.code.MailContentType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ohjic on 2017-06-22.
 */
@Entity
@Table(name = "MAIL_QUEUE")
@Data
public class MailQueue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private Long idx;

    @OneToOne(mappedBy = "mailQueue")
    private MailResult mailResult;

    @Column(name = "MAIL_FROM")
    private String from;

    @Column(name = "FROM_NAME")
    private String fromName;

    @Column(name = "MAIL_TO")
    private String to;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTENT_TYPE")
    private MailContentType contentType;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "ATTACH1")
    private String attach1;

    @Column(name = "ATTACH2")
    private String attach2;

    @Column(name = "ATTACH3")
    private String attach3;

    @Column(name = "ATTACH4")
    private String attach4;

    @Column(name = "ATTACH5")
    private String attach5;

    @Column(name = "REQUEST_TIME")
    private String requestTime;

    @Column(name = "ETC1")
    private String etc1;

    @Column(name = "ETC2")
    private String etc2;

    @Column(name = "ETC3")
    private String etc3;

    @Column(name = "ETC4")
    private String etc4;

    @Column(name = "ETC5")
    private String etc5;

}
