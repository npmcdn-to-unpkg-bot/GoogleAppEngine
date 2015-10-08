var SocialPlugin = {
    name: 'SocialPlugin',
    cssClass: 'social-plugin',
    template: $('.social-plugin-inner').html(),
    enableElement: function($el) {
      
      $el.find(".neo-asset-inner").html(SocialPlugin.template);
      
      $el.attr({
        "data-ng-controller": "SocialPluginController",
        "data-ng-click": "select();"
      });

      angular.bootstrap($el,["webcomApp"]);
    },
    enableAllElements: function() {
      $('.social-plugin').each(function(){
        SocialPlugin.enableElement($(this));
      });
    },
    ngController: function($scope, $rootScope, $element){
      
      //What happens onLoad
      var pluginInfo = $element.data('plugin-info');
      console.log("PluginType::: ->>>", pluginInfo.type);
      var propertiesBox = $('#social-module-shell');
      if(pluginInfo.type=='facebook')
      {
          $element.find('.link-icon').attr('src',"/images/ico-facebook.png");
      }
      else if(pluginInfo.type=='twitter')
      {
           $element.find('.link-icon').attr('src',"/images/ico-twitter.png");
      }
      else if(pluginInfo.type=='youtube')
      {
           $element.find('.link-icon').attr('src',"/images/ico-youtube.png");
      }
      else if(pluginInfo.type=='myspace')
      {
           $element.find('.link-icon').attr('src',"/images/ico-myspace.png");
      }
      
      //What happens when the element is selected
      $scope.select = function() {
        if(typeof(SocialModule) == "object") {
          console.log('social MODULE stuff');
          SocialModule.select($element);
        } else {
          console.log('social plugin stuff');
        }
      }
    }
}

//Plugin is responsible for initializing its own code, view and CSS are already in the DOM, JS is executed last.
{
    webcomApp.controller("SocialPluginController", SocialPlugin.ngController);
    
    webcomApp.registerPlugin(SocialPlugin);
}