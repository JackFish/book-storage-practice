package kr.bookstorage.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import kr.bookstorage.domain.code.BlockType;
import lombok.Data;

/**
 * Created by ksb on 16. 11. 19.
 */
public class ImageBlockDto {

    @JsonRootName(BlockType.IMAGE_TYPE)
    @JsonTypeName(BlockType.IMAGE_TYPE)
    @Data
    public static class Create extends BlockDto.Create {
        private String caption;
        private ImageGroupDto.Update imageGroup;
    }

    @JsonRootName(BlockType.IMAGE_TYPE)
    @JsonTypeName(BlockType.IMAGE_TYPE)
    @Data
    public static class Update extends BlockDto.Update {
        private String caption;
        private ImageGroupDto.Update imageGroup;
    }

    @JsonRootName(BlockType.IMAGE_TYPE)
    @JsonTypeName(BlockType.IMAGE_TYPE)
    @Data
    public static class Summary extends BlockDto.Summary {
        private String caption;
        private ImageGroupDto.Response imageGroup;
    }

    @JsonRootName(BlockType.IMAGE_TYPE)
    @JsonTypeName(BlockType.IMAGE_TYPE)
    @Data
    public static class Detail extends BlockDto.Detail {
        private String caption;
        private ImageGroupDto.Response imageGroup;
    }

//    @Data
//    public static class Visible {
//        private String caption;
//        private ImageGroupDto.Create imageGroup;
//    }
//
//    @Data
//    public static class Search {
//        private String caption;
//        private ImageGroupDto.Create imageGroup;
//    }

}
