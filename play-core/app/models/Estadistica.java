package models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Estadistica extends Model implements Serializable {

	private static final long serialVersionUID = 1L;

	// Si no funcionan los ids, descomentar y quitar el idUsuario y el de
	// pregunta
	// @Id
	// private long id;

	@Id
	private String usuario;
	@Id
	private String question;
	private String category;
	private int aciertos;
	private int fallos;

	public Estadistica(String usuario, String question, String category,
			int aciertos, int fallos) {
		super();
		this.usuario = usuario;
		this.question = question;
		this.category = category;
		this.aciertos = aciertos;
		this.fallos = fallos;
	}

	public static void addEstadistica(Estadistica estadistica) {
		estadistica.save();
	}

	public String getUsuario() {
		return usuario;
	}

	public String getQuestion() {
		return question;
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

	private static Finder<Integer, Estadistica> finder = new Finder<Integer, Estadistica>(
			Integer.class, Estadistica.class);
}
