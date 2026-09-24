@echo off
setlocal
cd /d "%~dp0"
echo ========================================================
echo   Student Course Registration System - Build and Run
echo ========================================================

where javac >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] JDK javac was not found in PATH.
    echo Please install JDK version 11 or higher and add it to PATH.
    pause
    exit /b 1
)

if not exist "bin" mkdir "bin"

echo Compiling Java source files...
javac -encoding UTF-8 -d bin src\app\*.java src\data\*.java src\model\*.java src\service\*.java src\ui\*.java src\utils\*.java
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Compilation failed.
    pause
    exit /b 1
)

echo [OK] Compilation successful.
echo Launching Application...
start javaw -cp bin app.Main
exit /b 0
