var MediaPlugin = {
    name: 'MediaPlugin',
    cssClass: 'media-plugin',
    template: $('.media-plugin-inner').html(),
    enableElement: function($el) {
      
      $el.find(".neo-asset-inner").html(MediaPlugin.template);
      
      $el.attr({
        "data-ng-controller": "MediaPluginController",
        "data-ng-click": "select();"
      });

      angular.bootstrap($el,["webcomApp"]);
    },
    enableAllElements: function() {
      $('.media-plugin').each(function(){
        MediaPlugin.enableElement($(this));
      });
    },
    ngController: function($scope, $rootScope, $element){
      
      //What happens onLoad
      var pluginInfo = $element.data('plugin-info');
      console.log("PluginType::: ->>>", pluginInfo.type);
      
      //What happens when the element is selected
      $scope.select = function() {
        if(typeof(MediaModule) == "object") {
          console.log('media MODULE stuff');
          MediaModule.select($element);
        } else {
          console.log('media plugin stuff');
        }
      }
    }
}

//Plugin is responsible for initializing its own code, view and CSS are already in the DOM, JS is executed last.
{
    webcomApp.controller("MediaPluginController", MediaPlugin.ngController);
    
    webcomApp.registerPlugin(MediaPlugin);
}