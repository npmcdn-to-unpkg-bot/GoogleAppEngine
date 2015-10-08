echo OFF
set PROJECT_HOME=c:

echo You backup Windows project file will be overwritten, are you sure (ctrl + c/close to exit) ?
pause
del %PROJECT_HOME%\GoogleAppEngine\bin\.classpath.win2
echo saving '%PROJECT_HOME%\GoogleAppEngine\.classpath' to '%PROJECT_HOME%\GoogleAppEngine\bin\.classpath.win2' ...
copy %PROJECT_HOME%\GoogleAppEngine\.classpath %PROJECT_HOME%\GoogleAppEngine\bin\.classpath.win2

pause