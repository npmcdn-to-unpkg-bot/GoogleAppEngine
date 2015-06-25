var GalleryPlugin = {
    name: 'GalleryPlugin',
    cssClass: 'gallery-plugin',
    template: $('.gallery-plugin-inner').html(),
    enableElement: function($el) {

        $el.find(".neo-asset-inner").html(GalleryPlugin.template);

        $el.attr({
            "data-ng-controller": "GalleryPluginController",
            "data-ng-click": "select();"
        });

        angular.bootstrap($el, ["webcomApp"]);
        //angular.module('MyApp', ['webcomApp']);   //James: no need to do this; taken care with angular.bootstrap
    },
    enableAllElements: function() {
        $('.gallery-plugin').each(function() {
            GalleryPlugin.enableElement($(this));
        });
    },
    ngController: function($scope, $rootScope, $element) {

        //alert("GalleryPlugin.js 1");
        $scope.select = function() {
            if (typeof(GalleryModule) === "object") {
                console.log('Gallery Clicked, do MODULE stuff');
                GalleryModule.select($element);
            }
            else {
                console.log('else Gallery Clicked, do plugin stuff');
            }
        }
    },
    
}

// {
$(document).ready(function() {
    // $('#gallery-module-shell').appendTo('#modules');
    // angular.bootstrap($('#gallery-module-shell'),["webcomApp"]);

    //$('#testdiv').appendTo('#modules');
    //angular.bootstrap($('#testdiv'),["webcomApp"]);

    Galleria.loadTheme('../assets/plugins/gallery/view/js/themes/classic/galleria.classic.min.js');

    Galleria.configure({
        dummy: '../assets/plugins/gallery/view/images/noimage.jpg',
        imageCrop: true,
        wait: true
    });

    $(function() {
        // Galleria.on('image', function(e) {
        //     //console.log(this); // the gallery scope
        //     //console.log(e.imageTarget); // the currently active IMG element
        // });        

        Galleria.run('.galleria');
        console.log("galleria loaded 0");
    });


    Galleria.ready(function() {
        this.attachKeyboard({
            right: this.next,
            left: this.prev


        });
    });
    
});
// }








//Plugin is responsible for initializing its own code, view and CSS are already in the DOM, JS is executed last.
{
    webcomApp.controller("GalleryPluginController", GalleryPlugin.ngController);

    webcomApp.registerPlugin(GalleryPlugin);
}