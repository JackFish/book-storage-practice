package kr.bookstorage.domain;

import lombok.Data;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * Created by ohjic on 2017-06-02.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "REPLY")
@Data
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private long idx;

    @Lob
    @Column(name = "CONTENT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_IDX")
    private Post post;

    @OneToOne
    @CreatedBy
    private User createdUser;

    @CreatedDate
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTimeAndZone")
    @Columns(columns={@Column(name = "CREATED_DATE"), @Column(name = "CREATED_DATE_TIMEZONE")})
    private DateTime createdDate;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @OneToOne
    @LastModifiedBy
    private User lastModifiedUser;

    @LastModifiedDate
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTimeAndZone")
    @Columns(columns={@Column(name = "LAST_MODIFIED_DATE"), @Column(name = "LAST_MODIFIED_DATE_TIMEZONE")})
    private DateTime lastModifiedDate;

}
