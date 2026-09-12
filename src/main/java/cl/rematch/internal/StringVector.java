package cl.rematch.internal;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdString;

@Properties(inherit = REmatchConfig.class)
@Name("std::vector<std::string>")
public class StringVector extends Pointer {
    static {
        Loader.load();
    }

    public StringVector() {
        allocate();
    }

    private native void allocate();

    public native @Cast("size_t") long size();

    public native @StdString String at(@Cast("size_t") long i);

    public native void push_back(@StdString String value);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (long i = 0; i < size(); i++) {
            sb.append("\"").append(at(i)).append("\"");
            if (i < size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
