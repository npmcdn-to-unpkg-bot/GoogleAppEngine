var MediaModule = {
  selectedElement: null,
  pluginInfo: {
    type: "box",
    name: "Box",
    propertyPanelShell: '#media-module-shell',
    editButtonLabel: 'Edit Media',
    propertySlides: {
      defaultSlide: 0,
      slides: [{
        id: "box-tab-1",
        name: "Code"
      }]
    }
  },
  select: function($element) {
    MediaModule.selectedElement = $element;
    //console.log($element);
    var pluginStyle = $element.attr('style');
    var pluginInfo = $element.data('plugin-info');
    //console.log(pluginInfo);
    $('#media-module-shell').toggle();
  },
  ngController: function($scope, $rootScope, $element) {
    //These two lines are needed for builder integration
    $scope.editButtonLabel = "Edit Box";
    $scope.pluginInfo = MediaModule.pluginInfo;

    /* var videocode;
        $scope.$watch('code', function() {
          console.log('code apply');
            videocode= $scope.code;
        });
        
        $("#ok").click(function() {
            $('.video').prepend(videocode);
             $('#media-module-shell').toggle();
        });*/

    $scope.$watch('code', function() {
      if (MediaModule.selectedElement) {
        $(MediaModule.selectedElement.find('.video')).prepend($scope.code);
      }
    });


  }
};

{
  $(document).ready(function() {
    $('#media-module-shell').appendTo('#modules');
    angular.bootstrap($('#media-module-shell'), ["webcomApp"]);
  });
}