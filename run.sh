#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

if ! command -v javac >/dev/null 2>&1; then
    if [ -n "$JAVA_HOME" ] && [ -x "$JAVA_HOME/bin/javac" ]; then
        PATH="$JAVA_HOME/bin:$PATH"
        export PATH
    else
        echo "Ошибка: не найден компилятор javac."
        echo "Установите JDK 21 (например: sudo pacman -S jdk21-openjdk)."
        exit 1
    fi
fi

mkdir -p target/classes

javac -encoding UTF-8 -d target/classes src/main/java/shell/emulator/*.java
if [ $? -ne 0 ]; then
    echo "Ошибка компиляции."
    exit 1
fi

java -cp target/classes shell.emulator.Main "$@"
