/**
 * Plugin is used for style and assign URL for Facebook like button
 * 
 */

var LikeModule = {
  selectedElement: null,
  pluginInfo: {
    type: "box",
    name: "Box",
    propertyPanelShell: '#like-module-shell',
    editButtonLabel: 'Edit Box',
    propertySlides: {
      defaultSlide: 0,
      slides: [{
        id: "box-tab-1",
        name: "Color"
      }, {
        id: "box-tab-1",
        name: "Layout"
      }, {
        id: "box-tab-1",
        name: "Display"
      }

      ]
    }
  },
  select: function($element) {
    LikeModule.selectedElement = $element;
    console.log($element);
    var pluginStyle = $element.attr('style');
    var pluginInfo = $element.data('plugin-info');
    console.log(pluginInfo);
    $('#like-module-shell').toggle();
  },
  ngController: function($scope, $rootScope, $element) {
    //These two lines are needed for builder integration
    $scope.editButtonLabel = "Edit Box";
    $scope.pluginInfo = LikeModule.pluginInfo;

    webcom.util.Events.register('AssetResize', 'likeResize', function(options) {
      if (options.settings.element.hasClass('fb-like')) {
        console.log('testoptions');
        console.log(options);
      }
    });

    $scope.$watch('color', function() {
      console.log('watch color');
      if (LikeModule.selectedElement) {
        LikeModule.selectedElement.find('.fb-like').attr('data-colorscheme', $scope.color);
        FB.XFBML.parse();
      }
    });

    $scope.$watch('layout', function() {
      console.log('Layout assign');
      if (LikeModule.selectedElement) {
        LikeModule.selectedElement.find('.fb-like').attr('data-layout', $scope.layout);
        FB.XFBML.parse();
      }
    });
    $scope.$watch('action', function() {
      if (LikeModule.selectedElement) {
        console.log('Display assign');
        LikeModule.selectedElement.find('.fb-like').attr('data-action', $scope.action);
        FB.XFBML.parse();
      }
    });

  }
};

// {
$(document).ready(function() {
  $('#like-module-shell').appendTo('#modules');
  angular.bootstrap($('#like-module-shell'), ["webcomApp"]);
});
// }