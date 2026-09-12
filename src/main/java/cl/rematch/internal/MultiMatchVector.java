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
@Name("vector<REmatch::MultiMatch>")
public class MultiMatchVector extends Pointer {
    static {
        Loader.load();
    }

    public MultiMatchVector() {
        allocate();
    }

    private native void allocate();

    public native @Cast("size_t") long size();

    public native @ByVal MultiMatch at(@Cast("size_t") long index);

    public native void push_back(@ByVal MultiMatch value);
}
