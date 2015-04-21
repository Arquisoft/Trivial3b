package models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Estadistica extends Model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	@Required
	private String usuario;
	private int questionId;
	private int aciertos;
	private int fallos;

	public Estadistica(String usuario, int questionId, int aciertos, int fallos) {
		super();
		this.usuario = usuario;
		this.questionId = questionId;
		this.aciertos = aciertos;
		this.fallos = fallos;
	}

	public static Estadistica get(Integer id) {
		return finder.byId(id);
	}

	public static void addEstadistica(Estadistica estadistica) {
		estadistica.save();
	}

	public static List<Estadistica> obtenerEstadisticas(String usuario,
			int pregunta) {
		return finder.where().eq("usuario", usuario)
				.conjunction().where().eq("questionId", pregunta).findList();
	}

	private static Finder<Integer, Estadistica> finder = new Finder<Integer, Estadistica>(
			Integer.class, Estadistica.class);
}
