#This is not working, please visit http://www.apache.org/dyn/closer.cgi/tapestry/apache-tapestry-5.3.8-bin.zip to download the latest tapestry

export TAPESTRY_VER=5.3.8

cd ~/Downloads/apache-tapestry-5.3.8-bin

#total 8 files
cp ./tapestry-core-$TAPESTRY_VER.jar /project_home/war/WEB-INF/lib
cp ./tapestry-func-$TAPESTRY_VER.jar /project_home/war/WEB-INF/lib
cp ./tapestry-ioc-$TAPESTRY_VER.jar /project_home/war/WEB-INF/lib
cp ./tapestry-json-$TAPESTRY_VER.jar /project_home/war/WEB-INF/lib
cp ./tapestry-test-$TAPESTRY_VER.jar /project_home/war/WEB-INF/lib
cp ./tapestry-yuicompressor-$TAPESTRY_VER.jar /project_home/war/WEB-INF/lib
cp ./tapestry5-annotations-$TAPESTRY_VER.jar /project_home/war/WEB-INF/lib
cp ./plastic-$TAPESTRY_VER.jar /project_home/war/WEB-INF/lib
