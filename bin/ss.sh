#change JAVA_HOME accordingly
#export JAVA_HOME=/System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home
#export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_45.jdk/Contents/Home
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_21.jdk/Contents/Home export PATH=$JAVA_HOME/bin:$PATH

java -javaagent:/appengine-java-sdk/lib/agent/appengine-agent.jar -classpath /appengine-java-sdk/lib/appengine-tools-api.jar com.google.appengine.tools.development.DevAppServerMain --port=8888 --address=0.0.0.0 ../war
