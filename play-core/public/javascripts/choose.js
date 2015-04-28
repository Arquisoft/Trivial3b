
var app = angular.module('test', [])

			app.run(function($rootScope,$window) {
				$rootScope.tablero=1;
				$rootScope.opciones=[{name:"Tablero Cuadrado"}, {name:"Tablero Circular"}];
				$rootScope.currentValue = $rootScope.opciones[1];
				$rootScope.change= function(currentValue){
					if(currentValue==$rootScope.opciones[0]){
					$rootScope.tablero=2;
					}
					else{
						$rootScope.tablero=1;
					}
				};
				$rootScope.redirect=function(){
					$window.location.href='/index/'+$rootScope.tablero;
				};
			})