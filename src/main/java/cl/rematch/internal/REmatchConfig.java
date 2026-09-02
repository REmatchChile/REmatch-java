package cl.rematch.internal;

import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

@Properties(
    value = {
        @Platform(
            include = {
                "REmatch/REmatch.hpp",
                "REmatch/span.hpp",
                "REmatch/constants.hpp",
                "REmatch/exceptions.hpp",
                "REmatch/flags.hpp",
                "REmatch/fstream_reader.hpp",
                "REmatch/iterator.hpp",
                "REmatch/match.hpp",
                "REmatch/match_generator.hpp",
                "REmatch/multi_iterator.hpp",
                "REmatch/multi_match.hpp",
                "REmatch/multi_match_generator.hpp",
                "REmatch/multi_query.hpp",
                "REmatch/query.hpp",
                "reader_wrapper_java.hpp"
            },            
            link = "REmatch",
            library = "jniREmatch"
        )
    }
)
public class REmatchConfig implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
    }
}
