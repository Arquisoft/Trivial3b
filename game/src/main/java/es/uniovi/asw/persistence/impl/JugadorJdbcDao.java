package es.uniovi.asw.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.persistence.JugadorDao;
import es.uniovi.asw.util.Conf;
import es.uniovi.asw.util.Jdbc;

public class JugadorJdbcDao implements JugadorDao {

	private Connection con;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	/**
	 * Carga el resultado de un ResultSet
	 */
	private Map<String, Object> load(ResultSet rs) throws SQLException {

		Map<String, Object> player = new HashMap();

		player.put("ID", rs.getInt("ID"));
		player.put("USER", rs.getString("USER"));
		player.put("PASSWORD", rs.getString("PASSWORD"));
		player.put("ROL", rs.getString("ROL"));

		return player;
	}
	
	/**
	 * Muestra los jugadores
	 */
	@Override
	public List<Map<String, Object>> listarJugadores() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = con.prepareStatement(Conf.get("SQL_PLAYERS_FINDALL"));

			rs = ps.executeQuery();
			List<Map<String, Object>> players = new ArrayList();
			while (rs.next()) {
				Map<String, Object> player = load(rs);
				players.add(player);
			}

			return players;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, ps);
		}
	}
	
	/**
	 * Inserta un jugador
	 */
	@Override
	public void insertarJugadores(Map<String, Object> player) {

		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement(Conf.get("SQL_PLAYERS_INSERT"));
			ps.setInt(1, (Integer) player.get("ID"));
			ps.setString(2, (String) player.get("USER"));
			ps.setString(3,(String) player.get("PASSWORD"));
			ps.setString(4,(String) player.get("ROL"));

			ps.executeUpdate();

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(ps);
		}		
	}
	
	/**
	 * Modifica un jugador
	 */
	@Override
	public void modificarJugadores(Map<String, Object> player) {

		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement(Conf.get("SQL_PLAYERS_UPDATE"));
			ps.setInt(1, (Integer) player.get("ID"));
			ps.setString(2, (String) player.get("USER"));
			ps.setString(3,(String) player.get("PASSWORD"));
			ps.setString(4,(String) player.get("ROL"));

			ps.executeUpdate();

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(ps);
		}		
	}
}
