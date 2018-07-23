#!/usr/bin/env bash

mvn install:install-file -Dfile=/opt/graalvm/jre/lib/boot/graal-sdk.jar -DgroupId=org.graalvm -DartifactId=graal-sdk -Dversion=GraalVM-1.0.0-rc1 -Dpackaging=jar

sudo chmod 777 /opt/graalvm-ee-1.0.0-rc4/jre/languages/js/asm-debug-all.jar
mvn compile
cd target && /opt/graalvm/bin/native-image --language:js -cp classes rocks.huanglei.NativePolyglotDemo