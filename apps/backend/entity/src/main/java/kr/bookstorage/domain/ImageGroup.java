package kr.bookstorage.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ksb on 2016-08-07.
 */
@Entity
@EqualsAndHashCode(exclude = "imageList")
@Table(name = "IMAGE_GROUP")
@Data
public class ImageGroup {
    @Id
    @Column(name = "IMAGE_GROUP_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageGroupIdx;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "imageGroup", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @OrderBy("sortOrder asc, imageIdx asc")
    @Where(clause = " enabled = true ")
    private List<Image> imageList;

    public List<Long> getImageIdxList(){
        return imageList.stream().map(image -> image.getImageIdx()).collect(Collectors.toList());
    }

    public Image getImage(long idx){
        return imageList.stream().filter(image -> image.getImageIdx() == idx).findFirst().orElse(null);
    }

}
