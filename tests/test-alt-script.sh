#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_DIR="$(cd "$SCRIPT_DIR/.." && pwd)"

"$PROJECT_DIR/run.sh" \
    "$PROJECT_DIR/vfs" \
    "$PROJECT_DIR/scripts/test-alt-script.txt"