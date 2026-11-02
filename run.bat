@echo off
chcp 65001 >nul
setlocal

cd /d "%~dp0"

where javac >nul 2>nul
if errorlevel 1 (
    if exist "%JAVA_HOME%\bin\javac.exe" (
        set "PATH=%JAVA_HOME%\bin;%PATH%"
    ) else (
        echo Ошибка: не найден компилятор javac.
        echo Установите JDK 21, например Eclipse Temurin: https://adoptium.net
        exit /b 1
    )
)

if not exist target\classes mkdir target\classes

javac -encoding UTF-8 -d target\classes src\main\java\shell\emulator\*.java
if errorlevel 1 (
    echo Ошибка компиляции.
    exit /b 1
)

java -Dstdout.encoding=UTF-8 -Dfile.encoding=UTF-8 -cp target\classes shell.emulator.Main %*
endlocal
