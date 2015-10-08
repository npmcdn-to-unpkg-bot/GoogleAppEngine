set JAVA_HOME=C:\jdk1.6.0_45
REM Uncomment the above mvn lines and change the version accordingly
set T_VERSION=5.3.7
REM goto http://tapestry.apache.org/download.html, download all jar files and copy them into 1/
REM remove 1/*.* when you are done!

cd 1
copy tapestry-core-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy tapestry-func-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy tapestry-ioc-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy tapestry-json-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy tapestry-test-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy tapestry-yuicompressor-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy tapestry5-annotations-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy plastic-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib

pause	