package es.uniovi.asw.modelo.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.modelo.model.Pregunta;
import es.uniovi.asw.modelo.persistence.PreguntaDao;
import es.uniovi.asw.util.Conf;
import es.uniovi.asw.util.Jdbc;

public class PreguntaJdbcDao implements PreguntaDao {

    private Connection con;

    @Override
    public void setConnection(Connection con) {
        this.con = con;
    }

    public PreguntaJdbcDao() {
        try {
            setConnection(Jdbc.getConnection());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
    public void insertar(Pregunta pregunta) {

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Conf.get("SQL_PREGUNTAS_INSERT"));
            ps.setString(1, pregunta.getCategory().toString());
            ps.setInt(2, 0);
            ps.setInt(3, 0);
            ps.setString(4, pregunta.getId());

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
    public void guardarResultado(Map<String, Object> pregunta, boolean acertada) {
        PreparedStatement ps = null;
        try {
			ps = con.prepareStatement(Conf.get("SQL_PREGUNTAS_GUARDARRESULTADO"));

            Integer aciertos = (Integer) pregunta.get("ACIERTOS");
            Integer fallos = (Integer) pregunta.get("FALLOS");

            if (acertada) {
                ps.setInt(1, aciertos == null ? 1 : ++aciertos);
                ps.setInt(2, fallos == null ? 0 : fallos);
            } else {
                ps.setInt(1, aciertos == null ? 0 : aciertos);
                ps.setInt(2, fallos == null ? 1 : ++fallos);
            }

            ps.setLong(3, Long.parseLong(String.valueOf(pregunta.get("ID"))));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Jdbc.close(ps);
        }
    }

    /**
     * Busca una pregunta por su id
     */
    @Override
    public Map<String, Object> findById(String idPregunta) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = con.prepareStatement(Conf.get("SQL_PREGUNTAS_FINDBYPREGUNTA"));
            ps.setString(1, idPregunta);

            rs = ps.executeQuery();
            Map<String, Object> pregunta;
            if (rs.next()) {

                pregunta = load(rs);

                return pregunta;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Jdbc.close(rs, ps);
        }
    }

	@Override
	public Map<String, Object> getMasAcertada() {
		PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = con.prepareStatement(Conf.get("SQL_PREGUNTAS_MASACERTADA"));
 

            rs = ps.executeQuery();
            Map<String, Object> pregunta;
            if (rs.next()) {

                pregunta = load(rs);

                return pregunta;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Jdbc.close(rs, ps);
        }
	}

	@Override
	public Map<String, Object> getMasFallada() {
		PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = con.prepareStatement(Conf.get("SQL_PREGUNTAS_MASFALLADA"));

            rs = ps.executeQuery();
            Map<String, Object> pregunta;
            if (rs.next()) {

                pregunta = load(rs);

                return pregunta;
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
