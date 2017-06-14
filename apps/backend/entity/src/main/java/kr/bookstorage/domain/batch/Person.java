package kr.bookstorage.domain.batch;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by ohjic on 2017-06-09.
 */
@Entity
@Table(name = "PERSON")
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private Long idx;

    private String lastName;

    private String firstName;

}