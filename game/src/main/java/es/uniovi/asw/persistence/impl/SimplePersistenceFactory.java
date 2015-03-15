package es.uniovi.asw.persistence.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

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
		return new EstadisticasJugadorDao() {

			@Override
			public void setConexion(Connection conexion) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public List<Map<String, Object>> findAll() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void insertar(Map<String, Object> estadisticaJugador) {
				// TODO Auto-generated method stub
				
			}
		};
	}	
}
