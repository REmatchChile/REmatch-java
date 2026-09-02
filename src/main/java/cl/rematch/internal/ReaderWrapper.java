package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Properties(inherit = REmatchConfig.class)
@Name("ReaderWrapperJava")
public class ReaderWrapper extends Pointer {
    static {
        Loader.load();
    }

    public ReaderWrapper(String path) {
        allocate(path);
    }

    private native void allocate(@StdString String path);

    public native @ByRef Reader get();
}
