var webcomApp = angular.module("webcomApp", ['kendo.directives']);

//webcom.log.Logger.init();
{
  webcomApp.plugins = [],
  webcomApp.registerPlugin = function(pluginClass) {
    webcomApp.plugins.push(pluginClass);
  },
  webcomApp.enableAllPlugins = function() {
    var pluginsInfo = "";
    for(var i=0;i<webcomApp.plugins.length;i++) {
      var plugin = webcomApp.plugins[i];
      if (typeof plugin.enableAllElements === 'function') {
        plugin.enableAllElements();
        pluginsInfo += plugin.name + ", ";
      }
    }
    console.log('webcomApp.js, Enabled Plugins : ', pluginsInfo);
  },
  webcomApp.enableByElement = function($el) {
    console.log('Enable Plugin By Element', $el);
    for(var i=0;i<webcomApp.plugins.length;i++) {
      var plugin = webcomApp.plugins[i];
      if (typeof plugin.enableAllElements === 'function' && $el.hasClass(plugin.cssClass)) {
        plugin.enableAllElements();
      }
    }
  }
}

webcomApp.directive('galleryImage', ['', function () {
    return {
        restrict: 'EA',
        replace: true,
        transclude: true,
        scope: {
            eventHandler: '&ngClick'
        },
        template: '<div class="img-wrap" id="gHolder"><span class="close">&times;</span><img id="gImg{{imgId}}" src="{{imgSrc}}" data-ng-click="eventHandler()" alt="Empty Image"/></div>',
        link:function(scope, element, attrs) {
            attrs.$observe('id', function(value) {
                scope.imgId = value;
                window.console && console.log('gallery-image-directive.js: id=', value);
            });

            attrs.$observe('src', function(value) {
                scope.imgSrc = value;
                window.console && console.log('gallery-image-directive.js: src=', value);
            });

            element.on('click', function () {
                scope[scope.imgId] = attrs.src;
                window.console && console.log('gallery-image-directive.js: scope['+scope.imgId+']=', scope[scope.imgId]);
                scope.selectedGalleryImage = scope[scope.imgId];
                scope.someObject = { name:scope[scope.imgId], id:scope.imgId };

                //=== begin of remove function
                window.console && console.log('current img selected id [' + 'gImg' + attrs.id + ']');
                //window.console && console.dir($('gImg' + attrs.id));
                //scope.currentImg = element;
                //=== end of remove function

                scope.$apply();
            });


        }
    };
}]);
    
var resizeOptions = {
  start: function(){
    //PropertiesModule.hidePropertiesPanel(true);
  },
  autoHide : true,
  handles : 'nw,ne,sw,se,n,w,s,e',
  resize: function(element, settings){
    
    $(element).find('.neo-asset-inner, .neo-asset-inner-scroll').height($(this).height());
    $(element).find('.neo-asset-inner, .neo-asset-inner-scroll').width($(this).width());
    
    var offset = $(element.target).offset();
    
    webcom.util.Events.fireEvent("AssetResize", {element: element, settings: settings});
  },
  stop: function() {
    //$('#pixel-dimensions').css('top', '-10000px');
    //CanvasModule.triggerSave = true;
  }
}
    
$(document).ready(function() {
  $('.neo-asset').draggable();
  $('.neo-asset').resizable(resizeOptions);  
});
