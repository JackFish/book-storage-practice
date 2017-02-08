package kr.bookstorage.exception.exceptions;

/**
 * Created by ksb on 16. 11. 12.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

}