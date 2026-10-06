@echo off
chcp 65001 >nul
setlocal

rem Go to the directory where this script lives (project root)
cd /d "%~dp0"

rem "call" is required: mvn is a .cmd file, without it the script would stop after mvn
call mvn compile
if errorlevel 1 (
    echo Build failed.
    exit /b 1
)

java -Dstdout.encoding=UTF-8 -Dfile.encoding=UTF-8 -cp target\classes shell.emulator.Main %*
endlocal
