package kr.bookstorage.exception;

import kr.bookstorage.exception.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Locale;
import java.util.Set;

/**
 * Created by ksb on 16. 11. 12.
 */
@ControllerAdvice
@Slf4j
public class ResponseDataControllerAdvice {
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    ErrorInfo
    handleBadRequest(HttpServletRequest req, BadRequestException ex) {
        log.error("Exception", ex);
        String message = messageSource.getMessage(ex.getLocalizedMessage(), null, "", Locale.KOREA);

        return new ErrorInfo(req.getRequestURL().toString(), message);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    @ResponseBody ErrorInfo
    handleConflict(HttpServletRequest req, ConflictException ex) {
        log.error("Exception", ex);
        String message = messageSource.getMessage(ex.getLocalizedMessage(), null, "", Locale.KOREA);

        return new ErrorInfo(req.getRequestURL().toString(), message);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody ErrorInfo
    handleForbidden(HttpServletRequest req, ForbiddenException ex) {
        log.error("Exception", ex);
        String message = messageSource.getMessage(ex.getLocalizedMessage(), null, "", Locale.KOREA);

        return new ErrorInfo(req.getRequestURL().toString(), message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody ErrorInfo
    handleNotFound(HttpServletRequest req, NotFoundException ex) {
        log.error("Exception", ex);
        String message = messageSource.getMessage(ex.getLocalizedMessage(), null, "", Locale.KOREA);

        return new ErrorInfo(req.getRequestURL().toString(), message);
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(PreconditionFailedException.class)
    @ResponseBody ErrorInfo
    handlePreconditionFailed(HttpServletRequest req, PreconditionFailedException ex) {
        log.error("Exception", ex);
        String message = messageSource.getMessage(ex.getLocalizedMessage(), null, "", Locale.KOREA);

        return new ErrorInfo(req.getRequestURL().toString(), message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody ErrorInfo
    handleConstraintViolation(HttpServletRequest req, ConstraintViolationException ex) {
        log.error("Exception", ex);
        String message = messageSource.getMessage(getExceptionMessageByConstraintViolationException(ex), null, "", Locale.KOREA);

        return new ErrorInfo(req.getRequestURL().toString(), message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionSystemException.class)
    @ResponseBody ErrorInfo
    handleTransactionSystemException(HttpServletRequest req, TransactionSystemException ex) {
        log.error("Exception", ex);
        String message = messageSource.getMessage(getExceptionMessageByTransactionSystemException(ex), null, "", Locale.KOREA);

        return new ErrorInfo(req.getRequestURL().toString(), message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody ErrorInfo
    handleAccessDenied(HttpServletRequest req, AccessDeniedException ex) {
        log.error("Exception", ex);
        String message = ex.getLocalizedMessage();

        return new ErrorInfo(req.getRequestURL().toString(), message);
    }

    String getExceptionMessageByConstraintViolationException(ConstraintViolationException cve) {
        String exceptionMessage = "";

        Set<ConstraintViolation<?>> cvs = cve.getConstraintViolations();

        for (ConstraintViolation<?> cv : cvs) {
            exceptionMessage = cv.getMessage();
        }

        return exceptionMessage;
    }

    String getExceptionMessageByTransactionSystemException(TransactionSystemException tse) {
        String exceptionMessage = "";
        Throwable t = tse.getCause();
        while ((t != null) && !(t instanceof ConstraintViolationException)) {
            t = t.getCause();
        }
        if (t instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) t;

            Set<ConstraintViolation<?>> cvs = cve.getConstraintViolations();

            for (ConstraintViolation<?> cv : cvs) {
                exceptionMessage = cv.getMessage();
            }
        }
        return exceptionMessage;
    }

}
