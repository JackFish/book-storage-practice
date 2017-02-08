package kr.bookstorage.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import kr.bookstorage.domain.code.BlockType;
import lombok.Data;

/**
 * Created by ksb on 16. 11. 19.
 */
public class BlockDto {

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = BlockType.BLOCK_TYPE)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = DraftBlockDto.Create.class, name = BlockType.DRAFT_TYPE),
            @JsonSubTypes.Type(value = ImageBlockDto.Create.class, name = BlockType.IMAGE_TYPE),
            @JsonSubTypes.Type(value = YoutubeBlockDto.Create.class, name = BlockType.YOUTUBE_TYPE)
    })
    @Data
    public static abstract class Create {
        private int sortOrder;
        private boolean visible;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = BlockType.BLOCK_TYPE)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = DraftBlockDto.Update.class, name = BlockType.DRAFT_TYPE),
            @JsonSubTypes.Type(value = ImageBlockDto.Update.class, name = BlockType.IMAGE_TYPE),
            @JsonSubTypes.Type(value = YoutubeBlockDto.Update.class, name = BlockType.YOUTUBE_TYPE)
    })
    @Data
    public static abstract class Update {
        private long idx;
        private int sortOrder;
        private boolean visible;

        final public boolean isNew(){
            return idx == 0;
        }

    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = BlockType.BLOCK_TYPE)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = DraftBlockDto.Update.class, name = BlockType.DRAFT_TYPE),
            @JsonSubTypes.Type(value = ImageBlockDto.Update.class, name = BlockType.IMAGE_TYPE),
            @JsonSubTypes.Type(value = YoutubeBlockDto.Update.class, name = BlockType.YOUTUBE_TYPE)
    })
    @Data
    public static abstract class Summary {
        private long idx;
        private int sortOrder;
        private boolean visible;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = BlockType.BLOCK_TYPE)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = DraftBlockDto.Update.class, name = BlockType.DRAFT_TYPE),
            @JsonSubTypes.Type(value = ImageBlockDto.Update.class, name = BlockType.IMAGE_TYPE),
            @JsonSubTypes.Type(value = YoutubeBlockDto.Update.class, name = BlockType.YOUTUBE_TYPE)
    })
    @Data
    public static abstract class Detail {
        private long idx;
        private int sortOrder;
        private boolean visible;
    }

//    @Data
//    public static class Visible {
//
//    }
//
//    @Data
//    public static class Search {
//
//    }

}
