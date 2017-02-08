package kr.bookstorage.domain.code;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by ksb on 16. 11. 19.
 */
public enum BlockType {
    DRAFT("드래프트"), IMAGE("사진"), YOUTUBE("유튜브");

    private String value;

    BlockType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @JsonValue
    public String getCode(){
        return this.name();
    }

    public final static String BLOCK_TYPE = "type";
    public final static String DRAFT_TYPE = "draft";
    public final static String IMAGE_TYPE = "image";
    public final static String YOUTUBE_TYPE = "youtube";

}
