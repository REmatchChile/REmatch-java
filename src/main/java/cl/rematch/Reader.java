package cl.rematch;

import java.nio.file.Path;

/**
 * The Reader works as a wrapper for a file.
 */
public class Reader {
    /**
     * Reader used internally.
     */
    public final cl.rematch.internal.ReaderWrapper cppReader;

    /**
     * Creates a new Reader for the file at the specified path.
     * 
     * @param path the path of the file.
     */
    public Reader(String path) throws REmatchException {
        this.cppReader = new cl.rematch.internal.ReaderWrapper(path);
    }

    /**
     * Creates a new Reader for the file at the specified path.
     * 
     * @param path a Path object.
     */
    public Reader(Path path) throws REmatchException {
        this.cppReader = new cl.rematch.internal.ReaderWrapper(path.toString());
    }
}
