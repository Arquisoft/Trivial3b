package main.java.es.uniovi.asw.trivial.preguntas;

public enum Category {

	DEPORTES, 
	
	GEOGRAF�A, 
	
	HISTORIA,
	
	ARTEYLITERATURA,
	
	CIENCIAYTECNOLOGIA{
		@Override
	    public String toString() {
	      return "Ciencias y Tecnolog�a";
	    }
	},
	ESPECT�CULOSYENTRETENIMIENTO
	
}
