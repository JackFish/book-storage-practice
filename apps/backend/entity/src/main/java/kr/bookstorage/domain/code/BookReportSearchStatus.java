package kr.bookstorage.domain.code;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by ksb on 16. 11. 16.
 */
public enum BookReportSearchStatus {
    SUBJECT("제목"),
    BOOK_RECORD_SUBJECT("책제목"),
    TAG("태그");

    private String value;

    BookReportSearchStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @JsonValue
    public String getCode(){
        return this.name();
    }

}
