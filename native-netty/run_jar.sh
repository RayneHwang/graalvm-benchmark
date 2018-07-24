#! /bin/bash

[[ -z "${GRAALVM_HOME}" ]] && { echo "Please install GraalVM and set env GRAALVM_HOME"  1>&2 ; exit 1; }

${GRAALVM_HOME}/bin/java -jar target/native-netty-1.0-SNAPSHOT.jar
