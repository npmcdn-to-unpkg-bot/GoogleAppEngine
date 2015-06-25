#change JAVA_HOME accordingly
#export JAVA_HOME=/System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_45.jdk/Contents/Home

#java -javaagent:/service/appengine-java-sdk-1.4.2/lib/agent/appengine-agent.jar -classpath /service/appengine-java-sdk-1.4.2/lib/appengine-tools-api.jar com.google.appengine.tools.development.DevAppServerMain --port=8080 --address=0.0.0.0 /service/gaej/GoogleAppEngine/war
cp ../lib/standalone/selenium-server-standalone-2.35.0.jar ../war/WEB-INF/lib
