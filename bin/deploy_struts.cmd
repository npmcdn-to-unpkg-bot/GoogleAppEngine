:setEnv.cmd

cd %~dp0
cd ..\..
set PROJECT_HOME=%CD%
:set PROJECT_HOME=C:

set JAVA_HOME=c:\jdk1.6.0_29
set GAE_JAVA_SDK_HOME=C:\appengine-java-sdk-1.6.0
set PATH=%PATH%;%JAVA_HOME%\bin;%GAE_JAVA_SDK_HOME%\bin

echo copying '%PROJECT_HOME%\GoogleAppEngine\war\WEB-INF\web.xml.struts ' to '%PROJECT_HOME%\GoogleAppEngine\war\WEB-INF\web.xml ' ...'
copy %PROJECT_HOME%\GoogleAppEngine\war\WEB-INF\web.xml.struts %PROJECT_HOME%\GoogleAppEngine\war\WEB-INF\web.xml

pause