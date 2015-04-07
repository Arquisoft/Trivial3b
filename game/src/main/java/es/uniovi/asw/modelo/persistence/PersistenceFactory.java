package es.uniovi.asw.modelo.persistence;

public interface PersistenceFactory {

    JugadorDao createJugadorDao();

    PreguntaDao createPreguntaDao();

    EstadisticasJugadorDao createEstadisticasJugadorDao();

}
