#!/usr/bin/env bash

echo '{"GraalVM":{"description":"Language Abstraction Platform","supports":["combining languages","embedding languages","creating native images"],"languages": ["Java","JavaScript","Node.js", "Python", "Ruby","R","LLVM"]}}' |\
 $(dirname $0)/target/rocks.huanglei.nativepolyglotdemo