package es.uniovi.asw.persistence.impl;

import es.uniovi.asw.persistence.EstadisticasJugadorDao;

public class EstadisticasJugadorJdbcDao implements EstadisticasJugadorDao{
	
	private Connection conexion;
	
	@Override
	public void setConexion(Connection conexion){
		this.conexion = conexion;
	}
	
	/**
	 * Carga el resultado de un ResultSet
	 */
	private Map<String, Object> load(ResultSet rs) throws SQLException {

		Map<String, Object> estadisticaJugador = new HashMap<>();

		estadisticaJugador.put("IDJUGADOR", rs.getInt("IDJUGADOR"));
		estadisticaJugador.put("IDPREGUNTA", rs.getInt("IDPREGUNTA"));
		estadisticaJugador.put("ACIERTOS", rs.getInt("ACIERTOS"));
		estadisticaJugador.put("FALLOS", rs.getInt("FALLOS"));

		return estadisticaJugador;
	}
	
	/**
	 * Muestra todas las estadisticasJugador
	 */
	@Override
	public List<Map<String, Object>> findAll() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = conexion.prepareStatement(Conf.get("SQL_ESTADISTICASJUGADOR_FINDALL"));

			rs = ps.executeQuery();
			List<Map<String, Object>> estadisticasJugador = new ArrayList<>();
			while (rs.next()) {

				Map<String, Object> estadisticaJugador = load(rs);

				estadisticasJugador.add(estadisticaJugador);
			}

			return estadisticasJugador;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, ps);
		}
	}
	
	/**
	 * inserta una estadistica
	 */
	@Override
	public void insertar(Map<String, Object> estadistica) {

		PreparedStatement ps = null;
		try{
			ps = conexion.prepareStatement(Conf.get("SQL_ESTADISTICA_INSERT"));
			ps.setInt(1,(int) estadistica.get("IDJUGADOR"));
			ps.setInt(2,(int) estadistica.get("IDPREGUNTA"));
			ps.setInt(3,(int) estadistica.get("ACIERTOS"));
			ps.setInt(4,(int) estadistica.get("FALLOS"));

			ps.executeUpdate();

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(ps);
		}		
	}

}
