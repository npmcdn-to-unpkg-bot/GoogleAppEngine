'use strict';
// App 0.0.1
var build = '0003';
var myApp = angular.module('myApp', ['ngSanitize'], MyController);

//Not working for now - https://github.com/angular/angular.js/issues/1400
//function MyCtrl ( $scope) {
//    $scope.rawHtml = "<div><script>alert('test');</script></div>";
//    //$scope.sanitizedHmtl = $sanitize( $scope.rawHtml );   //TBD $sanitize(html) for some reason can not be found !!! "(
//    console.log("MyCtrl loaded. [" + mBuild + "]");
//};

function MyController($scope) {
    $scope.build = build;

    $scope.search = function () {
        var r = "";
        var str = $scope.query;
        console.log("searching ...");
        var separator = " ";
        var arrayOfStrings = str.split(separator);
        var s;
        var firstIndent = "&nbsp;&nbsp;&nbsp;";
        var secondIndent = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        for (var i=0; i < arrayOfStrings.length; i++) {
            s = arrayOfStrings[i];

            if(s.indexOf("hcp") !== -1) {
                r += firstIndent + '&lt;div id=&quot;HeaderContentPane&quot; class=&quot;contentPane&quot; runat=&quot;server&quot;&gt;&lt;/div&gt;<br>';
            }
            if(s.indexOf("bcp") !== -1) {
                r += firstIndent + '&lt;div id=&quot;A_BannerContentPane&quot; class=&quot;contentPane&quot; runat=&quot;server&quot;&gt;&lt;/div&gt;<br>';
            }
            if(s.indexOf("mcp") !== -1) {
                r += firstIndent + '&lt;div id=&quot;A_MainContentPane&quot; class=&quot;contentPane&quot; runat=&quot;server&quot;&gt;&lt;/div&gt;<br>';
            }
            if(s.indexOf("lbcp") !== -1) {
                r += firstIndent + '&lt;div id=&quot;A_LeftBarContentPane&quot; class=&quot;contentPane&quot; runat=&quot;server&quot;&gt;&lt;/div&gt;<br>';
            }
            if(s.indexOf("spw") !== -1) {
                r += firstIndent + '&lt;div id=&quot;A_SubPaneWrapper1&quot;&gt;<br>';
                r += secondIndent + '&lt;div id=&quot;A_XXXContentPane1&quot; class=&quot;contentPane&quot; runat=&quot;server&quot;&gt;&lt;/div&gt;<br>';
                r += firstIndent + '&lt;/div&gt;<br>';
            }
            if(s.indexOf("lcp") !== -1) {
                r += firstIndent + '&lt;div id=&quot;A_LastContentPane&quot; class=&quot;contentPane&quot; runat=&quot;server&quot;&gt;&lt;/div&gt;<br>';
            }
            if(s.indexOf("fcp") !== -1) {
                r += firstIndent + '&lt;div id=&quot;FooterContentPane&quot; class=&quot;contentPane&quot; runat=&quot;server&quot;&gt;&lt;/div&gt;<br>';
            }
        }

        $scope.line1 = function() {
            return r;
        };
    };

}

//function Ctrl($scope) {
//    $scope.templates =
//        [ { name: 'template1.html', url: 'template1.html'}
//            , { name: 'template2.html', url: 'template2.html'} ];
//    $scope.template = $scope.templates[0];
//}

MyController.$inject = ['$scope'];
