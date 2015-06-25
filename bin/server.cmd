cd %~dp0
cd ..
set PROJECT_HOME=%CD%

java -jar "%PROJECT_HOME%\lib\selenium-server-standalone-2.25.0.jar" -singleWindow

pause