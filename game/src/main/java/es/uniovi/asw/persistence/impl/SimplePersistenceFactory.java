package es.uniovi.asw.persistence.impl;

import es.uniovi.asw.persistence.EstadisticasJugadorDao;
import es.uniovi.asw.persistence.JugadorDao;
import es.uniovi.asw.persistence.PersistenceFactory;
import es.uniovi.asw.persistence.PreguntaDao;

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
