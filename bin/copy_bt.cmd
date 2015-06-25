set TARGET1=%~dp0\..\war\WEB-INF\classes_bt\com\appspot\cloudserviceapi
set TARGET2=%~dp0\..\war\WEB-INF\classes_bt\edu\jhu\apl

copy %~dp0\..\war\WEB-INF\classes\com\appspot\cloudserviceapi\test\BigTimeTest.class %TARGET1%\test
copy %~dp0\..\war\WEB-INF\classes\com\appspot\cloudserviceapi\common\BigTimeUtil.class %TARGET1%\common
copy %~dp0\..\war\WEB-INF\classes\com\appspot\cloudserviceapi\common\RemoveHTMLReader.class %TARGET1%\common
copy %~dp0\..\war\WEB-INF\classes\com\appspot\cloudserviceapi\common\FileHelper.class %TARGET1%\common
copy %~dp0\..\war\WEB-INF\classes\edu\jhu\apl\CgiGet.class %TARGET2%
copy %~dp0\..\war\WEB-INF\classes\edu\jhu\apl\CgiPost.class %TARGET2%
copy %~dp0\..\war\WEB-INF\classes\edu\jhu\apl\CgiShow.class %TARGET2%
copy %~dp0\..\war\WEB-INF\classes\edu\jhu\apl\CgiShow.class %TARGET2%

rmdir /S/Q C:\GoogleAppEngine\war\WEB-INF\classes

:pause