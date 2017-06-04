package kr.bookstorage.talk.service.impl;

import kr.bookstorage.talk.repository.MessageRepository;
import kr.bookstorage.talk.repository.ParticipantRepository;
import kr.bookstorage.talk.repository.RoomRepository;
import kr.bookstorage.talk.service.TalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}
