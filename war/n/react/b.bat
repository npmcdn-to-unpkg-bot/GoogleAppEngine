rem html2react

rem watchify -t [ babelify --presets [ react ] ] jsx\*.jsx -t brfs insert_css.js -o bundle.js
browserify -d -t [ babelify --presets [ es2015 react ] ] jsx\*.jsx -t brfs insert_css.js -o bundle.js

rem Caused "Cannot find module './lib/auth'" by concatenify
rem browserify -t reactify -t concatenify app.js -t [ babelify --presets [ react ] ] jsx\*.jsx -t brfs insert_css.js -o bundle.js

nodist use 5 && node_modules\.bin\webpack --display-modules --display-reasons --display-chunks -d %*