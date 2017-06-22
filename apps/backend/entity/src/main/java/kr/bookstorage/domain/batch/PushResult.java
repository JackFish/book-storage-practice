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
@Table(name = "PUSH_RESULT")
@Data
@EqualsAndHashCode(exclude = {"pushQueue"})
@ToString(exclude = {"pushQueue"})
public class PushResult implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "IDX")
    private PushQueue pushQueue;

}
