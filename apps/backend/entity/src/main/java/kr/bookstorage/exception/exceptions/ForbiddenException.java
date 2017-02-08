package kr.bookstorage.exception.exceptions;

/**
 * Created by ksb on 16. 11. 12.
 */
public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String msg) {
        super(msg);
    }

    public ForbiddenException(String msg, Throwable t) {
        super(msg, t);
    }

}