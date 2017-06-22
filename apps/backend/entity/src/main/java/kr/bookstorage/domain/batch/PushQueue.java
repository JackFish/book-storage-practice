package kr.bookstorage.domain.batch;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ohjic on 2017-06-22.
 */
@Entity
@Table(name = "PUSH_QUEUE")
@Data
public class PushQueue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private Long idx;

}
