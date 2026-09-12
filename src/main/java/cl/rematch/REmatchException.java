package cl.rematch;

/**
 * REmatchException is thrown when an error occurs in the native library.
 */
public class REmatchException extends RuntimeException {
    /**
     * Creates a new Exception.
     * 
     * @param message Message of the exception.
     */
    public REmatchException(String message) {
        super(message);
    }
}
