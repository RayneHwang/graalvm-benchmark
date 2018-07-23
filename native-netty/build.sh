#!/usr/bin/env bash

GRAALVM_HOME=/opt/graalvm
mvn install:install-file -Dfile=${GRAALVM_HOME}/jre/lib/svm/builder/svm.jar -DgroupId=com.oracle.substratevm -DartifactId=svm -Dversion=GraalVM-1.0.0-rc1 -Dpackaging=jar
mvn clean package
/opt/graalvm/bin/native-image -jar target/native-netty-1.0-SNAPSHOT.jar -H:ReflectionConfigurationResources=reflection_config.json