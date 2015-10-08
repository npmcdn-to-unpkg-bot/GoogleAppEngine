/**
 * Copyright (c) 2013 Web.com Group, Inc. All Rights Reserved.
 *
 * This is an unpublished work protected by Web.com Group, Inc. as a trade
 * secret, and is not to be used or disclosed except as expressly provided in a
 * written license agreement executed by you and Web.com Group, Inc.
 *
 * @author David Dalcu
 */

var BoxModule = {
  selectedElement: null,
  /**
   * pluginInfo - object
   * carries generic information about the plugin
   * type - string (ie:sitesearch)
   * name - string 
   *   Human readable, will be used on the plugin's properties panel
   * editButtonLabel - string 
   *   the label that will be used on the main properties panel
   * propertyPanelShell - string
   *   critical for operation once integrated in the builder, this needs to be the CSS selector to the module property panel view
   * propertySlides: object
   *   used to describe the individual slides within the plugin's property panel
   *     - defaultSlide: integer - the default slide number that will be shown first (0 based)
   *     - slides: array - an array of slide objects, with id and name
   *               NOTE: make sure in your HTML you have a template named <slide.id> 
   *                     as this will be used to retrieve the content of your slide!
   */
  pluginInfo: {
    type: "box",
    name: "Box",
    editButtonLabel: 'Edit Box',
    propertyPanelShell: '#box-module-shell',
    propertySlides: {
      defaultSlide: 0,
      slides: [{
        id: "box-tab-1",
        name: "Color"
      }, {
        id: "box-tab-2",
        name: "Text"
      }]
    }
  },
  select: function($element) {
    BoxModule.selectedElement = $element;

    var pluginStyle = $element.attr('style');
    var pluginInfo = $element.data('plugin-info');
    console.log(pluginInfo);
    $('#box-module-shell').show();
  },
  ngController: function($scope, $rootScope, $element) {
    //These two lines are needed for builder integration
    $scope.editButtonLabel = "Edit Box";
    $scope.pluginInfo = BoxModule.pluginInfo;

    $scope.$watch('color', function() {
      console.log('watch color');
      if (BoxModule.selectedElement) {
        BoxModule.selectedElement.css('background-color', $scope.color);
      }
    });

    $scope.$watch('text', function() {
      if (BoxModule.selectedElement) {
        BoxModule.selectedElement.find('.box-text').html($scope.text);
      }
    });
    
    $scope.$watch('color1', function() {
      console.log('watch color');
      if (BoxModule.selectedElement) {
        BoxModule.selectedElement.css('background-color', $scope.color);
      }
    });

    $scope.$watch('text1', function() {
      if (BoxModule.selectedElement) {
        BoxModule.selectedElement.find('.box-text').html($scope.text);
      }
    });

  }
};

{
  $(document).ready(function() {
    $('#box-module-shell').appendTo('#modules');
    angular.bootstrap($('#box-module-shell'), ["webcomApp"]);
  });
}