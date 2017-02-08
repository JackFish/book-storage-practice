package kr.bookstorage.exception.exceptions;

/**
 * Created by ksb on 16. 11. 12.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(String msg, Throwable t) {
        super(msg, t);
    }

}
