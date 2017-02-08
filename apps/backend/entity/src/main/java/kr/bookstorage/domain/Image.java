package kr.bookstorage.domain;

import kr.bookstorage.exception.ErrorStatus;
import kr.bookstorage.exception.exceptions.ForbiddenException;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 * Created by ksb on 2016-08-07.
 */
@Data
@Entity
@Table(name = "IMAGE")
@DynamicUpdate
public class Image {
    @Id
    @Column(name = "IMAGE_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageIdx;

    @ManyToOne
    private ImageGroup imageGroup;

    /**
     * 이미지 원본 명
     */
    @Column(name = "IMAGE_NAME", nullable = false)
    private String imageName;

    /**
     * 이미지 파일 사이즈
     */
    @Column(name = "IMAGE_SIZE", nullable = false)
    private long imageSize;

    /**
     * 저장된 이미지 명
     */
    @Column(name = "STORED_IMAGE_NAME", nullable = false)
    private String storedImageName;

    /**
     * 이미지 컨텐츠 타입
     */
    @Column(name = "CONTENT_TYPE")
    private String contentType;

    /**
     * 사용 여부
     */
    @Column(name = "ENABLED", nullable = false)
    private boolean enabled = true;

    /**
     * 정렬 순서
     */
    @Column(name = "SORT_ORDER")
    private int sortOrder = 1;

    @Transient
    private MultipartFile file;

    @Transient
    private String uploadPath;

    @PrePersist
    public void prePersist(){
        if(isNew()) uploadImage();
    }

    public void delete(){
        setEnabled(false);
    }

    public boolean isNew(){
        return imageIdx == 0;
    }

    private void uploadImage(){
        if(file == null){
            throw new ForbiddenException(ErrorStatus.IMAGE_CONTENT_TYPE_VALID_MESSAGE);
        }
        else if(!file.getContentType().startsWith("image/")) {
            throw new ForbiddenException(ErrorStatus.IMAGE_CONTENT_TYPE_VALID_MESSAGE);
        }
        DateTime now = DateTime.now(DateTimeZone.UTC);
        String storedImageName = now + UUID.randomUUID().toString();
        storedImageName = storedImageName.replace(":", "").replace(".", "");

        DecimalFormat df = new DecimalFormat("00");
        String fileUploadPath =
                uploadPath
                        + File.separator
                        + now.getYear()
                        + File.separator
                        + df.format(now.getMonthOfYear())
                        + File.separator
                        + df.format(now.getDayOfMonth())
                        + File.separator;

        File dir = new File(fileUploadPath);
        if(!dir.exists() && !dir.mkdirs()){
            throw new ForbiddenException(ErrorStatus.IMAGE_FILE_UPLOAD_ERROR_MESSAGE);
        }

        fileUploadPath = fileUploadPath.concat(storedImageName);

        try {
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(fileUploadPath));
        } catch (IOException e) {
            throw new ForbiddenException(ErrorStatus.IMAGE_FILE_UPLOAD_ERROR_MESSAGE);
        }

        this.storedImageName = storedImageName;
        this.contentType = file.getContentType();
        this.imageSize = file.getSize();
        this.imageName = file.getOriginalFilename();
    }

}
