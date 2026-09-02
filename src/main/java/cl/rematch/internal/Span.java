package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Properties(inherit = REmatchConfig.class)
@Name("std::pair<int64_t, int64_t>")
@Opaque
public class Span extends Pointer {
    static {
        Loader.load();
    }

    public Span() {
        allocate();
    }

    private native void allocate();

    public Span(long first, long second) {
        allocate(first, second);
    }

    private native void allocate(long first, long second);

    @MemberGetter
    public native long first();

    @MemberSetter
    public native void first(long value);

    @MemberGetter
    public native long second();

    @MemberSetter
    public native void second(long value);
}
