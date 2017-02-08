package kr.bookstorage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.bookstorage.domain.code.BookRecordSearchStatus;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by ksb on 16. 11. 10.
 */
public class BookRecordDto {

    @Data
    public static class Create {
        private String subject;
        private String author;
        private String publisher;
        private String summary;
        private ImageGroupDto.Update imageGroup;

        public void setImageGroupFileList(List<MultipartFile> fileList){
            imageGroup.setFileList(fileList);
        }
    }

    @Data
    public static class Update {
        private long idx;
        private String subject;
        private String author;
        private String publisher;
        private String summary;
        private ImageGroupDto.Update imageGroup;

        public void setImageGroupFileList(List<MultipartFile> fileList){
            imageGroup.setFileList(fileList);
        }
    }

    @Data
    public static class Summary {
        private long idx;
        private String subject;
        private String author;
        private String publisher;
        private String summary;
        private boolean visible;
        private ImageGroupDto.Response imageGroup;
    }

    @Data
    public static class Detail {
        private long idx;
        private String subject;
        private String author;
        private String publisher;
        private String summary;
        private boolean visible;
        private ImageGroupDto.Response imageGroup;
        private UserDto.Summary createdUser;
        private UserDto.Summary lastModifiedUser;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date createdDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date lastModifiedDate;
    }

    @Data
    public static class Visible {
        private long idx;
        private boolean visible;
    }

    @Data
    public static class Search {
        private String term;
        private BookRecordSearchStatus type;
        private boolean enabled = true;
    }

    @Data
    public static class Refer {
        private long idx;
        private String subject;
    }

}
