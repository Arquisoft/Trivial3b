package es.uniovi.asw.trivial.preguntas;

public enum Category {

	DEPORTES, 
	
	GEOGRAFIA, 
	
	HISTORIA,
	
	ARTEYLITERATURA,
	
	CIENCIAYTECNOLOGIA{
		@Override
	    public String toString() {
	      return "Ciencias y Tecnolog�a";
	    }
	},
	ESPECTACULOSYENTRETENIMIENTO
	
}
