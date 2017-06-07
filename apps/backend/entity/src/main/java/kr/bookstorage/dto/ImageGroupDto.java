package kr.bookstorage.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by ksb on 2016-08-07.
 */
public class ImageGroupDto {

    @Data
    public static class Update {
        @Value("${bookstorage.upload.image-path}")
        private String uploadPath;

        private long imageGroupIdx;
        private List<ImageDto.Update> imageList;

        public void setFileList(List<MultipartFile> fileList){
            imageList.stream()
                    .filter(ImageDto.Update::isNew)
                    .forEach(imageDto -> {
                        imageDto.setFile(fileList.get(0));
                        fileList.remove(0);
                    });
        }
    }

    @Data
    public static class Response {
        private long imageGroupIdx;
        private List<ImageDto.Response> imageList;

        private String mainImageOriginUrl;
        private String mainImageThumbUrl;

        public String getMainImageOriginUrl() {
            if (!ObjectUtils.isEmpty(imageList) && imageList.size() > 0) {
                return imageList.get(0).getOriginUrl();
            }
            return null;
        }

        public String getMainImageThumbUrl() {
            if (!ObjectUtils.isEmpty(imageList) && imageList.size() > 0) {
                return imageList.get(0).getThumbUrl();
            }
            return null;
        }
    }

}
