/*!
 * Bootstrap v3.3.2 (http://getbootstrap.com)
 * Copyright 2011-2015 Twitter, Inc.
 * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 */
	var acierto = new Audio();
	var fallo = new Audio();

	acierto.src = "/sounds/Acierto.mp3";
	fallo.src = "/sounds/Fallo.mp3";

	function playAcierto(){
		acierto.play(); // Play button sound now
	    alert("Has acertado :D");
	  
	}
	
	function playFallo(){
		fallo.play(); // Play button sound now
	    alert("Ohhhhhh! Has Fallado :(");
	  
	}