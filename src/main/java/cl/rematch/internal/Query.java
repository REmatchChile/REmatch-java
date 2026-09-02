package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Properties(inherit = REmatchConfig.class)
@Namespace("REmatch::library_interface")
public class Query extends Pointer {
    static {
        Loader.load();
    }

    public Query(@Const @ByRef @StdString String pattern, @Cast("REmatch::library_interface::Flags") int flags,
            int maxMempoolDuplications, int maxDeterministicStates, int bufferSize) {
        allocate(pattern, flags, maxMempoolDuplications, maxDeterministicStates, bufferSize);
    }

    private native void allocate(@Const @ByRef @StdString String pattern,
            @Cast("REmatch::library_interface::Flags") int flags,
            int maxMempoolDuplications, int maxDeterministicStates, int bufferSize);

    public native boolean check(@Const @ByRef @StdString String document);

    public native boolean check(Reader reader);

    public native @ByVal StringVector variables();

    public native @ByVal OptionalMatch findone(@Const @ByRef @StdString String document);

    public native @ByVal OptionalMatch findone(Reader reader);

    public native @ByVal MatchVector findmany(@Const @ByRef @StdString String document,
            @Cast("uint_fast32_t") int limit);

    public native @ByVal MatchVector findmany(Reader reader, @Cast("uint_fast32_t") int limit);

    public native @ByVal MatchVector findall(@Const @ByRef @StdString String document);

    public native @ByVal MatchVector findall(Reader reader);

    public native @ByVal MatchGenerator finditer(@Const @ByRef @StdString String document);

    public native @ByVal MatchGenerator finditer(Reader reader);
}
