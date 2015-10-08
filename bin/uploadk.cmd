:del /Applications/google_appengine/lib/cacerts/cacerts.txt

REM ********************************************************************************************************************************************
REM DO NOT FORGET TO CHANGE remove the __key__ or id property before upload and change bulkloader_kind_Movie.yaml to bulkloader.yaml!!!
REM Also need to make sure that your datastore properties ARE THE SAME as the one specified in .yaml (like there SHOULD NOT BE ANY MISSING properties observed using Data Viewer)
REM ********************************************************************************************************************************************

C:\Python27\python c:/google_appengine/appcfg.py upload_data --config_file=C:\GoogleAppEngine\bin\bulkloader_kind_Movie.yaml --filename=C:\GoogleAppEngine\bin\download_kind_Movie.csv --kind=Movie --url=http://cloudaware.appspot.com/remote_api

pause