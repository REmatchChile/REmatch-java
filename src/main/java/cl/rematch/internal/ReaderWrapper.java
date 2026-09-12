package cl.rematch.internal;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdString;

import cl.rematch.REmatchException;

@Properties(inherit = REmatchConfig.class)
@Name("ReaderWrapperJava")
public class ReaderWrapper extends Pointer {
    static {
        Loader.load();
    }

    public ReaderWrapper(String path) throws REmatchException {
        allocate(path);
    }

    private native void allocate(@StdString String path) throws REmatchException;

    public native @ByRef Reader get();
}
