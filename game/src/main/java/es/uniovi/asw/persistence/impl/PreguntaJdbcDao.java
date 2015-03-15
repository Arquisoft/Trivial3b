package es.uniovi.asw.persistence.impl;

import java.sql.Connection;

import es.uniovi.asw.persistence.PreguntaDao;

public class PreguntaJdbcDao implements PreguntaDao {

	private Connection con;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

}
