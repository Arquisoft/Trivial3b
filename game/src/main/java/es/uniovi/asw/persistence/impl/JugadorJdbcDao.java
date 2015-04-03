package es.uniovi.asw.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.model.Player;
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
	private Player load(ResultSet rs) throws SQLException {

		Player player = new Player(rs.getString("USER"));
		return player;
	}
	
	/**
	 * Muestra los jugadores
	 */
	@Override
	public List<Player> listarJugadores() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = con.prepareStatement(Conf.get("SQL_PLAYERS_FINDALL"));

			rs = ps.executeQuery();
			List<Player> players = new ArrayList<Player>();
			while (rs.next()) {
				Player player = load(rs);
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
	
	/**
	 * True si existe un usuario con ese nombre, pass y rol.
	 */
	@Override
	public boolean existeUsuario(String username, String pass, String rol){
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean res = false;
		try {

			ps = con.prepareStatement(Conf.get("SQL_PLAYERS_EXISTE"));
			ps.setString(1, username);
			ps.setString(2, pass);
			ps.setString(3, rol);
			
			rs = ps.executeQuery();
			Map<String, Object> jugador;
			if (rs.next()) {

				jugador = load(rs);

				if (jugador!=null){res = true;}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, ps);
		}
		return res;
	}
}
