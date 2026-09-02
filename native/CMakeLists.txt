cmake_minimum_required(VERSION 3.10)
project(JavaREmatch)

# Find JavaCPP
find_package(Java REQUIRED)
include(UseJava)

set(JAVACPP_VERSION "1.5.13" CACHE STRING "JavaCPP version")

find_program(MAVEN_EXECUTABLE mvn REQUIRED)
if(WIN32)
    set(MAVEN_COMMAND cmd.exe /c "${MAVEN_EXECUTABLE}")
else()
    set(MAVEN_COMMAND "${MAVEN_EXECUTABLE}")
endif()
execute_process(
    COMMAND ${MAVEN_COMMAND} help:evaluate "-Dexpression=settings.localRepository" -q -DforceStdout
    OUTPUT_VARIABLE MAVEN_REPO
    OUTPUT_STRIP_TRAILING_WHITESPACE
)

find_jar(
    JAVACPP_JAR
    NAMES "javacpp-${JAVACPP_VERSION}"
    PATHS "${MAVEN_REPO}/org/bytedeco/javacpp"
    PATH_SUFFIXES "${JAVACPP_VERSION}"
)
if(NOT JAVACPP_JAR)
    message(FATAL_ERROR"Could not find JavaCPP ${JAVACPP_VERSION} JAR")
endif()

file(GLOB_RECURSE JAVACPP_SOURCES
    "${CMAKE_SOURCE_DIR}/../src/main/java/cl/rematch/internal/*.java"
)

file(GLOB JAVA_SOURCES
    "${CMAKE_SOURCE_DIR}/../src/main/java/cl/rematch/*.java"
)

set(NATIVE_PATH
    "${CMAKE_SOURCE_DIR}"
)

# Install REmatch
include(FetchContent)

FetchContent_Declare(
    REmatch
    GIT_REPOSITORY https://github.com/REmatchChile/REmatch
    GIT_TAG mvn
)
FetchContent_MakeAvailable(REmatch)

set(REMATCH_INSTALL_DIR "${CMAKE_BINARY_DIR}/REmatch-install")

set(REMATCH_INCLUDE_DIR "${REMATCH_INSTALL_DIR}/include")
set(REMATCH_LIB_DIR "${REMATCH_INSTALL_DIR}/lib")
set(REMATCH_BIN_DIR "${REMATCH_INSTALL_DIR}/bin")

add_custom_target(InstallREmatch
    COMMAND ${CMAKE_COMMAND}
            --install ${CMAKE_BINARY_DIR}
            --prefix ${REMATCH_INSTALL_DIR}
    DEPENDS
        REmatch
        rematch-cli
)

# Name of the generated library
set(JNI_WRAPPER_LIBRARY jniREmatch)

set(JAVA_CLASSES_STAMP "${CMAKE_BINARY_DIR}/compiled.stamp")

# Compile JavaCPP sources
add_custom_command(
    OUTPUT ${JAVA_CLASSES_STAMP}
    COMMAND ${Java_JAVAC_EXECUTABLE} 
            -cp ${JAVACPP_JAR}
            -d ${CMAKE_BINARY_DIR}/classes
            ${JAVACPP_SOURCES}
    COMMAND ${CMAKE_COMMAND} -E touch ${JAVA_CLASSES_STAMP}
    DEPENDS ${JAVACPP_SOURCES}
)

set(JAVA_OPTS
    -cp "${CMAKE_BINARY_DIR}/classes"
    -o ${JNI_WRAPPER_LIBRARY}
)

if(WIN32)
    list(APPEND JAVA_OPTS
        -Xcompiler "/std:c++17"
        -Xcompiler "/MT"
    )
else()
    list(APPEND JAVA_OPTS
        -Xcompiler "-std=c++17"
    )
endif()

set(REMATCH_JAVA_INCLUDES
    ${REMATCH_INCLUDE_DIR}
    ${NATIVE_PATH}
)

cmake_path(CONVERT "${REMATCH_JAVA_INCLUDES}" TO_NATIVE_PATH_LIST REMATCH_JAVA_INCLUDES)

set(JNI_BUILD_STAMP "${CMAKE_BINARY_DIR}/build.stamp")

# Compile the bindings
add_custom_command(
    OUTPUT ${JNI_BUILD_STAMP}
    COMMAND ${Java_JAVA_EXECUTABLE}
            -jar ${JAVACPP_JAR}
            ${JAVA_OPTS}
            "-Dplatform.includepath=${REMATCH_JAVA_INCLUDES}"
            "-Dplatform.linkpath=${REMATCH_LIB_DIR}"
            cl.rematch.internal.REmatchConfig
            cl.rematch.internal.Flags
            cl.rematch.internal.Query
            cl.rematch.internal.Match
            cl.rematch.internal.OptionalMatch
            cl.rematch.internal.OptionalMultiMatch
            cl.rematch.internal.Span
            cl.rematch.internal.StringSpanMap
            cl.rematch.internal.MatchIterator
            cl.rematch.internal.MatchGenerator
            cl.rematch.internal.StringVector
            cl.rematch.internal.MatchVector
            cl.rematch.internal.SpanVector
            cl.rematch.internal.MultiQuery
            cl.rematch.internal.MultiMatch
            cl.rematch.internal.MultiMatchVector
            cl.rematch.internal.MultiMatchIterator
            cl.rematch.internal.MultiMatchGenerator
            cl.rematch.internal.Reader
            cl.rematch.internal.ReaderWrapper
    DEPENDS
        ${JAVA_CLASSES_STAMP}
        InstallREmatch
    VERBATIM
)

# Target
add_custom_target(GenerateJNIWrapper ALL
    DEPENDS ${JNI_BUILD_STAMP}
)
