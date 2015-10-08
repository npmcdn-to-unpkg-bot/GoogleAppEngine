set PROJECT_HOME=c:

echo 'copying ' %PROJECT_HOME%/GoogleAppEngine/bin/.classpath.win2 ' to ' %PROJECT_HOME%/GoogleAppEngine/.classpath ' ...'
copy %PROJECT_HOME%\GoogleAppEngine\bin\.classpath.win2 %PROJECT_HOME%\GoogleAppEngine\.classpath

pause