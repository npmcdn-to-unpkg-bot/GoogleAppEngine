     //var App = angular.module('GalleryModule', ['ngDragDrop']);
    /*  App.controller('GalleryPluginController', function($scope, $timeout) {
 
      $scope.list3 = [];
      $scope.list4 = [];
      
      $scope.list5 = [
        { 'title': 'Item 1', 'drag': true, 'url':'http://inchoo.net/wp-content/uploads/2012/05/penguin-e1336375628766.jpg'}, { 'title': 'Item 1', 'drag': true, 'url':'http://inchoo.net/wp-content/uploads/2012/05/penguin-e1336375628766.jpg'}
        
      ];
    
      // Limit items to be dropped in list1
      $scope.optionsList1 = {
        accept: function(dragEl) {
          if ($scope.list1.length >= 2) {
            return false;
          } else {
            return true;
          }
        }
      };
    }); */
  


function mainController ($scope) {

    // Item List Arrays
    $scope.items = [{'title': 'http://www.site-build-it-scam.com/images/free-apps.jpg'},{'title':'http://inchoo.net/wp-content/uploads/2012/05/penguin-e1336375628766.jpg'}];
    $scope.checked = [];

    // Add a Item to the list
    $scope.addItem = function () {

        $scope.items.push({
            amount: $scope.itemAmount,
           
        });

       /* // Clear input fields after push
        $scope.itemAmount = '';
        $scope.itemName = '';
     */
    };

    // Add Item to Checked List and delete from Unchecked List
    $scope.toggleChecked = function (index) {
        
        $scope.checked.push($scope.items[index]);
         
        
    };


}