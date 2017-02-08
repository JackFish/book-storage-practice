package kr.bookstorage.domain;

import kr.bookstorage.dto.BookReportDto;
import kr.bookstorage.mapper.ConvertModelComponent;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by ksb on 16. 11. 9.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "BOOK_REPORT")
@Data
public class BookReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private long idx;

    @Column(name = "SUBJECT")
    private String subject = "";

    @Column(name = "VIEW")
    private long view = 0L;

    @Column(name = "VISIBLE")
    private boolean visible;

    @Column(name = "ENABLED")
    private boolean enabled = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_RECORD_IDX")
    private BookRecord bookRecord;

    @OneToMany(mappedBy = "bookReport", cascade = CascadeType.ALL)
    @Column(name = "BLOCK_LIST")
    @OrderBy("sortOrder asc, idx asc")
    @Where(clause = " enabled = true ")
    private List<Block> blockList;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "BOOK_REPORT_TAG",
            joinColumns = @JoinColumn(name = "BOOK_REPORT_IDX"),
            inverseJoinColumns = {@JoinColumn(name = "TAG_IDX")}
    )
    @Column(name = "TAG_LIST")
    private List<Tag> tagList;

    @OneToOne
    @CreatedBy
    private User createdUser;

    @CreatedDate
    private Date createdDate;

    @OneToOne
    @LastModifiedBy
    private User lastModifiedUser;

    @LastModifiedDate
    private Date lastModifiedDate;

    public Block getBlock(long idx){
        return blockList.stream()
                .filter(block -> block.getIdx() == idx)
                .findFirst()
                .orElse(null);
    }

    public void convert(BookReportDto.Create bookReportDto, ConvertModelComponent convertModelComponent) {
        setSubject(bookReportDto.getSubject());
        setBookRecord(convertModelComponent.convertToBookRecord(bookReportDto.getBookRecord()));
        setTagList(convertModelComponent.convertToTagList(bookReportDto.getTagList()));

        List<Block> blockList = bookReportDto.getBlockList().stream()
                .map(blockDto -> convertModelComponent.convertToBlock(blockDto)).collect(toList());

        for(Block block : blockList) {
            block.setBookReport(this);
        }

        setBlockList(blockList);
    }

    public void merge(BookReportDto.Update bookReportDto, ConvertModelComponent convertModelComponent){
        setSubject(bookReportDto.getSubject());
        setBookRecord(convertModelComponent.convertToBookRecord(bookReportDto.getBookRecord()));
        setTagList(convertModelComponent.convertToTagList(bookReportDto.getTagList()));

        //추가
        List<Block> createList = bookReportDto.getNewBlockList().stream()
                .map(blockDto -> convertModelComponent.convertToBlock(blockDto))
                .collect(toList());
        for(Block block : createList) {
            block.setBookReport(this);
        }

        //수정
        List<Block> updateList = bookReportDto.getExistBlockList().stream()
                .map(blockDto -> convertModelComponent.convertToBlock(blockDto))
                .map(block -> {
                    getBlock(block.getIdx()).merge(block);
                    return getBlock(block.getIdx());
                })
                .collect(toList());

        //삭제
        List<Block> deleteList = blockList.stream()
                .filter(block -> !bookReportDto.getExistBlockIdxList().contains(block.getIdx()))
                .collect(toList());
        deleteList.stream()
                .forEach(Block::delete);

        List<Block> blockList = new ArrayList<>();
        blockList.addAll(createList);
        blockList.addAll(updateList);
        blockList.addAll(deleteList);

        setBlockList(blockList);
    }

}
