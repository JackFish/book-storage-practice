package kr.bookstorage.domain.batch;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ohjic on 2017-06-09.
 */
@Entity
@Table(name = "PERSON")
@Data
@AllArgsConstructor
public class Person {
    private String lastName;
    private String firstName;
}