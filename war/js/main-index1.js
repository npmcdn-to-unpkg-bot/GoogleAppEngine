requirejs.config({
//    enforceDefine: true,

    baseUrl: '/js',

    paths: {
        jquery: ['//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery', 'jquery-1.9.1'],    //for some reason, this is needed for IE 8 otherwise  'jQuery' is undefined or  '$' is undefined will occur
        //===used by index1.html
        index: '../html/index1',
        initUser: ['../parse/init'],
        parse: 'https://www.parsecdn.com/js/parse-1.2.7.min'    //need this for localhost testing!
        //,
//        facebook: '../parse/facebook',
//        plusone:  '//apis.google.com/js/plusone',
//        gapi: 'https://apis.google.com/js/client.js?onload=load',
//        google: '../js/google',
//        sha1: '../js/sha1',
        //=== commented out, currently this outh 2.0 workaround does not work (caused: Mismatched anonymous define() module error)
//        codebird: '../js/codebird',
//        twitter: '../js/twitter'
    },

    shim: {
        jquery: {
            exports: '$'
        },
        index: {
            exports: 'index'
        },
        parse: {
            deps: ["jquery"],
            exports: 'Parse'
        },
        initUser: {
            deps: ['jquery', 'parse']
        }
        //run as specified in the following orders
//        recaptcha: {
//            deps: ['jquery'],
//            exports: 'recaptcha'
//        },
//        captcha: {
//            deps: ['jquery', 'recaptcha'],
//            exports: 'captcha'
//        },
//        index: {
//            deps: ['jquery'],
//            exports: 'index'
//        },

    },

    waitSeconds: 90     //timeout in seconds if not able to load any of the external URL above
});

//define("main-index1", function () {
//    //does nothing for now - just to suppress "Uncaught Error: No define call for ..." error
//    return function Index(one, two) {
//        this.one = one;
//        this.two = two;
//    };
//});

//Start the main app logic.
require(['./main-config', './app'], function (common, app) {
    require(
        ['jquery', 'purl', 'parse', 'index'
        ],
        function(
            $, purl, Parse, index
            ) {
            $(document).ready(function () {
                Parse.initialize("Ld70ODLxjXFkhhRux6kQqVCiJ4rQeXU8dISafNJa", "dLHhDNKnLimXHzSzxvQcGuwle5iLwnn3bFahDS9q");
                window.console && console.log("main-index1: Parse initialized.");
            });

            $('#offlineStatus').hide();
            $('#captchaForm').show();
            requirejs(['//www.google.com/recaptcha/api/js/recaptcha_ajax.js'], function() {
                var public_key;

                if(location.hostname.indexOf('radiant') > -1) {
                    public_key = '6LeZ7OcSAAAAAOQ-sJuwuzwWdE87OyiIOIhti2H3';
                }
                else
                if(location.hostname.indexOf('cbiit') > -1 || location.hostname.indexOf('cadsr') > -1) {
                	public_key = '6Ld4gOYSAAAAAIhBHyv2iH1Zej_1LVmoNdzXgEhp';
                }
                else
                if(location.hostname.indexOf('oo.tv') > -1) {
                	public_key = '6Ld7s-4SAAAAAJiNSV0OI19dm9WnyMauDOnVJEZ0';
                }
                else
                if(location.hostname.indexOf('hudoone') > -1) {
                    public_key = '6LfNgeESAAAAAAppEbVa-VNLv2FlNVS0loNmvy-h';
                }
                else
                if(location.hostname.indexOf('service') > -1) {
                	public_key = '6LfOiMASAAAAAO3mxDZRXIkA2t16nx4_LJzGWtyF';
                }
                else
                if(location.hostname.indexOf('share') > -1) {
                    public_key = '6LeiHegSAAAAAEzLEGvCbJSoArTauLqWHQW98bjs';
                }
                else
                if(location.hostname.indexOf('localhost') > -1) {
                	public_key = '6LckguYSAAAAAIFtPd21uc0eNCSjpKJrBuxSlQ0x';
                }
                
                Recaptcha.create(public_key, 'captchadiv', {
                    tabindex: 1,
                    theme: "blackglass"
                    //,
                    //callback: Recaptcha.focus_response_field
                });
            });

            var el = $('#username');
            el.removeAttr("disabled");
            $('#password').removeAttr("disabled");
            el.focus();
        }
    );
});