
# REmatch bindings for Java

Java bindings for REmatch, an information extraction focused regex library that uses constant delay algorithms.

* [REmatch's Official Website](https://rematch.cl)
* [Project Wiki](https://github.com/REmatchChile/REmatch/wiki)

## Usage

To use REmatch on your own Java project you can download it using:

* Maven:

```xml
    <dependency>
      <groupId>cl.rematch</groupId>
      <artifactId>rematch-platform</artifactId>
      <version>0.1.3</version>
      <type>pom</type>
    </dependency>
```

<!-- * Gradle:

```kts
    implementation("cl.rematch:rematch:0.1.3")
    implementation("cl.rematch:rematch:0.1.3:linux-x86_64")
``` -->

## Examples

In this example we find all the matches using findIter:

```java
package com.example;

import cl.rematch.Match;
import cl.rematch.Query;

public class App {
    public void main() {
        String document = "cperez@gmail.com\npvergara@ing.uc.cl\njuansoto@uc.cl";
        String pattern = "@!domain{(\\w+\\.)+\\w+}(\\n|$)";

        Query query = new Query(pattern);

        for (Match m : query.findIter(document)) {
            System.out.println(m);
        }
    }
}
```

We can find the first match using findOne:

```java
package com.example;

import cl.rematch.Match;
import cl.rematch.Query;

public class App {
    public void main() {
        String document = "cperez@gmail.com\npvergara@ing.uc.cl\njuansoto@uc.cl";
        String pattern = "@!domain{(\\w+\\.)+\\w+}(\\n|$)";

        Query query = new Query(pattern);
        Match match = query.findOne(document);

        if (match != null) {
            System.out.println(match);
        } else {
            System.out.println("No match found");
        }
    }
}
```

We can also use Reader to find matches in a file:

```java
package com.example;

import java.nio.file.Path;

import cl.rematch.Match;
import cl.rematch.Query;
import cl.rematch.Reader;

public class App {
    public void main() {
        Reader reader = new Reader(Path.of("data", "document.txt"));
        String pattern = "@!domain{(\\w+\\.)+\\w+}(\\n|$)";

        Query query = new Query(pattern);

        for (Match m : query.findIter(reader)) {
            System.out.println(m);
        }
    }
}
```

## Build

We use CMake to install the REmatch cpp library temporarily in `build/REmatch-install`, so that we can then build the JavaCPP bindings and link the native library.

```bash
cmake -Snative -Bbuild -DBUILD_SHARED_LIBS=ON -DCMAKE_TYPE_BUILD=Release
cmake --build build --config Release
```

Once the bindings are compiled, we can use the mvn commands normally.

```bash
mvn compile test
```

## Install

To install this project we need to install the main JAR and a platform POM.

```
mvn install
mvn -f pom-platform.xml install
```
