package top.lihaoyu.computerstore.service.exception;


public class ProductNotExistsException extends ServiceException{
    public ProductNotExistsException() {
        super();
    }

    public ProductNotExistsException(String message) {
        super(message);
    }

    public ProductNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotExistsException(Throwable cause) {
        super(cause);
    }

    protected ProductNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
