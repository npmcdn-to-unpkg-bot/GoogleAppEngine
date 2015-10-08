#! /usr/bin/env bash

#WORKAROUND for SDK 1.8.4 issue with selenium server 2.35.0!!!!
#rm ../war/WEB-INF/lib/selenium-server-standalone-2.35.0.jar

#Need to create soft link to your GAEJ home e.g.
#ln -s /Applications/appengine-java-sdk-1.8.7 /appengine-java-sdk
#sudo ln -s /Applications/appengine-java-sdk-1.8.8 /appengine-java-sdk
#ln -s '/Users/ag/demo' /project_home
#sudo chmod 755 /appengine-java-sdk/bin/appcfg.sh

#Also need to make sure Java is installed properly, generally available at /usr/bin/java and 
#change JAVA_HOME accordingly
#export JAVA_HOME=/System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_45.jdk/Contents/Home
#export JAVA_HOME='/System/Library/Frameworks/JavaVM.framework/Home'
export GAE_JAVA_SDK_HOME='/appengine-java-sdk'
#export PATH=${PATH}:$JAVA_HOME/bin:$GAE_JAVA_SDK_HOME/bin
PROJECT_HOME='/project_home'
#Reference: https://developers.google.com/appengine/docs/java/tools/uploadinganapp
#$GAE_JAVA_SDK_HOME/bin/appcfg.sh --enable_jar_splitting update ${PROJECT_HOME}/GoogleAppEngine/war
$GAE_JAVA_SDK_HOME/bin/appcfg.sh --use_java7 --enable_jar_splitting update ${PROJECT_HOME}/war
#$GAE_JAVA_SDK_HOME/bin/appcfg.sh --no_cookies --use_java7 --enable_jar_splitting update ${PROJECT_HOME}/GoogleAppEngine/war
