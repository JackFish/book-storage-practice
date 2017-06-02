package kr.bookstorage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by ohjic on 2017-06-02.
 */
public class ReplyDto {

    @Data
    public static class Summary {
        private long idx;
        private String content;
        private UserDto.Summary createdUser;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date createdDate;
    }

    @Data
    public static class Detail {
        private long idx;
        private String content;
        private UserDto.Summary createdUser;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date createdDate;
    }

    @Data
    public static class Create {
        private String content;
        private PostDto.Refer post;
    }

    @Data
    public static class Update {
        private long idx;
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
        private PostDto.Refer post;
    }
}
