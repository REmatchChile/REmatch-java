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
@Namespace("REmatch")
public class MultiMatch extends Pointer {
    static {
        Loader.load();
    }

    public MultiMatch(Pointer p) {
        super(p);
    }

    public native @ByVal SpanVector spans(@Cast("uint_fast32_t") int variable_id) throws REmatchException;

    public native @ByVal SpanVector spans(@Const @ByRef @StdString String variable_name) throws REmatchException;

    public native @ByVal StringVector groups(@Cast("uint_fast32_t") int variable_id) throws REmatchException;

    public native @ByVal StringVector groups(@Const @ByRef @StdString String variable_name) throws REmatchException;

    public native @ByVal MultiMatch submatch(@ByVal Span span);

    public native boolean empty();

    public native @ByVal StringVector variables();

    public native @StdString String to_string();
}
