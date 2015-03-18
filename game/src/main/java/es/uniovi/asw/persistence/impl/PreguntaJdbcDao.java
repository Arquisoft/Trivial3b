package es.uniovi.asw.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.persistence.PreguntaDao;
import es.uniovi.asw.util.Conf;
import es.uniovi.asw.util.Jdbc;

public class PreguntaJdbcDao implements PreguntaDao {

	private Connection con;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	/**
	 * Carga el resultado de un ResultSet
	 */
	private Map<String, Object> load(ResultSet rs) throws SQLException {

		Map<String, Object> pregunta = new HashMap<String, Object>();

		pregunta.put("ID", rs.getInt("ID"));
		pregunta.put("CATEGORIA", rs.getString("CATEGORIA"));
		pregunta.put("ACIERTOS", rs.getInt("ACIERTOS"));
		pregunta.put("FALLOS", rs.getInt("FALLOS"));

		return pregunta;
	}

	/**
	 * Muestra todas las preguntas
	 */
	@Override
	public List<Map<String, Object>> findAll() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = con.prepareStatement(Conf.get("SQL_PREGUNTAS_FINDALL"));

			rs = ps.executeQuery();
			List<Map<String, Object>> preguntas = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> pregunta = load(rs);
				preguntas.add(pregunta);
			}

			return preguntas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, ps);
		}
	}

	/**
	 * inserta una pregunta
	 */
	@Override
	public void insertar(Map<String, Object> pregunta) {

		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement(Conf.get("SQL_PREGUNTAS_INSERT"));
			ps.setInt(1, (Integer) pregunta.get("ID"));
			ps.setString(2,(String) pregunta.get("CATEGORIA"));
			ps.setInt(3,(Integer) pregunta.get("ACIERTOS"));
			ps.setInt(4,(Integer) pregunta.get("FALLOS"));

			ps.executeUpdate();

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(ps);
		}		
	}


	/**
	 * Guarda el resultado de una pregunta +1acierto/fallo
	 */
	@Override
	public void guardarResultado(Map<String, Object> pregunta, boolean acertada) {
		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement(Conf.get("SQL_PREGUNTAS_GUARDARRESULTADO"));
			if(acertada){
				ps.setInt(1,1+(Integer) pregunta.get("ACIERTOS"));
				ps.setInt(2,(Integer) pregunta.get("FALLOS"));
			}else{
				ps.setInt(1,(Integer) pregunta.get("ACIERTOS"));
				ps.setInt(2,1+(Integer) pregunta.get("FALLOS"));
			}

			ps.setLong(3,(Long) pregunta.get("ID"));

			ps.executeUpdate();

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(ps);
		}		
	}

	/**
	 * Busca una pregunta por su id
	 */
	@Override
	public Map<String, Object> findByID(int idPregunta) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = con.prepareStatement(Conf.get("SQL_PREGUNTAS_FINDBYID"));
			ps.setInt(1, idPregunta);

			rs = ps.executeQuery();
			Map<String, Object> pregunta;
			if (rs.next()) {

				pregunta = load(rs);

				return pregunta;
			}
			else return null;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, ps);
		}
	}
}
