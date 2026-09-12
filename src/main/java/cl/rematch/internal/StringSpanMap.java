package cl.rematch.internal;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdString;

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
