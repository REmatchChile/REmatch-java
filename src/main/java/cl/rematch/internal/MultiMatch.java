package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Properties(inherit = REmatchConfig.class)
@Namespace("REmatch")
public class MultiMatch extends Pointer {
    static {
        Loader.load();
    }

    public MultiMatch(Pointer p) {
        super(p);
    }

    public native @ByVal SpanVector spans(@Cast("uint_fast32_t") int variable_id);

    public native @ByVal SpanVector spans(@Const @ByRef @StdString String variable_name);

    public native @ByVal StringVector groups(@Cast("uint_fast32_t") int variable_id);

    public native @ByVal StringVector groups(@Const @ByRef @StdString String variable_name);

    public native @ByVal MultiMatch submatch(@ByVal Span span);

    public native boolean empty();

    public native @ByVal StringVector variables();

    public native @StdString String to_string();
}
