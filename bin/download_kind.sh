#! /usr/bin/env bash

#Need to create soft link to your GAEJ home e.g.
#ln -s /Users/ag/appengine-java-sdk-1.6.3.1 /appengine-java-sdk
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
#/google_appengine/bulkloader.py --dry_run --application=cloudserviceapi --download --url http://cloudaware.appspot.com/remote_api --config_file download_kind1.yaml --kind ServiceRegistry --filename download_kind_ServiceRegistry.csv
/google_appengine/bulkloader.py --download --url http://cloudserviceapi.appspot.com/remote_api --config_file download_kind1.yaml --kind ServiceRegistry --filename download_kind_ServiceRegistry.csv
#/google_appengine/bulkloader.py --download --url http://2.scigrpservice.appspot.com/remote_api --config_file download_kind1.yaml --kind Movies --filename download_kind1.csv
#/google_appengine/bulkloader.py --download --url http://2.scigrpservice.appspot.com/remote_api --config_file download_kind2.yaml --kind Geniu --filename download_kind2.csv
#/google_appengine/bulkloader.py --download --url http://cloudserviceapi.appspot.com/remote_api --config_file download_kind2.yaml --kind Geniu --filename download_kind_Geniu.csv
#/google_appengine/bulkloader.py --download --url http://cloudserviceapi.appspot.com/remote_api --config_file download_kind2.yaml --kind Huma --filename download_kind_Huma.csv
rm bulkloader-log*
rm bulkloader-progress*
rm bulkloader-results*
