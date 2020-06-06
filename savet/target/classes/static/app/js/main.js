savetApp = angular.module("savetApp", ["ngRoute"]);

savetApp.config(["$routeProvider", function($routeProvider){
	$routeProvider
	.when("/poruke", {
		templateUrl : "/app/html/savet.html"
			
	})
	.when("/poruke/edit/:id", {
		templateUrl : "/app/html/editSavet.html"
	})
	.when("/poruke/glasaj/:id", {
		templateUrl : "/app/html/glasaj.html"
	})
	.otherwise({
		redirectTo : "/poruke"
	})
}]);


savetApp.controller("savetCtrl", function($scope, $location, $http){
	
	var porukeUrl = "/api/poruke";
	var zgradaUrl = "/api/zgrade";

	$scope.zgrade = [];
	$scope.poruke = [];
	
	$scope.search = {};
	$scope.search.zgrada = "";
	$scope.search.naslov ="";
	$scope.search.tip ="";
	
	
	$scope.newPoruka = {};
	$scope.newPoruka.naslov = "";
	$scope.newPoruka.tip ="";
	$scope.newPoruka.opis = "";
	$scope.newPoruka.procenat = "0";
	$scope.newPoruka.zgradaId = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	
	var getPoruke = function(){
		
		var config = { params : {}};
		
		if($scope.search.zgrada != ""){
			config.params.zgrada = $scope.search.zgrada;
			}
		if($scope.search.naslov != ""){
			config.params.naslov = $scope.search.naslov;
		}
		if($scope.search.tip != ""){
			config.params.tip = $scope.search.tip;
		}
		config.params.pageNum = $scope.pageNum;
		
		$http.get(porukeUrl , config).then(
		function succses(res){
			$scope.poruke = res.data;
			$scope.totalPages = res.headers("totalPages");
		},
		function error(res){
			alert("Neuspesno dobijanje poruka");
		}
		);
	}
	getPoruke();
	
	var getZgrade = function(){
		$http.get(zgradaUrl).then(
		function succses(res){
			$scope.zgrade = res.data;
		},
		function error(res){
			alert("Neuspesno dobijanje zgrada");
		}
		);
	}
	
	getZgrade();
	
	$scope.trazi = function(){
		$scope.pageNum = 0;
		getPoruke();
	}
	
	
	$scope.dodaj = function(){
		$http.post(porukeUrl, $scope.newPoruka).then(
		function succses(){
			getPoruke();
			
			$scope.newPoruka = {};
			$scope.newPoruka.naslov = "";
			$scope.newPoruka.tip ="";
			$scope.newPoruka.opis = "";
			$scope.newPoruka.procenat = "";
			$scope.newPoruka.zgradaId = "";
		},
		function error(){
			alert("Neuspesno dodavanje");
		}
		)
	}
	
	$scope.doDelete = function(id){
		$http.delete(porukeUrl + "/" + id).then(
		function succses(){
			alert("Uspesno brisanje");
			getPoruke();
		},
		function error(){
			alert("Nuespesno brisanje");
		}
		)
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getPoruke();
	}
	
	$scope.goToEdit = function(id){
		$location.path("/poruke/edit/" + id);
	}
	
	$scope.goGlasaj = function(id){
		$location.path("/poruke/glasaj/" + id)
	}
	
	$scope.glasajte = function(id){
		$http.post(porukeUrl + "/" + id).then(
		function succes(data){
			alert("Glasanje uspesno");
			getPoruke();
			console.log($scope.poruke);
		},
		function error(){
			alert("Glasanje ne uspesno");
		}
		);
	}
	
});

savetApp.controller("glasajCtrl", function($routeParams, $http, $location, $scope){
	
	var porukeUrl = "/api/poruke/" + $routeParams.id;
	var zgradaUrl = "/api/zgrade";
	
	$scope.newPoruka = {};
	$scope.newPoruka.naslov = "";
	$scope.newPoruka.tip ="";
	$scope.newPoruka.opis = "";
	$scope.newPoruka.procenat = "";
	$scope.newPoruka.zgradaId = "";
	
	$scope.zgrade = [];
	
	var getZgrade = function(){
		$http.get(zgradaUrl).then(
		function succses(res){
			$scope.zgrade = res.data;
		},
		function error(res){
			alert("Neuspesno dobijanje zgrada");
		}
		);
	}
	getZgrade();
	
	var getPoruke = function(){
		$http.get(porukeUrl).then(
		function succses(res){
			$scope.newPoruka = res.data;
			console.log($scope.newPoruka);
		},
		function error(res){
			alert("Neuspesno dobijanje poruka");
		}
		);
	}
	getPoruke();
	
	$scope.glasajte = function(){
		$http.post(porukeUrl, $scope.newPoruka).then(
		function succes(){
			alert("Glasanje uspesno");
			$location.path("/poruke");
			
			
		},
		function error(){
			alert("Glasanje ne uspesno");
		}
		);
	}
	
	$scope.protiv = function(){
		$location.path("/poruke");
	}
	
});

savetApp.controller("editCtrl", function($routeParams, $http, $location, $scope){
	
	var porukeUrl = "/api/poruke/" + $routeParams.id;
	var zgradaUrl = "/api/zgrade";
	
	$scope.newPoruka = {};
	$scope.newPoruka.naslov = "";
	$scope.newPoruka.tip ="";
	$scope.newPoruka.opis = "";
	$scope.newPoruka.procenat = "";
	$scope.newPoruka.zgradaId = "";
	
	$scope.zgrade = [];
	
	var getZgrade = function(){
		$http.get(zgradaUrl).then(
		function succses(res){
			$scope.zgrade = res.data;
		},
		function error(res){
			alert("Neuspesno dobijanje zgrada");
		}
		);
	}
	getZgrade();
	
	var getPoruke = function(){
		$http.get(porukeUrl).then(
		function succses(res){
			$scope.newPoruka = res.data;
		},
		function error(res){
			alert("Neuspesno dobijanje poruka");
		}
		);
	}
	getPoruke();
	
	$scope.doEdit = function(){
		$http.put(porukeUrl , $scope.newPoruka).then(
		function succes(){
			alert("Izmena uspesna");
			$location.path("/poruke");
		},
		function error(){
			alert("Izmena neuspesna");
		}
		);
	}
	
});