document.write("It works.");
require("../js/bower_components/milligram/dist/milligram.css");
// var angular = require('angular');
require('./app');
demoModule.run(() => {
    console.log('demo running');
});
// angular.bootstrap(document, ['myApp']);
console.log('app booted!');
