#!/usr/bin/env bash

if [ -z "${GRAALVM_HOME}" ]; then
    echo "Please install GraalVM and set env GRAALVM_HOME" && exit 1
else
    ${GRAALVM_HOME}/bin/java -jar target/benchmark-1.0-SNAPSHOT.jar
fi