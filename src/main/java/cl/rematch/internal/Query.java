package cl.rematch.internal;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdString;

import cl.rematch.REmatchException;

@Properties(inherit = REmatchConfig.class)
@Namespace("REmatch::library_interface")
public class Query extends Pointer {
    static {
        Loader.load();
    }

    public Query(@Const @ByRef @StdString String pattern, @Cast("REmatch::library_interface::Flags") int flags,
            int maxMempoolDuplications, int maxDeterministicStates, int bufferSize) throws REmatchException {
        allocate(pattern, flags, maxMempoolDuplications, maxDeterministicStates, bufferSize);
    }

    private native void allocate(@Const @ByRef @StdString String pattern,
            @Cast("REmatch::library_interface::Flags") int flags,
            int maxMempoolDuplications, int maxDeterministicStates, int bufferSize) throws REmatchException;

    public native boolean check(@Const @ByRef @StdString String document) throws REmatchException;

    public native boolean check(Reader reader) throws REmatchException;

    public native @ByVal StringVector variables();

    public native @ByVal OptionalMatch findone(@Const @ByRef @StdString String document) throws REmatchException;

    public native @ByVal OptionalMatch findone(Reader reader) throws REmatchException;

    public native @ByVal MatchVector findmany(@Const @ByRef @StdString String document,
            @Cast("uint_fast32_t") int limit) throws REmatchException;

    public native @ByVal MatchVector findmany(Reader reader, @Cast("uint_fast32_t") int limit) throws REmatchException;

    public native @ByVal MatchVector findall(@Const @ByRef @StdString String document) throws REmatchException;

    public native @ByVal MatchVector findall(Reader reader) throws REmatchException;

    public native @ByVal MatchGenerator finditer(@Const @ByRef @StdString String document) throws REmatchException;

    public native @ByVal MatchGenerator finditer(Reader reader) throws REmatchException;
}
