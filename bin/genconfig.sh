### Avoid InvalidCertificateException (http://stackoverflow.com/questions/13899530/gae-sdk-1-7-4-and-invalidcertificateexception)
#rm /Applications/google_appengine/lib/cacerts/cacerts.txt

/Applications/google_appengine/appcfg.py create_bulkloader_config --filename=bulkloader.yaml --url=http://cloudaware.appspot.com/remote_api

