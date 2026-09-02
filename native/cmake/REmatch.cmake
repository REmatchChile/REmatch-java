include(FetchContent)

FetchContent_Declare(
    REmatch
    GIT_REPOSITORY https://github.com/REmatchChile/REmatch
    GIT_TAG java-bindings-v2
)

FetchContent_MakeAvailable(REmatch)
