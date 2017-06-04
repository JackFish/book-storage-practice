package kr.bookstorage.dto.talk;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.bookstorage.domain.talk.Message;
import kr.bookstorage.domain.talk.Participant;
import kr.bookstorage.dto.UserDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by ohjic on 2017-06-01.
 */
public class RoomDto {

    @Data
    public static class Summary {
        private long idx;
        private String subject;
        private String description;
        private int participantLimit;
        private List<ParticipantDto.Detail> participantList;
        private UserDto.Summary createdUser;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date createdDate;

    }

    @Data
    public static class Detail {
        private String subject;
        private String description;
        private int participantLimit;
        private List<ParticipantDto.Detail> participantList;
        private List<MessageDto.Detail> messageList;
        private UserDto.Summary createdUser;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date createdDate;

    }

    @Data
    public static class Create {
        private String subject;
        private String description;
        private int participantLimit;
    }

    @Data
    public static class Update {
        private String subject;
        private String description;
        private int participantLimit;
    }

    @Data
    public static class Delete {
        private String subject;
        private String description;
        private int participantLimit;
    }

    @Data
    public static class Refer {
        private long idx;
    }

}
