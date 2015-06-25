var SocialModule = {
  selectedElement: null,
  pluginInfo: {
    type: "box",
    name: "Box",
    propertyPanelShell: '#social-module-shell',
    editButtonLabel: 'Edit Box',
    propertySlides: {
      defaultSlide: 0,
      slides: [{
        id: "box-tab-1",
        name: "URL"
      }]
    }
  },
  select: function($element) {
    SocialModule.selectedElement = $element;
    //console.log($element);
    var pluginStyle = $element.attr('style');
    var pluginInfo = $element.data('plugin-info');
    //console.log(pluginInfo);


    var propertiesBox = $('#social-module-shell');
    if (pluginInfo.type == 'facebook') {
      propertiesBox.find('#socialtitle').text("Facebook Link");
      propertiesBox.find('#socialurl').text("Facebook URL:");
      propertiesBox.find('#socialexample').text("Example:http://www.facebook.com/pagename");
    }
    if (pluginInfo.type == 'youtube') {
      propertiesBox.find('#socialtitle').text("YouTube Link");
      propertiesBox.find('#socialurl').text("YouTube URL:");
      propertiesBox.find('#socialexample').text("Example:http://www.youtube.com/user/username");
    }
    if (pluginInfo.type == 'twitter') {
      propertiesBox.find('#socialtitle').text("Twitter Link");
      propertiesBox.find('#socialurl').text("Twitter URL:");
      propertiesBox.find('#socialexample').text("Example:http://www.twitter.com/pagename");
    }
    if (pluginInfo.type == 'myspace') {
      propertiesBox.find('#socialtitle').text("MySpace Link");
      propertiesBox.find('#socialurl').text("MySpace URL:");
      propertiesBox.find('#socialexample').text("Example:http://www.myspace.com/username");
    }
    $('#social-module-shell').toggle();
  },
  ngController: function($scope, $rootScope, $element) {
    //These two lines are needed for builder integration
    $scope.editButtonLabel = "Edit Box";
    $scope.pluginInfo = SocialModule.pluginInfo;

    //console.log("Width : " + $element.attr("id"));//find('.link-icon').width());
    //console.log("plug:" + $('.neo-asset').data('plugin-info').type);
    //maxWidth = $('.neo-asset').find('.link-icon').width();
    //maxHeight =$('.neo-asset').find('.link-icon').height();

    var imageWidth = [];
    var imageHeight = [];
    $('.neo-asset img').each(function() {
      var $this = $(this);
      imageWidth.push($this.width());
      imageHeight.push($this.height());
    });

    //console.log($('.neo-asset').)

    webcom.util.Events.register('AssetResize', 'socialResize', function(options) {
      //$(options.element.target).find('.link-icon').width($(options.element.target).width());
      //$(options.element.target).find('.link-icon').height($(options.element.target).height());

      var maxWidth;
      var maxHeight;
      if ($(options.element.target).data('plugin-info').type == 'facebook') {
        maxWidth = imageWidth[0];
        maxHeight = imageHeight[0];
      }
      if ($(options.element.target).data('plugin-info').type == 'twitter') {
        maxWidth = imageWidth[1];
        maxHeight = imageHeight[1];
      }
      if ($(options.element.target).data('plugin-info').type == 'youtube') {
        maxWidth = imageWidth[2];
        maxHeight = imageHeight[2];
      }
      if ($(options.element.target).data('plugin-info').type == 'myspace') {
        maxWidth = imageWidth[3];
        maxHeight = imageHeight[3];
      }
      var ratio = 0;
      var targetWidth = $(options.element.target).width();
      var targetHeight = $(options.element.target).height();

      if (targetWidth > maxWidth) {
        ratio = maxWidth / targetWidth;
        $(options.element.target).find('.link-icon').width(maxWidth);
        $(options.element.target).find('.link-icon').height(targetHeight * ratio);
        //div sizes
        // $(options.element.target).width(maxWidth);
        //$(options.element.target).height(targetHeight * ratio);
        targetHeight = targetHeight * ratio;
        targetWidth = targetWidth * ratio;
      }
      //var targetWidth = $(options.element.target).width();
      //var targetHeight = $(options.element.target).height();
      if (targetHeight > maxHeight) {
        ratio = maxHeight / targetHeight;
        $(options.element.target).find('.link-icon').width(targetWidth * ratio);
        $(options.element.target).find('.link-icon').height(maxHeight);
        //div sizes
        // $(options.element.target).width(targetWidth * ratio);
        //$(options.element.target).height(maxHeight);
        targetHeight = targetHeight * ratio;
        targetWidth = targetWidth * ratio;
      }

    });

    $scope.$watch('url', function() {
      console.log('url assign');
      if (SocialModule.selectedElement) {
        SocialModule.selectedElement.find('.img-url').attr('href', $scope.url);
      }
    });

  }
};

{
  $(document).ready(function() {
    $('#social-module-shell').appendTo('#modules');
    angular.bootstrap($('#social-module-shell'), ["webcomApp"]);
  });
}