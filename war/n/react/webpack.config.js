var path = require('path');
// var $script = require("scriptjs");

module.exports = {
    entry: {
        main: 'entry.js',
        // ionic: './www/lib/ionic/js/ionic.bundle.js',
        // ioniccss: './www/lib/ionic/css/ionic.css',
        // mcss: "../js/bower_components/milligram/dist/milligram.min.css",
        // bcss: "../js/bower_components/bootstrap/dist/css/bootstrap.min.css",
        // directives: "./www/js/directives.js",
        // js: ["../ng/app.js"],
        //jsx: ["./jsx/sr-start.jsx", "./jsx/sr-create.jsx", "./jsx/sr-update.jsx", "./jsx/mount-sr-create.jsx", "./jsx/mount-sr-update.jsx"],
        // app: ["./www/js/_app.js", "./www/js/_controllers.js", "./www/js/_routes.js", "./www/js/_services.js"]
        swaggerclient: "../js/swagger-client.js.SWG1"
    },
    output: {
        path: __dirname,
        filename: "bundle.js"
    },
    module: {
        noParse: path.resolve("../js/swagger-client.js.SWG1"),
        loaders: [
            { test: /\.css$/, loader : 'style!css' },
            //{ test: /\.css$/, loader: "style-loader!css-loader" },
	    {
        test: /.jsx?$/,
        loader: 'babel-loader',
        exclude: ['/node_modules/', '/bower_components/', '../ng'],
        query: {
          presets: ['react']
        }
      	    }
        ],
    },
    resolve: {
        root: __dirname,
        modulesDirectories: ['../js/bower_components', 'node_modules', 'js', 'css']
    }
};

// $script("//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.min.js", function() {
//   console.log('loaded momentjs');
// });
// $script("//cdnjs.cloudflare.com/ajax/libs/angular-moment/0.9.0/angular-moment.min.js", function() {
//   console.log('loaded angular momentjs');
// });
