#!/usr/bin/env bash

[[ -z "${GRAALVM_HOME}" ]] && { echo "Please install GraalVM and set env GRAALVM_HOME"  1>&2 ; exit 1; }

mvn install:install-file -Dfile=${GRAALVM_HOME}/jre/lib/svm/builder/svm.jar -DgroupId=com.oracle.substratevm -DartifactId=svm -Dversion=GraalVM-1.0.0-rc1 -Dpackaging=jar
mvn clean package
${GRAALVM_HOME}/bin/native-image -jar $(dirname $0)/native-netty-1.0-SNAPSHOT.jar -H:ReflectionConfigurationResources=reflection_config.json