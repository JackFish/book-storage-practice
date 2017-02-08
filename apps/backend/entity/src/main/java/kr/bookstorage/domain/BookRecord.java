package kr.bookstorage.domain;

import kr.bookstorage.dto.BookRecordDto;
import kr.bookstorage.mapper.ConvertModelComponent;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by ksb on 16. 11. 9.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "BOOK_RECORD")
@Data
public class BookRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private long idx;

    @Column(name = "SUBJECT")
    private String subject = "";

    @Column(name = "AUTHOR")
    private String author = "";

    @Column(name = "PUBLISHER")
    private String publisher = "";

    @Lob
    @Column(name = "SUMMARY")
    private String summary = "";

    @Column(name = "VISIBLE")
    private boolean visible;

    @Column(name = "ENABLED")
    private boolean enabled = true;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "IMAGE_GROUP")
    private ImageGroup imageGroup;

    @OneToMany(mappedBy = "bookRecord")
    private List<BookReport> bookReportList;

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

    public void convert(BookRecordDto.Create bookRecordDto, ConvertModelComponent convertModelComponent) {
        setSubject(bookRecordDto.getSubject());
        setAuthor(bookRecordDto.getAuthor());
        setSummary(bookRecordDto.getSummary());
        setPublisher(bookRecordDto.getPublisher());

        ImageGroup imageGroup = convertModelComponent.convertToImageGroup(bookRecordDto.getImageGroup());

        setImageGroup(imageGroup);
    }

    public void merge(BookRecordDto.Update bookRecordDto, ConvertModelComponent convertModelComponent){
        setSubject(bookRecordDto.getSubject());
        setAuthor(bookRecordDto.getAuthor());
        setSummary(bookRecordDto.getSummary());
        setPublisher(bookRecordDto.getPublisher());

        ImageGroup mergeImageGroup = convertModelComponent.convertToImageGroup(bookRecordDto.getImageGroup());
        List<Long> imageIdxList = mergeImageGroup.getImageIdxList();

        imageGroup.getImageList().stream()
                .filter(image -> !imageIdxList.contains(image.getImageIdx()))
                .forEach(image -> imageGroup.getImage(image.getImageIdx()).delete());

        mergeImageGroup.getImageList().stream()
                .filter(image -> image.isNew())
                .forEach(image -> {
                    imageGroup.getImageList().add(image);
                    image.setImageGroup(imageGroup);
                });

        mergeImageGroup.getImageList().stream()
                .forEach(image -> imageGroup.getImage(image.getImageIdx()).setSortOrder(image.getSortOrder()));

    }

}
