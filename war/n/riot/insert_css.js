var fs = require('fs');
var insertCss = require('insert-css');
var css = fs.readFileSync(__dirname + '/../js/bower_components/bootstrap/dist/css/bootstrap.min.css');
insertCss(css);