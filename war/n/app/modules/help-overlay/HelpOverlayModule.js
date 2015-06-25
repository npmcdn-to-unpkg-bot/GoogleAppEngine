/**
 * Plugin is used for setting size and style for 
 * 
 */

var HelpOverlayModule = {
    selectedElement: null,
    pluginInfo: {
        type: "box",
        name: "Box",
        editButtonLabel: 'Edit Box',
        propertySlides: {
            defaultSlide: 0,
            slides: [{
                id: "box-tab-1",
                name: "Color"
            }, {
                id: "box-tab-1",
                name: "Transparency"
            }, {
                id: "box-tab-1",
                name: "Image"
            }]
        }
    },
    select: function($element) {
        HelpOverlayModule.selectedElement = $element;
        console.log($element);
        var pluginStyle = $element.attr('style');
        var pluginInfo = $element.data('plugin-info');
        console.log(pluginInfo);
        $('#helpoverlay-module-shell').toggle();
    },
    ngController: function($scope, $rootScope, $element) {

        /*console.log("divid:" + $element.attr("id"));
        $scope.ok = function($value1, $value2) {
            console.log("OK");
            console.log($value1);
            var currentdiv = $value1;
            var nextdiv = $value2;
            console.log(currentdiv, $(currentdiv));
            //$("'"+ currentdiv +"'").css('display', 'block !important');
            //console.log($element.context.innerHTML.find(currentdiv));
            console.log("ID"+ $('#helpoverlay-module-shell').find("#slide2"));
            
            $(nextdiv).removeClass('hide');
            $(currentdiv).addClass("hide");
        };*/
        //$('#myModal').data('modal').options.remote


    }
};

// {
$(document).ready(function() {
   $('#helpoverlay-module-shell').appendTo('#modules');
    angular.bootstrap($('#helpoverlay-module-shell'), ["webcomApp"]);
});
// }