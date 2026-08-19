@echo off
cd /d "%~dp0"
if not exist out mkdir out
javac -d out src\citysurvival\*.java
if errorlevel 1 (
  echo Compilation failed. Make sure Java 17+ is installed.
  pause
  exit /b 1
)
java -cp out citysurvival.Main
pause
