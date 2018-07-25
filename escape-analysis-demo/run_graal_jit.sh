#! /bin/bash

${GRAALVM_HOME}/bin/java -cp target/classes -XX:+UnlockDiagnosticVMOptions \
        -XX:+PrintAssembly -XX:-TieredCompilation -Xcomp \
        -XX:CompileCommand=compileonly,rocks/huanglei/EADemo,main \
        "-XX:CompileCommand=dontinline,java/*,*" -XX:+PrintCompilation \
        -XX:-UseCompressedOops rocks.huanglei.EADemo
