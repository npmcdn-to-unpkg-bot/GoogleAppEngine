call setEnv.cmd

:set JAVA_HOME=C:\jdk1.7.0_45
:set GAE_JAVA_SDK_HOME=C:\appengine-java-sdk-1.8.8
set PATH=%JAVA_HOME%\bin;%GAE_JAVA_SDK_HOME%\bin;%PATH%

start appcfg --enable_jar_splitting update "%~dp0\..\..\GoogleAppEngine\war"

:pause