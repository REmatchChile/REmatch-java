package cl.rematch.internal;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = REmatchConfig.class)
@Namespace("std")
@Name("vector<std::pair<int64_t, int64_t>>")
public class SpanVector extends Pointer {
    static {
        Loader.load();
    }

    public SpanVector() {
        allocate();
    }

    private native void allocate();

    public native @Cast("size_t") long size();

    public native @ByVal Span at(@Cast("size_t") long index);
}
