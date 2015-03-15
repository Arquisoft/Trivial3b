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

		categoria.put("IDJUGADOR", rs.getLong("IDJUGADOR"));
		categoria.put("IDPREGUNTA", rs.getString("IDPREGUNTA"));
		categoria.put("ACIERTOS", rs.getDouble("ACIERTOS"));
		categoria.put("FALLOS", rs.getDouble("FALLOS"));

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

}
