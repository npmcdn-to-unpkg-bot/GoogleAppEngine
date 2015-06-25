#! /usr/bin/env bash

#Need to create soft link to your GAEJ home e.g.
#ln -s /Users/ag/appengine-java-sdk-1.5.1 /appengine-java-sdk
#ln -s '/Users/ag/Workspaces/Eclipse 3.7 Java' /eclipse_home
#ln -s /Users/ag/google_appengine /google_appengine

#Also need to make sure Java is installed properly, generally available at /usr/bin/java and 
#change JAVA_HOME accordingly

export JAVA_HOME='/System/Library/Frameworks/JavaVM.framework/Home'
export GAE_JAVA_SDK_HOME='/appengine-java-sdk'
export GAE_PYTHON_SDK_HOME='/google_appengine'
PROJECT_HOME='/eclipse_home'
#http://blog.notdot.net/2010/04/Using-the-new-bulkloader
#http://stackoverflow.com/questions/2364310/gae-j-datastore-backup
#/google_appengine/bulkloader.py --create_config --url http://localhost:8888/remote_api --filename generated_bulkloader.yaml
/google_appengine/bulkloader.py --create_config --url http://cloudserviceapi.appspot.com/remote_api --filename generated_bulkloader.yaml
#/google_appengine/bulkloader.py --create_config --url http://2.scigrpservice.appspot.com/remote_api --filename generated_bulkloader.yaml
