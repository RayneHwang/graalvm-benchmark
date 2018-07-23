#!/usr/bin/env bash


clang -g -O1 -c -emit-llvm -I/opt/graalvm/jre/languages/llvm polyglot.c
/opt/graalvm/bin/lli --polyglot --jvm polyglot.bc