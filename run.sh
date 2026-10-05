#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

mvn compile
java -cp target/classes shell.emulator.Main "$@"