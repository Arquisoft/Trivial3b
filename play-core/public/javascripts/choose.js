
var app = angular.module('test', [])

			app.run(function($rootScope,$window) {
				$rootScope.tablero=1;
				$rootScope.unirse=0;
				$rootScope.opciones2=[{name:"Unirse a partida"}, {name:"Crear Partida"}];
				$rootScope.opciones=[{name:"Tablero Cuadrado"}, {name:"Tablero Circular"}];
				$rootScope.currentValue = $rootScope.opciones[1];
				$rootScope.currentValue2=$rootScope.opciones[2];
				$rootScope.selecteable=true;
				$rootScope.change= function(currentValue){
					if(currentValue==$rootScope.opciones[0]){
					$rootScope.tablero=2;
					}
					else{
						$rootScope.tablero=1;
					}
				};
				$rootScope.change2=function(currentValue2){
					if(currentValue2==$rootScope.opciones2[0]){
						$rootScope.selecteable=false;
					}
					else{
						$rootScope.selecteable=true;
					}
				};
				$rootScope.redirect=function(){
					$window.location.href='/index/'+$rootScope.tablero;
				};
				$rootScope.redirect2=function(){
					$window.location.href='/findGame';
				};
			})