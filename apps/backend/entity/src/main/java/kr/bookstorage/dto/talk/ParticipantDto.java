package kr.bookstorage.dto.talk;

import kr.bookstorage.dto.UserDto;
import lombok.Data;

/**
 * Created by ohjic on 2017-06-01.
 */
public class ParticipantDto {

    @Data
    public static class Summary {
        private long idx;
        private UserDto.Summary createdUser;
    }

    @Data
    public static class Detail {
        private long idx;
        private UserDto.Summary createdUser;
    }

    @Data
    public static class Create {
        private RoomDto.Refer room;
    }

    @Data
    public static class Delete {
        private long idx;
    }

}
