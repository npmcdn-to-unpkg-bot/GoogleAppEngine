:setEnv.cmd

echo on
set JAVA_HOME=C:\jdk1.7.0_45
set GAE_JAVA_SDK_HOME=C:\appengine-java-sdk-1.8.7
set PATH=%JAVA_HOME%\bin;%GAE_JAVA_SDK_HOME%\bin
mkdir c:\temp
set TEMP=c:\temp
set TMP=c:\temp

cd %~dp0

:http://blog.notdot.net/2010/04/Using-the-new-bulkloader
:http://stackoverflow.com/questions/2364310/gae-j-datastore-backup
c:\google_appengine\bulkloader.py --download --url http://cloudserviceapi.appspot.com/remote_api --config_file download_kind1.yaml --kind ServiceRegistry --filename download_kind_ServiceRegistry.csv
:c:\google_appengine\bulkloader.py --download --url http://2.scigrpservice.appspot.com/remote_api --config_file download_kind1.yaml --kind Movies --filename download_kind_Movie.csv
:c:\google_appengine\bulkloader.py --download --url http://cloudserviceapi.appspot.com/remote_api --config_file download_kind2.yaml --kind Geniu --filename download_kind_Geniu.csv
:c:\google_appengine\bulkloader.py --download --url http://cloudserviceapi.appspot.com/remote_api --config_file download_kind2.yaml --kind Huma --filename download_kind_Huma.csv
:c:\google_appengine\bulkloader.py --download --url http://cloudserviceapi.appspot.com/remote_api --config_file download_kind2.yaml --kind Secure --filename download_kind_Secure.csv
c:\google_appengine\bulkloader.py --download --url http://cloudaware.appspot.com/remote_api --config_file download_kind3.yaml --kind Movie --filename download_kind_Movie.csv
:del bulkloader-log*
:del bulkloader-progress*

pause