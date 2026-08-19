#!/bin/bash
cd "$(dirname "$0")"
mkdir -p out
javac -d out src/citysurvival/*.java || exit 1
java -cp out citysurvival.Main
