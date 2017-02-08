package kr.bookstorage.domain.code;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by ksb on 16. 11. 14.
 */
public enum BookRecordSearchStatus {
    SUBJECT("제목"),
    AUTHOR("저자"),
    PUBLISHER("출판사"),
    SUMMARY("요약");

    private String value;

    BookRecordSearchStatus(String value) {
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
