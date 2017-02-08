package kr.bookstorage.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Created by ksb on 16. 11. 9.
 */
@Entity
@DiscriminatorValue("DRAFT")
@Data
public class DraftBlock extends Block {

    @Lob
    @Column(name = "PLAIN_TEXT")
    private String plainText;

    @Lob
    @Column(name = "CONTENT_STATE_JSON")
    private String contentStateJson;

    @Override
    public void merge(Block block) {
        DraftBlock draftBlock = (DraftBlock) block;
        setPlainText(draftBlock.getPlainText());
        setContentStateJson(draftBlock.getContentStateJson());
    }
}
