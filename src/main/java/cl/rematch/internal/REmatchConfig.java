package cl.rematch.internal;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(target = "cl.rematch.internal.REmatchConfig", value = {
        @Platform(include = {
                "REmatch/REmatch.hpp",
                "reader_wrapper_java.hpp",
        }, define = {
                "GENERIC_EXCEPTION_CLASS REmatch::REmatchException"
        }, link = "REmatch", library = "jniREmatch")
})
public class REmatchConfig implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
    }
}
