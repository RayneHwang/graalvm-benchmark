#!/usr/bin/env bash

[[ -z "${GRAALVM_HOME}" ]] && { echo "Please install GraalVM and set env GRAALVM_HOME"  1>&2 ; exit 1; }

mvn install:install-file -Dfile=${GRAALVM_HOME}/jre/lib/boot/graal-sdk.jar -DgroupId=org.graalvm -DartifactId=graal-sdk -Dversion=GraalVM-1.0.0-rc1 -Dpackaging=jar

sudo chmod 777 ${GRAALVM_HOME}/jre/languages/js/asm-debug-all.jar
mvn compile
cd $(dirname $0)/target && ${GRAALVM_HOME}/bin/native-image --language:js -cp classes rocks.huanglei.NativePolyglotDemo