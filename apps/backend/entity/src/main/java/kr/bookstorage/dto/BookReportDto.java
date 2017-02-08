package kr.bookstorage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.bookstorage.domain.code.BookReportSearchStatus;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by ksb on 16. 11. 16.
 */
public class BookReportDto {

    @Data
    public static class Create {
        private String subject;
        private BookRecordDto.Refer bookRecord;
        private List<BlockDto.Create> blockList;
        private List<TagDto> tagList;

        public void setImageBlockFileList(List<MultipartFile> fileList){
            for (BlockDto.Create blockDto : blockList) {
                if(blockDto instanceof ImageBlockDto.Create){
                    ImageBlockDto.Create imageBlockDto = (ImageBlockDto.Create)blockDto;
                    imageBlockDto.getImageGroup().setFileList(fileList);
                }
            }
        }
    }

    @Data
    public static class Update {
        private long idx;
        private String subject;
        private BookRecordDto.Refer bookRecord;
        private List<BlockDto.Update> blockList;
        private List<TagDto> tagList;

        public void setImageBlockFileList(List<MultipartFile> fileList){
            for (BlockDto.Update blockDto : blockList) {
                if(blockDto instanceof ImageBlockDto.Update){
                    ImageBlockDto.Update imageBlockDto = (ImageBlockDto.Update)blockDto;
                    imageBlockDto.getImageGroup().setFileList(fileList);
                }
            }
        }

        public List<BlockDto.Update> getNewBlockList() {
            return blockList.stream().filter(blockDto -> blockDto.isNew()).collect(toList());
        }

        public List<BlockDto.Update> getExistBlockList() {
            return blockList.stream().filter(blockDto -> !blockDto.isNew()).collect(toList());
        }

        public List<Long> getNewBlockIdxList() {
            return getNewBlockList().stream().map(blockDto -> blockDto.getIdx()).collect(toList());
        }

        public List<Long> getExistBlockIdxList() {
            return getExistBlockList().stream().map(blockDto -> blockDto.getIdx()).collect(toList());
        }

    }

    @Data
    public static class Summary {
        private long idx;
        private String subject;
        private long view;
        private boolean visible;
        private BookRecordDto.Summary bookRecord;
        private List<BlockDto.Summary> blockList;
        private List<TagDto> tagList;
    }

    @Data
    public static class Detail {
        private long idx;
        private String subject;
        private long view;
        private boolean visible;
        private BookRecordDto.Refer bookRecord;
        private List<BlockDto.Detail> blockList;
        private List<TagDto> tagList;
        private UserDto.Summary createdUser;
        private UserDto.Summary lastModifiedUser;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date createdDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date lastModifiedDate;
    }

    @Data
    public static class Visible {
        private long idx;
        private boolean visible;
    }

    @Data
    public static class Search {
        private String term;
        private BookReportSearchStatus type;
        private boolean enabled = true;
    }

}
