:setEnv.cmd

echo on
cd %~dp0
cd ..\..
set PROJECT_HOME=%CD%

set JAVA_HOME=C:\jdk1.7.0_03
set CLASSPATH=%CLASSPATH%;%PROJECT_HOME%\bin\axis\axis.jar

java -cp %CLASSPATH% -DproxyHost=CAPROXY -DproxyPort=9001 org.apache.axis.utils.tcpmon
cd %~dp0

:pause