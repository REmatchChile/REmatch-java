package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Properties(inherit = REmatchConfig.class)
@Namespace("REmatch::library_interface")
public class Flags extends Pointer {
    static {
        Loader.load();
    }

    public static final int NONE = 0;
    public static final int LINE_BY_LINE = 1;

    private int value;

    public Flags() {
        this.value = NONE;
        allocate();
    }

    public Flags(int value) {
        this.value = value;
    }

    public int getVal() {
        return this.value;
    }

    public static Flags NONE() {
        return new Flags(NONE);
    }

    public static Flags LINE_BY_LINE() {
        return new Flags(LINE_BY_LINE);
    }

    private native void allocate();
}
