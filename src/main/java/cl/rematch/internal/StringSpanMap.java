package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Properties(inherit = REmatchConfig.class)
@Name("std::map<std::string, Span>")
public class StringSpanMap extends Pointer {
    static {
        Loader.load();
    }

    public StringSpanMap() {
        allocate();
    }

    private native void allocate();

    public native @ByRef Span at(@StdString String key);

    public native @Cast("size_t") long size();

    public native boolean count(@StdString String key);
}
