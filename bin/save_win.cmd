echo OFF
set PROJECT_HOME=c:

echo You backup Windows project file will be overwritten, are you sure (ctrl + c/close to exit) ?
pause
del %PROJECT_HOME%\GoogleAppEngine\bin\.classpath.win
echo saving '%PROJECT_HOME%\GoogleAppEngine\.classpath' to '%PROJECT_HOME%\GoogleAppEngine\bin\.classpath.win' ...
copy %PROJECT_HOME%\GoogleAppEngine\.classpath %PROJECT_HOME%\GoogleAppEngine\bin\.classpath.win

pause