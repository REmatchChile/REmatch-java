#pragma once

#include <fstream>
#include <memory>
#include "REmatch/exceptions.hpp"
#include "REmatch/fstream_reader.hpp"

#include <iostream>
#include <filesystem>

using namespace REmatch;

struct ReaderWrapperJava {
  explicit ReaderWrapperJava(const std::string& path) {
    stream.open(path, std::ios::in | std::ios::binary);
    if (!stream) {
      throw REmatchException("Could not open file " + path);
    }
    reader = std::make_unique<FStreamReader>(stream);
  }

  Reader& get() const { return *reader; }

  std::fstream stream;
  std::unique_ptr<FStreamReader> reader;
};
