REM Hit ctrl + break if you have not done: C:\>rhc git-clone mygaeapp!!!

pause

xcopy ..\war c:\mygaeapp\deployments\war\ /s /e /h

copy skip_maven_build c:\mygaeapp\.openshift\markers\

copy rhcp.cmd c:\mygaeapp\

cd c:\mygaeapp\

REM Invoke rhcp.cmd now to push to OpenShift! :)