Download Google App Engine SDK for Python from https://developers.google.com/appengine/downloads?hl=de-DE&csw=1

Install it at C:\google_appengine\

Refer to https://developers.google.com/appengine/docs/python/tools/uploadingdata

Download the kind:

C:\google_appengine\appcfg.py download_data --kind=Movie --url=http://cloudaware.appspot.com/_ah/remote_api --filename=download_kind_Movie.csv

Upload the kind:

appcfg.py upload_data --url=http://cloudaware.appspot.com/_ah/remote_api --kind=Movie --filename=download_kind_Movie.csv

Notes:

All download_kind*.cmd/download_kind*.sh and upload_kind*.cmd/upload_kind*.sh are obsolete. Please use the corresponding downloadk/uploadk instead.

CreateKey for deep create
http://longsystemit.com/javablog/?p=23