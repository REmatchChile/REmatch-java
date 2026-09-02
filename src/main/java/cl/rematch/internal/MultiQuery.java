package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Properties(inherit = REmatchConfig.class)
@Namespace("REmatch")
public class MultiQuery extends Pointer {
    static {
        Loader.load();
    }

    public MultiQuery(@Const @ByRef @StdString String pattern, @Cast("REmatch::library_interface::Flags") int flags,
            int maxMempoolDuplications, int maxDeterministicStates, int bufferSize) {
        allocate(pattern, flags, maxMempoolDuplications, maxDeterministicStates, bufferSize);
    }

    private native void allocate(@Const @ByRef @StdString String pattern,
            @Cast("REmatch::library_interface::Flags") int flags,
            int maxMempoolDuplications, int maxDeterministicStates, int bufferSize);

    public native boolean check(@Const @ByRef @StdString String document);

    public native boolean check(Reader reader);

    public native @ByVal StringVector variables();

    public native @ByVal OptionalMultiMatch findone(String document);

    public native @ByVal OptionalMultiMatch findone(Reader reader);

    public native @ByVal MultiMatchVector findmany(@Const @ByRef @StdString String document,
            @Cast("uint_fast32_t") int limit);

    public native @ByVal MultiMatchVector findmany(Reader reader, @Cast("uint_fast32_t") int limit);

    public native @ByVal MultiMatchVector findall(@Const @ByRef @StdString String document);

    public native @ByVal MultiMatchVector findall(Reader reader);

    public native @ByVal MultiMatchGenerator finditer(@Const @ByRef @StdString String document);

    public native @ByVal MultiMatchGenerator finditer(Reader reader);
}
