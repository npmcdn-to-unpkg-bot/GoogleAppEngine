:call setEnv.cmd

set JAVA_HOME=C:\jdk1.6.0_24
set GAE_JAVA_SDK_HOME=C:\appengine-java-sdk-1.4.3
set PATH=%PATH%;%JAVA_HOME%\bin;%GAE_JAVA_SDK_HOME%\bin

start appcfg --enable_jar_splitting rollback "%~dp0\..\..\GoogleAppEngine\war"

:pause