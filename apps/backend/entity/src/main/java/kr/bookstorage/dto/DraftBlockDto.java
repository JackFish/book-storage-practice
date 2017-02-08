package kr.bookstorage.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import kr.bookstorage.domain.code.BlockType;
import lombok.Data;

/**
 * Created by ksb on 16. 11. 19.
 */
public class DraftBlockDto {

    @JsonRootName(BlockType.DRAFT_TYPE)
    @JsonTypeName(BlockType.DRAFT_TYPE)
    @Data
    public static class Create extends BlockDto.Create {
        private String plainText;
        private String contentStateJson;
    }

    @JsonRootName(BlockType.DRAFT_TYPE)
    @JsonTypeName(BlockType.DRAFT_TYPE)
    @Data
    public static class Update extends BlockDto.Update {
        private String plainText;
        private String contentStateJson;
    }

    @JsonRootName(BlockType.DRAFT_TYPE)
    @JsonTypeName(BlockType.DRAFT_TYPE)
    @Data
    public static class Summary extends BlockDto.Summary {
        private String plainText;
        private String contentStateJson;

    }

    @JsonRootName(BlockType.DRAFT_TYPE)
    @JsonTypeName(BlockType.DRAFT_TYPE)
    @Data
    public static class Detail extends BlockDto.Detail {
        private String plainText;
        private String contentStateJson;

    }

//    @Data
//    public static class Visible {
//        private String content;
//
//    }
//
//    @Data
//    public static class Search {
//        private String content;
//
//    }

}
