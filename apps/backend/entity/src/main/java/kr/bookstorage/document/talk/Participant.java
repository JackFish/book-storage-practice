package kr.bookstorage.document.talk;

import lombok.Data;

import java.util.UUID;

@Data
public class Participant {

    private long idx;

    private boolean enabled;

    private UUID uniqueId;

    private String email;

    private String userName;

}
