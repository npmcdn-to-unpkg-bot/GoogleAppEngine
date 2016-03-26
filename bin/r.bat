call setenv.bat

java -Xmx2048m -javaagent:%GAE_JAVA_SDK_HOME%/lib/agent/appengine-agent.jar -classpath %GAE_JAVA_SDK_HOME%/lib/appengine-tools-api.jar  com.google.appengine.tools.development.DevAppServerMain --port=8888 --address=0.0.0.0 ../war