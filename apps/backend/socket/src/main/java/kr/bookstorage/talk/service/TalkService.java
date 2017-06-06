package kr.bookstorage.talk.service;

import kr.bookstorage.dto.talk.MessageDto;
import kr.bookstorage.dto.talk.RoomDto;

import java.util.List;

/**
 * Created by ksb on 2017. 5. 29..
 */
public interface TalkService {

    List<RoomDto.Summary> findRoomSummaryList();

    void createRoom(RoomDto.Create room);

    void updateRoom(RoomDto.Update room);

    void deleteRoom(RoomDto.Delete room);

    void createMessage(MessageDto.Create message);

    void participateRoom(int roomIdx);

    void desertRoom(int roomIdx, int participantIdx);
}
