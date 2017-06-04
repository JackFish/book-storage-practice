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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ksb on 2017. 5. 29..
 */
@Controller
public class TalkController {

    @Autowired
    private TalkService talkServiceImpl;

    private List<RoomDto.Summary> roomList;

    TalkController() {
        roomList = new ArrayList<>();

        RoomDto.Summary room1 = new RoomDto.Summary();
        RoomDto.Summary room2 = new RoomDto.Summary();
        RoomDto.Summary room3 = new RoomDto.Summary();

        room1.setSubject("room0");
        room1.setDescription("droom0");
        room1.setParticipantLimit(0);
        room2.setSubject("room1");
        room2.setDescription("droom1");
        room2.setParticipantLimit(1);
        room3.setSubject("room2");
        room3.setDescription("droom2");
        room3.setParticipantLimit(2);

        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
    }

    @SubscribeMapping("/talk/room.find")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> findRoom() {
        return roomList;
    }

    @SubscribeMapping("/talk/room.insert")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> insertRoom(RoomDto.Create room) {

        RoomDto.Summary test = new RoomDto.Summary();

        test.setSubject("room"+roomList.size());
        test.setDescription("droom"+roomList.size());
        test.setParticipantLimit(roomList.size());

        roomList.add(test);

        return roomList;
    }

    @SubscribeMapping("/talk/room.update")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> updateRoom(RoomDto.Update room) {

        RoomDto.Summary test = new RoomDto.Summary();

        test.setSubject("room"+roomList.size());
        test.setDescription("droom"+roomList.size());
        test.setParticipantLimit(roomList.size());

        roomList.add(test);

        return roomList;
    }

    @SubscribeMapping("/talk/room.delete")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> deleteRoom(RoomDto.Delete room) {
        roomList.remove(roomList.size()-1);
        return roomList;
    }

    @SubscribeMapping("/talk/room.participate/{room_idx}/")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> participateRoom(@DestinationVariable int roomIdx, Authentication authentication) {
        UUID uuid = (UUID) authentication.getCredentials();

        return roomList;
    }

    @SubscribeMapping("/talk/room.desert/{room_idx}/")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> desertRoom(@DestinationVariable int roomIdx, Authentication authentication) {
        UUID uuid = (UUID) authentication.getCredentials();

        return roomList;
    }

    @SubscribeMapping("/talk/room.message/{room_idx}")
    @SendTo("/talk/room.list/{room_idx}")
    public List<RoomDto.Summary> message(@DestinationVariable int roomIdx, MessageDto.Create message) {

        return roomList;
    }

}
