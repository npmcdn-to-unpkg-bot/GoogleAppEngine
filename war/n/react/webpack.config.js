var path = require('path');
// var $script = require("scriptjs");

module.exports = {
    entry: {
        // ionic: './www/lib/ionic/js/ionic.bundle.js',
        // ioniccss: './www/lib/ionic/css/ionic.css',
        // directives: "./www/js/directives.js",
        jsx: ["./jsx/sr-start.jsx", "./jsx/sr-create.jsx", "./jsx/sr-update.jsx", "./jsx/mount-sr-create.jsx", "./jsx/mount-sr-update.jsx"],
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
            { test: /\.css$/, loader: "style!css" },
            {
                test: /\.jsx?$/,
                exclude: /(node_modules|bower_components)/,
                loader: 'babel', // 'babel-loader' is also a legal name to reference
                query: {
                    presets: ['react', 'es2015']
                }
            }
        ]
    },
    resolve: {
        root: __dirname,
        modulesDirectories: ['node_modules', 'js', 'css']
    }
};

// $script("//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.min.js", function() {
//   console.log('loaded momentjs');
// });
// $script("//cdnjs.cloudflare.com/ajax/libs/angular-moment/0.9.0/angular-moment.min.js", function() {
//   console.log('loaded angular momentjs');
// });
