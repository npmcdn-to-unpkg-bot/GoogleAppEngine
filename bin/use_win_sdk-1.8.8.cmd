set PROJECT_HOME=c:

echo 'copying ' %PROJECT_HOME%/GoogleAppEngine/bin/.classpath.win.sdk-1.8.8 ' to ' %PROJECT_HOME%/GoogleAppEngine/.classpath ' ...'
copy %PROJECT_HOME%\GoogleAppEngine\bin\.classpath.win.sdk-1.8.8 %PROJECT_HOME%\GoogleAppEngine\.classpath

pause