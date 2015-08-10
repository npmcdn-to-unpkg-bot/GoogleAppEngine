REM Hit ctrl + break if you have not done: C:\>rhc git-clone mygaeapp!!!

set CAPEDWAFT=C:\Users\sony\Downloads\CapeDwarf_WildFly_2.0.0.Final\CapeDwarf_WildFly_2.0.0.Final

pause

xcopy ..\war %CAPEDWAFT%\standalone\deployments\war\ /s /e /h

copy skip_maven_build %CAPEDWAFT%\standalone\.openshift\markers\

copy rhcp.cmd %CAPEDWAFT%\bin\

copy rcd.cmd %CAPEDWAFT%\bin\

:cd %CAPEDWAFT%

REM cd %CAPEDWAFT% and invoke rhcp.cmd now to push to OpenShift! :)