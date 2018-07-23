# Graal Demos
A set of benchmark and test projects for [Oracle GraalVM](https://www.graalvm.org/)

## Contents

- [top-ten](https://github.com/RayneHwang/graalvm-benchmark/tree/master/top-ten): benchmark GraalVM Graal JIT compiler
- [native-demo](https://github.com/RayneHwang/graalvm-benchmark/tree/master/native-demo): single file native image building example
- [native-dispatch](https://github.com/RayneHwang/graalvm-benchmark/tree/master/native-dispatch): single jar native image building example
- [native-netty](https://github.com/RayneHwang/graalvm-benchmark/tree/master/native-netty): native netty image building example(credits to [cstancu](https://github.com/cstancu/netty-native-demo))
- [native-polyglot](https://github.com/RayneHwang/graalvm-benchmark/tree/master/native-polyglot): native polyglot image build example (Java and Javascript)
- [polyglot-c](https://github.com/RayneHwang/graalvm-benchmark/tree/master/polyglot-c): C and Javascript interop example



## Requires
- maven
- [GraalVM release](https://www.graalvm.org/downloads/)(graalvm-ee-1.0.0-RC4)
- clang ( frontend for LLVM )