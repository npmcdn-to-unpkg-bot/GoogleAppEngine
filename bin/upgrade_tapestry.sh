export TAPESTRY_VER=5.3.7

mvn archetype:generate -DarchetypeCatalog=http://tapestry.apache.org
#mvn jetty:run
cd ~/.m2/repository/org/apache/tapestry/
#total 8 files
cp tapestry-core/$TAPESTRY_VER/tapestry-core-$TAPESTRY_VER.jar /project_home/GoogleAppEngine/war/WEB-INF/lib
cp tapestry-func/$TAPESTRY_VER/tapestry-func-$TAPESTRY_VER.jar /project_home/GoogleAppEngine/war/WEB-INF/lib
cp tapestry-ioc/$TAPESTRY_VER/tapestry-ioc-$TAPESTRY_VER.jar /project_home/GoogleAppEngine/war/WEB-INF/lib
cp tapestry-json/$TAPESTRY_VER/tapestry-json-$TAPESTRY_VER.jar /project_home/GoogleAppEngine/war/WEB-INF/lib
cp tapestry-test/$TAPESTRY_VER/tapestry-test-$TAPESTRY_VER.jar /project_home/GoogleAppEngine/war/WEB-INF/lib
cp tapestry-yuicompressor/$TAPESTRY_VER/tapestry-yuicompressor-$TAPESTRY_VER.jar /project_home/GoogleAppEngine/war/WEB-INF/lib
cp tapestry5-annotations/$TAPESTRY_VER/tapestry5-annotations-$TAPESTRY_VER.jar /project_home/GoogleAppEngine/war/WEB-INF/lib
cp plastic/$TAPESTRY_VER/plastic-$TAPESTRY_VER.jar /project_home/GoogleAppEngine/war/WEB-INF/lib
