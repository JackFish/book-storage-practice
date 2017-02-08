package kr.bookstorage.domain;

import kr.bookstorage.exception.ErrorStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

/**
 * Created by ksb on 16. 11. 9.
 */
@Entity
@DiscriminatorValue("YOUTUBE")
@Data
public class YoutubeBlock extends Block {

    @Column(name = "CAPTION")
    private String caption;

    @Column(name = "YOUTUBE_URL")
    @Pattern(regexp = "$|^(?:https?:\\/\\/)?(?:m\\.|www\\.)?(?:youtu\\.be\\/|youtube\\.com\\/(?:embed\\/|v\\/|watch\\?v=|watch\\?.+&v=))((\\w|-){11})(?:\\S+)?$",
            message = ErrorStatus.YOUTUBE_BLOCK_YOUTUBE_URL_VALID_MESSAGE)
    private String youtubeUrl;

    @Override
    public void merge(Block block) {
        YoutubeBlock youtubeBlock = (YoutubeBlock) block;
        setCaption(youtubeBlock.getCaption());
        setYoutubeUrl(youtubeBlock.getYoutubeUrl());
    }
}
