#!/bin/bash

lein pom
scp pom.xml target/rtfc-0.0.2.jar clojars@clojars.org:
