package cl.rematch;

/**
 * The Reader works as a wrapper for a file.
 */
public class Reader {
    public final cl.rematch.internal.ReaderWrapper cppReader;

    /**
     * Creates a new Reader for the file at the specified path.
     * 
     * @param path the path of the file.
     */
    public Reader(String path) {
        this.cppReader = new cl.rematch.internal.ReaderWrapper(path);
    }
}
