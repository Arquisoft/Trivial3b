package models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import play.db.ebean.Model;

@Entity
public class Estadistica extends Model implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId 
	public EstadisticaKey key;
	private String category;
	private int aciertos;
	private int fallos;

	public Estadistica(String usuario, String question, String category,
			int aciertos, int fallos) {
		super();
		this.key = new EstadisticaKey(usuario, question);
		this.category = category;
		this.aciertos = aciertos;
		this.fallos = fallos;
	}

	public static void addEstadistica(Estadistica estadistica) {
		estadistica.save();
	}

	public String getUsuario() {
		return key.usuario;
	}

	public String getQuestion() {
		return key.question;
	}

	public int getAciertos() {
		return aciertos;
	}

	public int getFallos() {
		return fallos;
	}

	public String getCategoria() {
		return category.toLowerCase();
	}

	public static List<Estadistica> obtenerTodasEstadisticas() {
		return finder.findList();
	}

	public static List<Estadistica> obtenerEstadisticas(String usuario,
			int pregunta) {
		return finder.where().eq("usuario", usuario).conjunction().where()
				.eq("questionId", pregunta).findList();
	}

	public static void updateEstadistica(String idUsuario, String idPregunta, String category, boolean acierto) {
		List<Estadistica> lista = finder.where().eq("question", idPregunta).conjunction().eq("usuario", idUsuario).findList();
		Estadistica e = (lista.isEmpty()) ? null : lista.get(0);
		if(e == null) {
			e = new Estadistica(idUsuario, idPregunta, category, 0, 0);
			e.save();
		}
		if(acierto)
			e.incrementarAcierto();
		else 
			e.incrementarFallo();
		e.update();
	}

	private void incrementarFallo() {
		fallos++;
	}

	private void incrementarAcierto() {
		aciertos++;
	}

	private static Finder<EstadisticaKey, Estadistica> finder = new Finder<EstadisticaKey, Estadistica>(
			EstadisticaKey.class, Estadistica.class);
}
