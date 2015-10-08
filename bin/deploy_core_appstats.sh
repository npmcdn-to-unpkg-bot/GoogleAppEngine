#! /usr/bin/env bash

export PROJECT_HOME='/project_home'

echo 'copying ' ${PROJECT_HOME}/GoogleAppEngine/war/WEB-INF/web.xml.core_appstats ' to ' ${PROJECT_HOME}/GoogleAppEngine/war/WEB-INF/web.xml ' ...'
cp -f ${PROJECT_HOME}/GoogleAppEngine/war/WEB-INF/web.xml.core_appstats ${PROJECT_HOME}/GoogleAppEngine/war/WEB-INF/web.xml
rm current.web.*
touch current.web.core_appstats
ls current.web.*
