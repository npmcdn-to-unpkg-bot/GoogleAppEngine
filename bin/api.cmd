:setEnv.cmd

echo on
set JAVA_HOME=C:\jdk1.6.0_34
set GAE_JAVA_SDK_HOME=C:\appengine-java-sdk-1.7.2.1
set PATH=%JAVA_HOME%\bin;%GAE_JAVA_SDK_HOME%\bin
mkdir c:\temp
set TEMP=c:\temp
set TMP=c:\temp
echo %CLASSPATH%

cd %~dp0
cd ..

start endpoints get-java-client-lib com.bkper.server.api.TransactionEndpoint
cd %~dp0

pause