package kr.bookstorage.exception.exceptions;

/**
 * Created by ksb on 16. 11. 12.
 */
public class ConflictException extends RuntimeException {

    public ConflictException(String msg) {
        super(msg);
    }

    public ConflictException(String msg, Throwable t) {
        super(msg, t);
    }

}