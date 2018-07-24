#!/usr/bin/env bash

[[ -z "${GRAALVM_HOME}" ]] && { echo "Please install GraalVM and set env GRAALVM_HOME"  1>&2 ; exit 1; }

mvn compile
cd target
${GRAALVM_HOME}/bin/native-image -cp classes rocks.huanglei.DispatchExample