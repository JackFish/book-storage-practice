package kr.bookstorage.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ksb on 2016-08-07.
 */
public class ImageDto {

    @Data
    public static class Update {
        private long imageIdx;
        private int sortOrder;

        @JsonIgnore
        private MultipartFile file;

        public boolean isNew(){
            return imageIdx == 0;
        }
    }

    @Component
    @Data
    public static class Response {
        private static String IMAGE_HOST_URL;
        private long imageIdx;
        private String imageName;

        private String thumbUrl;
        private String originUrl;

        public String getThumbUrl() {
            return IMAGE_HOST_URL + "/thumb/" + this.imageIdx + "-" + this.imageName;
        }

        public String getOriginUrl() {
            return IMAGE_HOST_URL + "/" + this.imageIdx + "-" + this.imageName;
        }

        @Value("${bookstorage.image-host-url}")
        public void setImageHostUrl(String imageHostUrl){
            IMAGE_HOST_URL = imageHostUrl;
        }
    }

}
