package kr.bookstorage.exception;

/**
 * Created by ksb on 16. 11. 12.
 */
public class ErrorInfo {

    public final String url;
    public final String message;

    public ErrorInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.message = ex.getLocalizedMessage();
    }

}
