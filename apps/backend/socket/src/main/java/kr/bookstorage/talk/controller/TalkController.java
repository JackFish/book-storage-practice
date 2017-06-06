package kr.bookstorage.talk.controller;

import kr.bookstorage.dto.talk.MessageDto;
import kr.bookstorage.dto.talk.RoomDto;
import kr.bookstorage.talk.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

/**
 * Created by ksb on 2017. 5. 29..
 */
@Controller
public class TalkController {

    @Autowired
    private TalkService talkServiceImpl;

    @SubscribeMapping("/talk/room.find")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> findRoom() {
        return talkServiceImpl.findRoomSummaryList();
    }

    @SubscribeMapping("/talk/room.insert")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> insertRoom(RoomDto.Create room) {
        talkServiceImpl.createRoom(room);
        return talkServiceImpl.findRoomSummaryList();
    }

    @SubscribeMapping("/talk/room.update")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> updateRoom(RoomDto.Update room) {
        talkServiceImpl.updateRoom(room);
        return talkServiceImpl.findRoomSummaryList();
    }

    @SubscribeMapping("/talk/room.delete")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> deleteRoom(RoomDto.Delete room) {
        talkServiceImpl.deleteRoom(room);
        return talkServiceImpl.findRoomSummaryList();
    }

    @SubscribeMapping("/talk/room.participate/{room_idx}")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> participateRoom(@DestinationVariable int roomIdx) {
        talkServiceImpl.participateRoom(roomIdx);
        return talkServiceImpl.findRoomSummaryList();
    }

    @SubscribeMapping("/talk/room.desert/{room_idx}/participant/{participant_idx}")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> desertRoom(@DestinationVariable int roomIdx, @DestinationVariable int participantIdx) {
        talkServiceImpl.desertRoom(roomIdx, participantIdx);
        return talkServiceImpl.findRoomSummaryList();
    }

    @SubscribeMapping("/talk/room.message/{room_idx}")
    @SendTo("/talk/room.list/{room_idx}")
    public List<RoomDto.Summary> message(@DestinationVariable int roomIdx, MessageDto.Create message) {
        talkServiceImpl.createMessage(message);

        return talkServiceImpl.findRoomSummaryList();
    }

}
