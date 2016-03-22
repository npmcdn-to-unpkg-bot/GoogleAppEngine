xcopy /S/Y ..\lib\*.jar ..\war\WEB-INF\lib
move ..\war\WEB-INF\classes ..\war\WEB-INF\classes_1
xcopy /S/Y ..\war\WEB-INF\classes_ ..\war\WEB-INF\classes\