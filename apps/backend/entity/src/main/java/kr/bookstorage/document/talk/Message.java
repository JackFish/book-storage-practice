package kr.bookstorage.document.talk;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Document(indexName = "message", type = "message", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
@NoArgsConstructor
public class Message {

    @Id
    private long idx;

    private String content;

    private boolean enabled;

    private Participant participant;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    @Field(type = FieldType.Date, index = FieldIndex.not_analyzed, store = true,
            format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    @Field(type = FieldType.Date, index = FieldIndex.not_analyzed, store = true,
            format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date lastModifiedDate;

}
