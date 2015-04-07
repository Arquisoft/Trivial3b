package es.uniovi.asw.modelo.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.modelo.persistence.EstadisticasJugadorDao;
import es.uniovi.asw.util.Conf;
import es.uniovi.asw.util.Jdbc;

public class EstadisticasJugadorJdbcDao implements EstadisticasJugadorDao {

    private Connection conexion;

    @Override
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Carga el resultado de un ResultSet
     */
    private Map<String, Object> load(ResultSet rs) throws SQLException {

        Map<String, Object> estadisticaJugador = new HashMap<String, Object>();

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

			ps = conexion.prepareStatement(Conf.get("SQL_ESTADISTICAS_FINDALL"));

            rs = ps.executeQuery();
            List<Map<String, Object>> estadisticasJugador = new ArrayList<Map<String, Object>>();
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
        try {
            ps = conexion.prepareStatement(Conf.get("SQL_ESTADISTICAS_INSERT"));
            ps.setInt(1, (Integer) estadistica.get("IDJUGADOR"));
            ps.setInt(2, (Integer) estadistica.get("IDPREGUNTA"));
            ps.setInt(3, (Integer) estadistica.get("ACIERTOS"));
            ps.setInt(4, (Integer) estadistica.get("FALLOS"));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Jdbc.close(ps);
        }
    }

    /**
     * Guarda el resultado de una pregunta +1acierto/fallo
     */
    @Override
	public void guardarResultado(Map<String, Object> estadistica, boolean acertada) {
        PreparedStatement ps = null;
        try {
			ps = conexion.prepareStatement(Conf.get("SQL_ESTADISTICAS_GUARDARRESULTADO"));
            if (acertada) {
                ps.setInt(1, 1 + (Integer) estadistica.get("ACIERTOS"));
                ps.setInt(2, (Integer) estadistica.get("FALLOS"));
            } else {
                ps.setInt(1, (Integer) estadistica.get("ACIERTOS"));
                ps.setInt(2, 1 + (Integer) estadistica.get("FALLOS"));
            }

            ps.setString(3, (String) estadistica.get("IDJUGADOR"));
            ps.setString(4, (String) estadistica.get("IDPREGUNTA"));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Jdbc.close(ps);
        }
    }

    /**
     * Busca una estadistica por su jugador y pregunta
     */
    @Override
    public Map<String, Object> findByJyP(int idJugador, int idPregunta) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

			ps = conexion.prepareStatement(Conf.get("SQL_ESTADISTICAS_FINDBYJugadorYPregunta"));
            ps.setInt(1, idJugador);
            ps.setInt(2, idPregunta);

            rs = ps.executeQuery();
            Map<String, Object> estadistica;
            if (rs.next()) {

                estadistica = load(rs);

                return estadistica;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Jdbc.close(rs, ps);
        }
    }
}
