package cl.rematch.internal;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = REmatchConfig.class)
@Name("std::optional<REmatch::Match>")
public class OptionalMatch extends Pointer {
    static {
        Loader.load();
    }

    @Name("has_value")
    public native boolean hasValue();

    @Name("value")
    public native @ByVal Match value();
}
