package kr.bookstorage.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ksb on 16. 11. 9.
 */
@Entity
@DiscriminatorValue("IMAGE")
@Data
public class ImageBlock extends Block {

    @Column(name = "CAPTION")
    private String caption;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "IMAGE_GROUP")
    private ImageGroup imageGroup;

    @Override
    public void merge(Block block) {
        ImageBlock imageBlock = (ImageBlock) block;
        setCaption(imageBlock.getCaption());

        List<Long> imageIdxList = imageBlock.getImageIdxList();

        imageGroup.getImageList().stream()
                .filter(image -> !imageIdxList.contains(image.getImageIdx()))
                .forEach(image -> imageGroup.getImage(image.getImageIdx()).delete());

        imageBlock.getImageList().stream()
                .filter(image -> image.isNew())
                .forEach(image -> imageGroup.getImageList().add(image));

        imageBlock.getImageList().stream()
                .forEach(image -> imageGroup.getImage(image.getImageIdx()).setSortOrder(image.getSortOrder()));
    }

    public List<Image> getImageList(){
        return imageGroup.getImageList();
    }

    public List<Long> getImageIdxList(){
        return imageGroup.getImageIdxList();
    }
}