#!/bin/bash

lein clean
lein pom
lein jar
scp pom.xml target/rtfc-*.jar clojars@clojars.org:
