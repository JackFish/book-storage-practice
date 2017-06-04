package kr.bookstorage.dto.talk;

import kr.bookstorage.dto.UserDto;
import lombok.Data;

/**
 * Created by ohjic on 2017-06-01.
 */
public class MessageDto {

    @Data
    public static class Detail {
        private long idx;
        private String content;
        private UserDto.Summary createdUser;
    }

    @Data
    public static class Create {
        private String content;
    }

}
