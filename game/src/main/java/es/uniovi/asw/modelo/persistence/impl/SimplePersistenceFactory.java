package es.uniovi.asw.modelo.persistence.impl;

import es.uniovi.asw.modelo.persistence.EstadisticasJugadorDao;
import es.uniovi.asw.modelo.persistence.JugadorDao;
import es.uniovi.asw.modelo.persistence.PersistenceFactory;
import es.uniovi.asw.modelo.persistence.PreguntaDao;

public class SimplePersistenceFactory implements PersistenceFactory{

	@Override
	public JugadorDao createJugadorDao(){
		return new JugadorJdbcDao();
	}

	@Override
	public PreguntaDao createPreguntaDao() {
		return new PreguntaJdbcDao();
	}

	@Override
	public EstadisticasJugadorDao createEstadisticasJugadorDao() {
		return new EstadisticasJugadorJdbcDao();
	}	
}
