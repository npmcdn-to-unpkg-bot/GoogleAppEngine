set JAVA_HOME=C:\jdk1.5.0_22
REM You need Maven to use this script!!!
set MAVEN_HOME=C:\apache-maven-3.0.4
set PATH=%MAVEN_HOME%\bin;%JAVA_HOME%\bin;%PATH%

REM Reference: http://tapestry.apache.org/getting-started.html
REM Run the following two commands separately by commenting/uncommenting (I know)
:mvn -X archetype:generate -DarchetypeCatalog=http://tapestry.apache.org

REM You have to pick 1 (you will figure it out ;))
cd 1
mvn -X jetty:run

REM Uncomment the above mvn lines and change the version accordingly
set T_VERSION=5.3.4

:cd C:\Documents and Settings\test\.m2\repository\org\apache\tapestry\
cd C:\Users\test\.m2\repository\org\apache\tapestry\
copy tapestry-core\%T_VERSION%\tapestry-core-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy tapestry-func\%T_VERSION%\tapestry-func-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy tapestry-ioc\%T_VERSION%\tapestry-ioc-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy tapestry-json\%T_VERSION%\tapestry-json-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy tapestry-test\%T_VERSION%\tapestry-test-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy tapestry-yuicompressor\%T_VERSION%\tapestry-yuicompressor-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy tapestry5-annotations\%T_VERSION%\tapestry5-annotations-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib
copy plastic\%T_VERSION%\plastic-%T_VERSION%.jar c:\GoogleAppEngine\war\WEB-INF\lib

pause	