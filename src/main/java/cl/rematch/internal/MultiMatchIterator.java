package cl.rematch.internal;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByPtr;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;

import cl.rematch.REmatchException;

@Properties(inherit = REmatchConfig.class)
@Namespace("REmatch")
@Name("MultiIterator")
public class MultiMatchIterator extends Pointer {
    static {
        Loader.load();
    }

    @Name("operator*")
    public native @ByRef MultiMatch operator_star();

    @Name("operator->")
    public native @ByPtr MultiMatch operator_arrow();

    @Name("operator++")
    public native @ByRef MultiMatchIterator operator_increment() throws REmatchException;

    @Name("operator==")
    public native boolean operatorEquals(@ByRef MultiMatchIterator other);

    @Name("operator!=")
    public native boolean operatorNotEquals(@ByRef MultiMatchIterator other);
}
