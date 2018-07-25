#!/usr/bin/env bash

[[ -z "${GRAALVM_HOME}" ]] && { echo "Please install GraalVM and set env GRAALVM_HOME"  1>&2 ; exit 1; }
[[ "$(command -v java)" ]] || { echo "JDK is not installed" 1>&2 ; exit 1; }
[[ -z "${JAVA_HOME}" ]] && { echo "Please set env JAVA_HOME"  1>&2 ; exit 1; }

sudo cp  $(dirname $0)/lib/hsdis-amd64.so ${GRAALVM_HOME}/jre/lib/amd64/server
sudo cp  $(dirname $0)/lib/hsdis-amd64.so ${JAVA_HOME}/jre/lib/amd64/server

echo ">>> hsdis-amd64.so installed!"