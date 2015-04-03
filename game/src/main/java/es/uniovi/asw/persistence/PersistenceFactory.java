package es.uniovi.asw.persistence;

public interface PersistenceFactory {

	JugadorDao createJugadorDao();
	PreguntaDao createPreguntaDao();
	EstadisticasJugadorDao createEstadisticasJugadorDao();
	
}
