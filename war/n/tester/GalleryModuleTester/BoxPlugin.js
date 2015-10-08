/**
* Copyright (c) 2013 Web.com Group, Inc. All Rights Reserved.
*
* This is an unpublished work protected by Web.com Group, Inc. as a trade
* secret, and is not to be used or disclosed except as expressly provided in a
* written license agreement executed by you and Web.com Group, Inc.
*
* @author David Dalcu
*/

var BoxPlugin = {
    name: 'BoxPlugin',
    cssClass: 'box-plugin',
    template: $('.box-plugin-inner').html(),
    enableElement: function($el) {
      
      $el.find(".neo-asset-inner").html(BoxPlugin.template);
      
      $el.attr({
        "data-ng-controller": "BoxPluginController",
        "data-ng-click": "select();"
      });

      angular.bootstrap($el,["webcomApp"]);
    },
    enableAllElements:function() {
      $('.box-plugin').each(function(){
        BoxPlugin.enableElement($(this));
      });
    },
    ngController:function($scope, $rootScope, $element){
        $scope.select = function() {
          if(typeof(BoxModule) == "object") {
            console.log('Box Clicked, do MODULE stuff');
            BoxModule.select($element);
          } else {
            console.log('Box Clicked, do plugin stuff');
          }
        }
    }
}

//Plugin is responsible for initializing its own code, view and CSS are already in the DOM, JS is executed last.
{
    webcomApp.controller("BoxPluginController", BoxPlugin.ngController);
    
    webcomApp.registerPlugin(BoxPlugin);
}