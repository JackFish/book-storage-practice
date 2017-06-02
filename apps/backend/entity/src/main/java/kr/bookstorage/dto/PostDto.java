package kr.bookstorage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by ohjic on 2017-06-02.
 */
public class PostDto {

    @Data
    public static class Detail {
        private long idx;
        private String subject;
        private String content;
        private PostDto.Detail parent;
        private UserDto.Summary createdUser;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date createdDate;
    }

    @Data
    public static class Summary {
        private long idx;
        private String subject;
        private String content;
        private PostDto.Summary parent;
        private UserDto.Summary createdUser;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date createdDate;
    }

    @Data
    public static class Create {
        private String subject;
        private String content;
        private PostDto.Refer parent;
    }

    @Data
    public static class Update {
        private long idx;
        private String subject;
        private String content;
    }

    @Data
    public static class Delete {
        private long idx;
    }

    @Data
    public static class Refer {
        private long idx;
    }

    @Data
    public static class Search {
        private String subject;
    }
}
