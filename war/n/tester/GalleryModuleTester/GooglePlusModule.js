/**
 * Plugin is used for setting size and style for Googleplus button
 * 
 */

var GooglePlusModule = {
      selectedElement: null,
      pluginInfo: {
        type: "box",
        name: "Box",
        editButtonLabel: 'Edit Box',
        propertySlides: {
          defaultSlide: 0,
          slides: [{
            id : "box-tab-1",
            name : "Size"
          }, {
            id : "box-tab-1",
            name : "Layout"
          }
          ]
        }
      },
      select: function($element) {
        GooglePlusModule.selectedElement = $element;
        console.log($element);
        var pluginStyle = $element.attr('style');
        var pluginInfo = $element.data('plugin-info');
        console.log(pluginInfo);
        $('#googleplus-module-shell').toggle();
      },
      ngController: function($scope, $rootScope, $element) {
        
         webcom.util.Events.register('AssetResize', 'googleplusResize', function(options) {
          if(options.settings.element.hasClass('googleplus-plugin-inner')) {
            console.log(options);
          }
        });
        var scopesize="standard",scopelayout="inline";
        $scope.$watch('size', function() {
          console.log('googleplus watch size');
          if(GooglePlusModule.selectedElement) {
            //GooglePlusModule.selectedElement.find('plusone-div').attr('size',$scope.size);
            gapi.plusone.render('plusone',{"size": $scope.size,"annotation":scopelayout });
            scopesize=$scope.size;
          }
        });
        
        $scope.$watch('layout', function() {
          console.log('googleplus watch Layout');
          if(GooglePlusModule.selectedElement) {
            //GooglePlusModule.selectedElement.find('plusone-div').attr('annotation',$scope.layout);
            gapi.plusone.render('plusone',{"size": scopesize,"annotation":$scope.layout });
            scopelayout=$scope.layout;
          }
        });
     
        
      }
};

// {
  $(document).ready(function(){
    $('#googleplus-module-shell').appendTo('#modules');
    angular.bootstrap($('#googleplus-module-shell'),["webcomApp"]);
  });
// }