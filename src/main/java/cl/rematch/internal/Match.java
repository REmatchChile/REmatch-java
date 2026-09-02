package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Properties(inherit = REmatchConfig.class)
@Namespace("REmatch")
public class Match extends Pointer {
    static {
        Loader.load();
    }

    public Match(Pointer p) {
        super(p);
    }

    public native long start(@Const @ByRef @StdString String variable_name);

    public native long start(@Cast("uint_fast32_t") int variable_id);

    public native long end(@Const @ByRef @StdString String variable_name);

    public native long end(@Cast("uint_fast32_t") int variable_id);

    public native @StdString String group(@Const @ByRef @StdString String variable_name);

    public native @StdString String group(@Cast("uint_fast32_t") int variable_id);

    public native @ByVal Span span(@Const @ByRef @StdString String variable_name);

    public native @ByVal Span span(@Cast("uint_fast32_t") int variable_id);

    public native @ByVal StringSpanMap groupdict();

    public native @ByVal StringVector variables();

    public native boolean empty();

    public native @StdString String to_string();
}