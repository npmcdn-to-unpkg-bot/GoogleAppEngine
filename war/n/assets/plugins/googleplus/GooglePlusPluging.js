var GooglePlusPlugin = {
    name: 'GooglePlusPlugin',
    cssClass: 'googleplus-plugin',
    template: $('.googleplus-plugin-inner').html(),
    enableElement: function($el) {
      
      $el.find(".neo-asset-inner").html(GooglePlusPlugin.template);
      
      $el.attr({
        "data-ng-controller": "GooglePlusPluginController",
        "data-ng-click": "select();"
      });

      angular.bootstrap($el,["webcomApp"]);
    },
    enableAllElements: function() {
      $('.googleplus-plugin').each(function(){
        GooglePlusPlugin.enableElement($(this));
      });
    },
    ngController: function($scope, $rootScope, $element){
        $scope.select = function() {
          if(typeof(GooglePlusModule) == "object") {
            console.log('googleplus MODULE stuff');
            GooglePlusModule.select($element);
          } else {
            console.log('googleplus plugin stuff');
          }
        }
    }
}

//Plugin is responsible for initializing its own code, view and CSS are already in the DOM, JS is executed last.
{
    webcomApp.controller("GooglePlusPluginController", GooglePlusPlugin.ngController);
    
    webcomApp.registerPlugin(GooglePlusPlugin);
}