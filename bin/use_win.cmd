set PROJECT_HOME=c:

echo 'copying ' %PROJECT_HOME%/GoogleAppEngine/bin/.classpath.win ' to ' %PROJECT_HOME%/GoogleAppEngine/.classpath ' ...'
copy %PROJECT_HOME%\GoogleAppEngine\bin\.classpath.win %PROJECT_HOME%\GoogleAppEngine\.classpath

pause