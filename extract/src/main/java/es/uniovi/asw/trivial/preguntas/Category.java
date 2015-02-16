package main.java.es.uniovi.asw.trivial.preguntas;

public enum Category {

	DEPORTES, 
	
	GEOGRAFÍA, 
	
	HISTORIA,
	
	ARTEYLITERATURA,
	
	CIENCIAYTECNOLOGIA{
		@Override
	    public String toString() {
	      return "Ciencias y Tecnología";
	    }
	},
	ESPECTÁCULOSYENTRETENIMIENTO
	
}
