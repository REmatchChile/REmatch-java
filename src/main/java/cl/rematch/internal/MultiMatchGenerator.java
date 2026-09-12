package cl.rematch.internal;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;

import cl.rematch.REmatchException;

@Properties(inherit = REmatchConfig.class)
@Namespace("REmatch")
public class MultiMatchGenerator extends Pointer {
    static {
        Loader.load();
    }

    public MultiMatchGenerator(Pointer p) {
        super(p);
    }

    public native @ByVal MultiMatchIterator begin() throws REmatchException;

    public native @ByVal MultiMatchIterator end();
}
