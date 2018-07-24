#!/usr/bin/env bash

[[ "$(command -v clang)" ]] || { echo "clang is not installed" 1>&2 ; exit 1; }

[[ -z "${GRAALVM_HOME}" ]] && { echo "Please install GraalVM and set env GRAALVM_HOME"  1>&2 ; exit 1; }

clang -g -O1 -c -emit-llvm -I/opt/graalvm/jre/languages/llvm polyglot.c
${GRAALVM_HOME}/bin/lli --polyglot --jvm polyglot.bc
