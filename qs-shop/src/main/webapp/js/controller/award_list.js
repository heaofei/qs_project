define(['app',],function(app){
    app.controller('ctrl.index',function($scope,$location){
        var vm = $scope.vm = {};
        vm.title = '奖品兑换';
        var v=$location.absUrl() ;
        $scope.sesskey=v.substring(v.indexOf("=")+1,v.indexOf("#"));
    });
});