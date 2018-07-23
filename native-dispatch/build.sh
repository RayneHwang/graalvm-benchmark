#!/usr/bin/env bash

mvn compile
cd target
/opt/graalvm/bin/native-image -cp classes rocks.huanglei.DispatchExample