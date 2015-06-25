#! /usr/bin/env bash

export JAVA_HOME='/System/Library/Frameworks/JavaVM.framework/Home'
export GAE_JAVA_SDK_HOME='/appengine-java-sdk'
PROJECT_HOME='/project_home'

java -jar ${PROJECT_HOME}/GoogleAppEngine/bin/strutstool/StrutsTool.jar $*
