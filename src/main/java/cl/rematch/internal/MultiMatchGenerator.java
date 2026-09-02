package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Properties(inherit = REmatchConfig.class)
@Namespace("REmatch")
public class MultiMatchGenerator extends Pointer {
    static {
        Loader.load();
    }

    public MultiMatchGenerator(Pointer p) {
        super(p);
    }

    public native @ByVal MultiMatchIterator begin();

    public native @ByVal MultiMatchIterator end();
}
