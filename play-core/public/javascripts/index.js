var app = angular.module('index', [])

			app.run(function($rootScope) {
				if(@game.getQuestionGiven()!=null){
						$(document).ready(function() {
							$("#dialog").modal("show");
						});
			})