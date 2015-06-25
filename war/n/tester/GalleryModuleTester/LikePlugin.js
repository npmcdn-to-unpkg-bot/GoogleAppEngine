var LikePlugin = {
    name: 'LikePlugin',
    cssClass: 'like-plugin',
    template: $('.like-plugin-inner').html(),
    enableElement: function($el) {
      
      $el.find(".neo-asset-inner").html(LikePlugin.template);
      
      $el.attr({
        "data-ng-controller": "LikePluginController",
        "data-ng-click": "select();"
      });

      angular.bootstrap($el,["webcomApp"]);
    },
    enableAllElements: function() {
      $('.like-plugin').each(function(){
        LikePlugin.enableElement($(this));
      });
    },
    ngController: function($scope, $rootScope, $element){
        $scope.select = function() {
          if(typeof(LikeModule) == "object") {
            console.log('like MODULE stuff');
            LikeModule.select($element);
          } else {
            console.log('like plugin stuff');
          }
        }
    }
}

//Plugin is responsible for initializing its own code, view and CSS are already in the DOM, JS is executed last.
{
    webcomApp.controller("LikePluginController", LikePlugin.ngController);
    
    webcomApp.registerPlugin(LikePlugin);
}