package top.lihaoyu.computerstore.service.exception;


public class OriginalPasswordNotMatchException extends ServiceException{
    public OriginalPasswordNotMatchException() {
        super();
    }

    public OriginalPasswordNotMatchException(String message) {
        super(message);
    }

    public OriginalPasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public OriginalPasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    protected OriginalPasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
