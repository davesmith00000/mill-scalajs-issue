#!/bin/bash

set -e

cd mylib
sbt sharedJS/publishLocal
cd ..

cd foo-sbt
sbt foo/fastOptJS
cd ..

cd foo-mill
mill foo.fastOpt
cd ..
