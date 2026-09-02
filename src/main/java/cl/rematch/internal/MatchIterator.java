package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Properties(inherit = REmatchConfig.class)
@Namespace("REmatch")
@Name("Iterator")
public class MatchIterator extends Pointer {
    static {
        Loader.load();
    }

    @Name("operator*")
    public native @ByRef Match operator_star();

    @Name("operator->")
    public native @ByPtr Match operator_arrow();

    @Name("operator++")
    public native @ByRef MatchIterator operator_increment();

    @Name("operator==")
    public native boolean operatorEquals(@ByRef MatchIterator other);

    @Name("operator!=")
    public native boolean operatorNotEquals(@ByRef MatchIterator other);
}
