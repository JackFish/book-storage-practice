package kr.bookstorage.domain.code;

/**
 * Created by ohjic on 2017-06-22.
 */
public enum MailContentType {
    HTML("text/html; charset=utf-8");

    private String contentType;

    MailContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}