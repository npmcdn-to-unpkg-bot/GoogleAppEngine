set PATH=%PATH%;c:\spring-roo-1.2.5.RELEASE\bin
roo

project --topLevelPackage com.appspot.cloudserviceapi
jpa setup --provider DATANUCLEUS --database GOOGLE_APP_ENGINE
entity jpa --class ~.Service --testAutomatically
field string --fieldName service --notNull
web mvc setup
web mvc all --package ~.web
selenium test --controller ~.web.ServiceController
//perform tests
perform package

quit

References:

Spring Roo:快速建立企業級程式(夏肇毅 雲端教學台)
https://www.youtube.com/watch?v=xT9y_oEDVtI

Google App Engine + Spring MVC, CRUD Example With Datastore Low Level Api
http://www.mkyong.com/google-app-engine/google-app-engine-spring-mvc-crud-example-with-datastore-low-level-api/
