package kr.bookstorage.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

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
