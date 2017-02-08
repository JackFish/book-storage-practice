package kr.bookstorage.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by ksb on 16. 11. 9.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
@Table(name = "BLOCK")
@Data
public abstract class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private long idx;

    @Column(name = "SORT_ORDER")
    private int sortOrder;

    @Column(name = "VISIBLE")
    private boolean visible;

    @Column(name = "ENABLED")
    private boolean enabled = true;

    @ManyToOne
    private BookReport bookReport;

    final public boolean isNew(){
        return idx == 0;
    }

    final public void delete(){
        setEnabled(false);
    }

    public abstract void merge(Block block);

}
