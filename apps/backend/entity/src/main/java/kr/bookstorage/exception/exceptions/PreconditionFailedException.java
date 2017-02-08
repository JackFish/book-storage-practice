package kr.bookstorage.exception.exceptions;

/**
 * Created by ksb on 16. 11. 12.
 */
public class PreconditionFailedException extends RuntimeException{

    public PreconditionFailedException(String msg) {
        super(msg);
    }

    public PreconditionFailedException(String msg, Throwable t) {
        super(msg, t);
    }
}