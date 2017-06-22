package kr.bookstorage.domain.batch;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ohjic on 2017-06-22.
 */
@Entity
@Table(name = "MAIL_RESULT")
@Data
@EqualsAndHashCode(exclude = {"mailQueue"})
@ToString(exclude = {"mailQueue"})
public class MailResult implements Serializable {

    @Id
    @Column(unique = true, nullable = false)
    private Long idx;

    @MapsId
    @OneToOne
    @JoinColumn(name = "IDX")
    private MailQueue mailQueue;

}
