:setEnv.cmd

echo on
set JAVA_HOME=C:\jdk1.7.0_03
set GAE_JAVA_SDK_HOME=C:\appengine-java-sdk-1.6.2.1
set PATH=%JAVA_HOME%\bin;%GAE_JAVA_SDK_HOME%\bin
mkdir c:\temp
set TEMP=c:\temp
set TMP=c:\temp

cd %~dp0
cd ..\..
set PROJECT_HOME=%CD%


echo %CLASSPATH%

start appcfg vacuum_indexes "%PROJECT_HOME%\GoogleAppEngine\war"
cd %~dp0

:pause