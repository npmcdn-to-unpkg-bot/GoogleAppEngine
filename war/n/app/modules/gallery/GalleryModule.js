/**
 * Plugin is used for setting size and style for Gallery button
 *
 */

var GalleryModule = {
    state: null,
    selectedElement: null,
    pluginInfo: {
        type: "box",
        name: "Box",
        editButtonLabel: 'Edit Box',
        propertySlides: {
            defaultSlide: 0,
            slides: [
                {
                    id: "box-tab-1",
                    name: "autoPlay"
                },
                {
                    id: "box-tab-2",
                    name: "SetUp"
                },
                {l
                    id: "box-tab-3",
                    name: "Style"
                }
            ]
        }
    },
    init: function () {
        console.log("GalleryModule.selectedElement");
        var thumbnailCount = 0;
        var galleria;
        var galleriaDataLength = 0;
        var galleriaData = [];
        var x;
        // if(galleria != undefined) {
        //     galleriaDataLength = galleria.getDataLength();
        // }
        // alert("GalleryModule.js initialized 1");
        $('#asset-manager img').click(function () {
                var state = GalleryModule.state[GalleryModule.selectedElement.attr('id')];
                //alert(state);
                window.console && console.log("GalleryModule.js img click in fam state is '" + state + "'");

                if (state === 'done') {
                    var galleriaData1 = [];
                    var assetManagerThis = $(this);
                    var addedImage = assetManagerThis.clone().width('80px');
                    galleriaData1.push({image: addedImage.attr('src')});
                    galleria.load(galleriaData1);
                } else
                if (state === 'normal') {

                    var assetManagerThis = $(this);
                    var selectedImageId = assetManagerThis.attr('id');
                    galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
                    // var galleriaArray = Galleria.get();
                    // galleria = Galleria.get( GalleryModule.selectedElement.find('.galleria') )[0];
                    galleriaDataLength = galleria.getDataLength();
                    galleriaData = [];
                    var curImg;
                    for (var i = 0; i < galleriaDataLength; i++) {
                        curImg = galleria.getData(i);
                        if (thumbnailCount === 0) {
                            galleria.splice(0, 1); //remove the dummy black image! :)
                            galleria.setIndex(i + 1);
                        }

                        //alert("id [" + curImg.id + "]");
                        if (curImg.id) {
                            galleriaData.push(galleria.getData(i));
                        }
                    }
                    try {
                        if (assetManagerThis.hasClass('selected')) {

                            thumbnailCount--;
                            $('.properties-images-grid').find('#sortable').find('li#' + selectedImageId).parent('div.img-wrap').remove();
                            galleriaData = $.grep(galleriaData, function (e) {
                                return e.image != assetManagerThis.attr('src')
                            });

                            assetManagerThis.removeClass('selected');
                        } else {
                            thumbnailCount++;
                            var addedImage = assetManagerThis.clone().width('80px');
                            assetManagerThis.addClass('selected');

                            /*addedImage.click(function () {
                             // Galleria.get(0).splice(0,0);
                             //galleria.splice(galleria.getDataLength() - 1, 1);
                             window.console && console.log("addedImage.id [" + addedImage.attr('id') + "]");
                             // galleria.splice(addedImage.attr('id'), 1);
                             // galleria.setIndex(0);
                             // galleria.show(0);
                             // $(this).remove();
                             //   GalleryModule.sortableArray.push(addedImage);
                             // GalleryModule.jsonData.push({image: addedImage.attr('src')});

                             });*/
                            $('.properties-images-grid').find('#sortable').append('<div class="img-wrap"><span class="close">&times;</span><li id=' +
                                selectedImageId + '><img src=' + assetManagerThis.attr("src") + '></li></div>');
                            $('.img-wrap img').click(function() {
                                $('.details-img').html($(this).clone());

                            });
                            $('.close').click(function () {
                                var imageSrc = $(this).closest('.img-wrap').find('li').find('img').attr('src');
                                galleriaData = $.grep(galleriaData, function (e) {
                                    return e.image != imageSrc
                                });
                                $(this).parent('div').remove();


                            });

                            // $('.properties-images-grid').find('#sortable').append('<li id='+selectedImageId+'><img src='+assetManagerThis.attr("src")+'></li>');

                            galleriaData.push({ thumb: assetManagerThis.attr("src"),
                                image: assetManagerThis.attr("src"),
                                id: selectedImageId,
                                title: 'My title'

                            });
                        }

                        galleria.load(galleriaData);

                    } catch (e) {
                        window.console && console.log("1 GalleryModule error: " + e);
                    }
                }
            } //end of if(state === xxx) check
        );

        $('#sortable').sortable({

            update: function () {
                var order = $("#sortable ").sortable('toArray').toString();
                var afterSorting = [];
                $("ul#sortable li").each(function () {
                    afterSorting.push($(this).attr('id'));
                });
                console.log(afterSorting);
                var neworder = [];
                for (var i = 0; i < afterSorting.length; i++) {
                    for (var j = 0; j < galleriaData.length; j++) {
                        if (afterSorting[i] == galleriaData[j].id) {
                            neworder.push(galleriaData[j]);
                            break;
                        }
                    }
                }
                galleriaData = neworder;
                galleria.load(galleriaData);
            }
        });

    },
    select: function ($element) {
        GalleryModule.selectedElement = $element;
        // var galleriaInstance = Galleria.get(GalleryModule.selectedElement.attr('id'));
        var pluginStyle = $element.attr('style');
        var pluginInfo = $element.data('plugin-info');
        console.log(pluginInfo);
        $('#gallery-module-shell').toggle();
        var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
        $('.properties-images-grid ul').html('');

        var galleriaDataLength = galleria.getDataLength();
        var galleriaData = [];
        console.log("galleria");
        console.log(galleria);
        for (var i = 0; i < galleriaDataLength; i++) {
            var imageObj=  galleria.getData(i);
            var imageId = imageObj.id;
            var imageSrc=imageObj.image;
            $('.properties-images-grid').find('#sortable').append('<div class="img-wrap"><span class="close">&times;</span><li id=' +
                imageId + '><img src=' + imageSrc + '></li></div>');

        }

        console.log($('#gallery-module-shell').find('#box-tab-1'));

    },
    ngController: function ($scope, $rootScope, $element) {
        GalleryModule.state = [];
        $element.find('#addimage2').css({'display':'none'});
        $element.find('#addimage3').css({'display':'none'});

        $scope.setState = function (stat) {
            GalleryModule.state[GalleryModule.selectedElement.attr('id')] = stat;
            var temp = "GalleryModule.js state[" + GalleryModule.selectedElement.attr('id') + "] set to '" + GalleryModule.state[GalleryModule.selectedElement.attr('id')] + "'";
            //alert(temp);
            console.log(temp);
            // alert("angular js");
            // $scope.state = stat;
            // window.state=stat;
            // $scope.flag='normal';
            // x=stat;
            if(stat === 'normal') {
                $element.find('#addimage1').css({"display":"none"});$element.find('#addimage2').css({"display":"inline"});
            } else
            if(stat === 'hover') {
                $element.find('#addimage2').css({"display":"none"});$element.find('#addimage3').css({"display":"inline"});
            } else
            if(stat === 'done') {
                $element.find('#addimage3').css({"display":"none"});$element.find('#addimage1').css({"display":"inline"});
            }
        }

        webcom.util.Events.register('AssetResize', 'galleryResize', function (options) {
            if (options.settings.element.hasClass('gallery-plugin-inner')) {
                console.log(options);
            }
        });
        console.log('inside ngcontroller');
        // auto play checkbox
        $scope.$watch('autoPlay', function () {
            console.log('gallery watch autoPlay');
            if (GalleryModule.selectedElement) {
                var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
                console.log(galleria);
                if ($scope.autoPlay) {
                    galleria.play();
                } else {
                    galleria.pause();
                }
            }
        });

        // Transition Effect select box
        $scope.$watch('transEffect', function () {
            console.log('gallery watch transEffect');
            if (GalleryModule.selectedElement) {
                // var galleria = Galleria.get( GalleryModule.selectedElement.find('.galleria'))[0];
                var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
                galleria.setOptions('transition', $scope.transEffect);
            }
        });

        // Transition Speed select box
        $scope.$watch('transitionSpeed', function () {
            console.log('gallery watch transitionSpeed');
            if (GalleryModule.selectedElement) {
                // var galleria = $('.neo-asset').find('.galleria').data('galleria');
                var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
                galleria.setOptions('transitionSpeed', $scope.transitionSpeed);
            }
        });

        //display navigation (thumbnails)
        $scope.$watch('displayNavigation', function () {
            console.log('gallery watch displayNavigation');
            if (GalleryModule.selectedElement) {
                // var galleria = Galleria.get( GalleryModule.selectedElement.find('.galleria') )[0];
                var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
                galleria.setOptions('thumbnails', $scope.displayNavigation);

            }
        });

        //Loop playback checkbox
        $scope.$watch('loopPlayBack', function () {
            console.log('gallery watch loopPlayBack');
            if (GalleryModule.selectedElement) {
                // var galleria = Galleria.get( GalleryModule.selectedElement.find('.galleria') )[0];
                var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
                var totalImages = parseInt(galleria.$('total').html());
                if ($scope.loopPlayBack) {
                    galleria.unbind("loadfinish");
                    galleria.play();
                } else {
                    galleria.bind("loadfinish", function (e) {
                        if (e.index === 0 || e.index == totalImages - 1) {
                            galleria.pause();
                        }
                    });
                }
            }
        });

        // Image Counter check box
        $scope.$watch('imageCounter', function () {
            console.log('gallery watch imageCounter');
            if (GalleryModule.selectedElement) {
                // var galleria = Galleria.get( GalleryModule.selectedElement.find('.galleria') )[0];
                var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
                var totalImages = parseInt(galleria.$('total').html());
                if ($scope.imageCounter) {
                    $('.neo-asset-inner .galleria-counter').css('display', 'block');
                } else {
                    $('.neo-asset-inner .galleria-counter').css('display', 'none');
                }

            }
        });

        // Pause Gallery on hover checkbox
        $scope.$watch('pauseGalleryOnHover', function () {
            console.log('gallery watch pauseGalleryOnHover');
            if (GalleryModule.selectedElement) {
                // var galleria = Galleria.get( GalleryModule.selectedElement.find('.galleria') )[0];
                var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
                if ($scope.pauseGalleryOnHover) {
                    $('.neo-asset-inner .galleria-stage').hover(function () {
                        galleria.pause();
                    }, function () {
                        galleria.play();
                    });

                } else {
                    $('.neo-asset-inner .galleria-stage').hover(function () {
                        galleria.play();
                    });
                }
            }
        });

        // font size selectbox
        $scope.$watch('fontSize', function () {
            console.log('gallery watch fontSize');
            if (GalleryModule.selectedElement) {
                // var galleria = Galleria.get( GalleryModule.selectedElement.find('.galleria'))[0];
                var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
                $('.neo-asset-inner .galleria-info-title').css("font-size", parseInt($scope.fontSize));
            }
        });
        // font family selectbox
        $scope.$watch('fontFamily', function () {
            console.log('gallery watch fontFamily');
            if (GalleryModule.selectedElement) {
                // var galleria = Galleria.get( GalleryModule.selectedElement.find('.galleria'))[0];
                var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
                $('.neo-asset-inner .galleria-info-title').css("font-family", $scope.fontFamily);
            }
        });
        //fontColor select box
        $scope.$watch('fontColor', function () {
            console.log('gallery watch fontColor');
            if (GalleryModule.selectedElement) {
                // var galleria = Galleria.get( GalleryModule.selectedElement.find('.galleria'))[0];
                var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
                $('.neo-asset-inner .galleria-info-title').css("color", $scope.fontColor);


            }
        });

        $scope.$watch('easySlide', function () {
            console.log('gallery watch easySlide');
            if (GalleryModule.selectedElement) {
                var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
                // alert(parseInt($scope.easySlide)*1000);
                galleria.setPlaytime( parseInt($scope.easySlide)*1000 );




            }
        });


        // function declaration
        $scope.detailsSaveButton = function () {
            console.log('gallery watch detailsSaveButton');
            var galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
            galleria = Galleria.get(GalleryModule.selectedElement.attr('id'));
            galleriaDataLength = galleria.getDataLength();
            galleriaData = [];
            var curImg;
            for (var i = 0; i < galleriaDataLength; i++) {
                curImg = galleria.getData(i);
                //alert("id [" + curImg.id + "]");
                if (curImg.image == $('.details-img img').attr('src')) {
                    $('.details-img img').attr('src');
                    curImg.title = $('#imageCaption').val();
                    curImg.description=$('#imageDescription').val();
                    //  alert(curImg.title);
                    // alert(curImg.description);
                }
                galleriaData.push(curImg);
            }
            console.log("galleriaDatagalleriaDatagalleriaData  ");
            console.log(galleriaData);
            galleria.load(galleriaData);
        }



    },

};


$(document).ready(function () {
    $('#gallery-module-shell').appendTo('#modules');
    angular.bootstrap($('#gallery-module-shell'), ["webcomApp"]);
    GalleryModule.init();
});