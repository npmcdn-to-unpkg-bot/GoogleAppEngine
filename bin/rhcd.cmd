REM Hit ctrl + break if you have not done: C:\>rhc git-clone mygaeapp!!!

set LOCAL_CAPEDWARF=C:\Users\sony\Downloads\CapeDwarf_WildFly_2.0.0.Final\CapeDwarf_WildFly_2.0.0.Final
set CAPEDWARF=C:\gae

pause

:### Local CapeDwarf Only ###
del /f/s/q %LOCAL_CAPEDWARF%\deployments\ROOT.war > nul
copy /S/Y ..\.openshift\config\standalone.xml %LOCAL_CAPEDWARF%\standalone\configuration\
xcopy /S/Y ..\war %LOCAL_CAPEDWARF%\standalone\deployments\ROOT.war
copy rcd %LOCAL_CAPEDWARF%\bin

:#### OpenShift ###
del /f/s/q %CAPEDWARF%\deployments\ROOT.war > nul
xcopy /S/Y ..\war %CAPEDWARF%\deployments\ROOT.war
copy skip_maven_build %CAPEDWARF%\.openshift\markers
copy rhcp %CAPEDWARF%

:cd %CAPEDWARF%

echo "cd %LOCAL_CAPEDWARF% and capedwarf.sh or "
echo "cd %CAPEDWARF% and invoke rhcopy now to push to OpenShift! :)"




