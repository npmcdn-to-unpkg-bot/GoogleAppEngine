call setenv.bat

java -Dtapestry.execution-mode=DevelopmentMode -javaagent:%GAE_JAVA_SDK_HOME%/lib/agent/appengine-agent.jar -classpath %GAE_JAVA_SDK_HOME%/lib/appengine-tools-api.jar;%PROJECT_HOME%/lib/mygae.jar  com.google.appengine.tools.development.DevAppServerMain --port=8888 --address=0.0.0.0 %PROJECT_HOME%/war
