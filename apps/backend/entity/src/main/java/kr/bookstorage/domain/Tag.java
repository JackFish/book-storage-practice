package kr.bookstorage.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ksb on 16. 11. 16.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TAG")
@Data
public class Tag {

    @Id
    @Column(name = "TEXT")
    private String text;

}
