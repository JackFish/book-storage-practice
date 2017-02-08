package kr.bookstorage.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import kr.bookstorage.domain.code.BlockType;
import lombok.Data;

/**
 * Created by ksb on 16. 11. 19.
 */
public class YoutubeBlockDto {

    @JsonRootName(BlockType.YOUTUBE_TYPE)
    @JsonTypeName(BlockType.YOUTUBE_TYPE)
    @Data
    public static class Create extends BlockDto.Create {
        private String caption;
        private String youtubeUrl;
    }

    @JsonRootName(BlockType.YOUTUBE_TYPE)
    @JsonTypeName(BlockType.YOUTUBE_TYPE)
    @Data
    public static class Update extends BlockDto.Update {
        private String caption;
        private String youtubeUrl;
    }

    @JsonRootName(BlockType.YOUTUBE_TYPE)
    @JsonTypeName(BlockType.YOUTUBE_TYPE)
    @Data
    public static class Summary extends BlockDto.Summary {
        private String caption;
        private String youTubeUrl;
    }

    @JsonTypeName(BlockType.YOUTUBE_TYPE)
    @JsonRootName(BlockType.YOUTUBE_TYPE)
    @Data
    public static class Detail extends BlockDto.Detail {
        private String caption;
        private String youTubeUrl;
    }

//    @Data
//    public static class Visible {
//        private String caption;
//        private String youTubeUrl;
//    }
//
//    @Data
//    public static class Search {
//        private String caption;
//        private String youTubeUrl;
//    }

}
