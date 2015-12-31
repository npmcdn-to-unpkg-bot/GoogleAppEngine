<sr-update>
<div class="container">
    <div ng-controller="PageCtrl as page">

        <h1>Service Manager - Create</h1><br>

        <form class="form-horizontal" role="form">
            <div class="form-group row">
                <div class="control-group">
                    <label for="title" class="col-sm-2 control-label">Service:</label>
                    <input type="text" class="col-sm-10 form-control" id="title" ng-enter="page.createItem()" required ng-model="page.service" placeholder="Enter any easy to remember service name.">
                </div>
                <div class="control-group">
                    <label for="desc" class="col-sm-2 control-label">Description:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="desc" ng-enter="page.createItem()" ng-model="page.description">
                    </div>
                </div>
                <div class="control-group">
                    <label for="endpoint" class="col-sm-2 control-label">URL:</label>
                    <div class="col-sm-10">
                        <input type="url" class="form-control" id="endpoint" ng-enter="page.createItem()" required ng-model="page.endpoint" placeholder="Enter any valid URL e.g. http://www.google.com.">
                    </div>
                </div>
                <div class="control-group">
                    <label for="createItem" class="col-sm-2 control-label"></label>
                    <div class="col-sm-10">
                        <input type="hidden" name="_method" value="POST">
                            <input class="form-control btn btn-primary" id="createItem" type="submit" style="background:#98969E;"
                                   value="Save" ng-click="page.createItem()">
                                <input class="form-control btn" id="cancelItem" value="Cancel" type="button" onclick="location.href='fusrstart.html'">
                    </div>
                </div>
            </div>
        </form>

    </div>
</div>
</sr-update>
