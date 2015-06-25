echo This will remove all your app engine related jar files! Are you sure?
pause

del /f c:\GoogleAppEngine\war\WEB-INF\lib\appengine-api*.jar
del /f c:\GoogleAppEngine\war\WEB-INF\lib\appengine-endpoints*.jar
del /f c:\GoogleAppEngine\war\WEB-INF\lib\appengine-jsr*.jar
del /f c:\GoogleAppEngine\war\WEB-INF\lib\asm-4.0.jar
del /f c:\GoogleAppEngine\war\WEB-INF\lib\datanucleus-api-*.jar
del /f c:\GoogleAppEngine\war\WEB-INF\lib\datanucleus-appengine-*.jar
del /f c:\GoogleAppEngine\war\WEB-INF\lib\datanucleus-core-*.jar
del /f c:\GoogleAppEngine\war\WEB-INF\lib\jdo-api-*.jar
del /f c:\GoogleAppEngine\war\WEB-INF\lib\jsr107cache-1.1.jar
del /f c:\GoogleAppEngine\war\WEB-INF\lib\jta-1.1.jar

echo Done!
pause