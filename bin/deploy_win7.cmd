:setEnv.cmd

echo on
set JAVA_HOME=C:\jdk1.6.0_45
set GAE_JAVA_SDK_HOME=C:\Users\ag\AppData\Local\Genuitec\Common\plugins\com.google.appengine.eclipse.sdkbundle_1.8.1.1\appengine-java-sdk-1.8.1.1
set PATH=%JAVA_HOME%\bin;%GAE_JAVA_SDK_HOME%\bin
mkdir c:\temp
set TEMP=c:\temp
set TMP=c:\temp

cd %~dp0
cd ..\..
set PROJECT_HOME=%CD%


echo %CLASSPATH%

start appcfg --enable_jar_splitting update "%PROJECT_HOME%\GoogleAppEngine\war"
cd %~dp0

:pause