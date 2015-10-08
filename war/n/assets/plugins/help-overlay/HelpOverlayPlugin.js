var HelpOverlayPlugin = {
    name: 'HelpOverlayPlugin',
    cssClass: 'helpoverlay-plugin',
    template: $('.helpoverlay-plugin-inner').html(),
    enableElement: function($el) {
      
      $el.find(".neo-asset-inner").html(HelpOverlayPlugin.template);
      
      $el.attr({
        "data-ng-controller": "HelpOverlayPluginController",
        "data-ng-click": "select();"
      });

      angular.bootstrap($el,["webcomApp"]);
    },
    enableAllElements: function() {
      $('.helpoverlay-plugin').each(function(){
        HelpOverlayPlugin.enableElement($(this));
      });
    },
    ngController: function($scope, $rootScope, $element){
        $scope.select = function() {
          if(typeof(HelpOverlayModule) == "object") {
            console.log('helpoverlay MODULE stuff');
            HelpOverlayModule.select($element);
          } else {
            console.log('helpoverlay plugin stuff');
          }
        }
    }
}

//Plugin is responsible for initializing its own code, view and CSS are already in the DOM, JS is executed last.
{
    webcomApp.controller("HelpOverlayPluginController", HelpOverlayPlugin.ngController);
    
    webcomApp.registerPlugin(HelpOverlayPlugin);
}