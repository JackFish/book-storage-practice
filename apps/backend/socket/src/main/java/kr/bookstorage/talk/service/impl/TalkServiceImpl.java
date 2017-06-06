package kr.bookstorage.talk.service.impl;

import kr.bookstorage.dto.talk.MessageDto;
import kr.bookstorage.dto.talk.RoomDto;
import kr.bookstorage.talk.repository.MessageRepository;
import kr.bookstorage.talk.repository.ParticipantRepository;
import kr.bookstorage.talk.repository.RoomRepository;
import kr.bookstorage.talk.service.TalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ksb on 2017. 5. 29..
 */
@Service("talkService")
@Slf4j
public class TalkServiceImpl implements TalkService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<RoomDto.Summary> findRoomSummaryList() {
        return null;
    }

    @Override
    public void createRoom(RoomDto.Create room) {

    }

    @Override
    public void updateRoom(RoomDto.Update room) {

    }

    @Override
    public void deleteRoom(RoomDto.Delete room) {

    }

    @Override
    public void createMessage(MessageDto.Create message) {

    }

    @Override
    public void participateRoom(int roomIdx) {

    }

    @Override
    public void desertRoom(int roomIdx, int participantIdx) {

    }
}
