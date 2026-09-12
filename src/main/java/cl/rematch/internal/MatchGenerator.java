package cl.rematch.internal;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;

import cl.rematch.REmatchException;

@Properties(inherit = REmatchConfig.class)
@Namespace("REmatch")
public class MatchGenerator extends Pointer {
    static {
        Loader.load();
    }

    public MatchGenerator(Pointer p) {
        super(p);
    }

    public native @ByVal MatchIterator begin() throws REmatchException;

    public native @ByVal MatchIterator end();
}
