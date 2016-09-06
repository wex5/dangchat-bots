@REM dangchat-bots launcher script
@REM
@REM Environment:
@REM JAVA_HOME - location of a JDK home dir (optional if java on path)
@setlocal enabledelayedexpansion

@echo off

if "%DANGCHAT_BOTS_HOME%"=="" set "DANGCHAT_BOTS_HOME=%~dp0\\.."
echo "%DANGCHAT_BOTS_HOME%"

java -jar -Xms256M -Xmx256M -server %DANGCHAT_BOTS_HOME%/lib/dangchat-bots-0.8.jar