package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Estadistica extends Model{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
private long id;
@Required
private Player usuario;
private int questionId;
private int aciertos;
public Estadistica(Player usuario, int questionId, int aciertos, int fallos) {
	super();
	this.usuario = usuario;
	this.questionId = questionId;
	this.aciertos = aciertos;
	this.fallos = fallos;
}
public Player getUsuario() {
	return usuario;
}
public void setUsuario(Player usuario) {
	this.usuario = usuario;
}
public int getQuestionId() {
	return questionId;
}
public void setQuestionId(int questionId) {
	this.questionId = questionId;
}
public int getAciertos() {
	return aciertos;
}
public void setAciertos(int aciertos) {
	this.aciertos = aciertos;
}
public int getFallos() {
	return fallos;
}
public void setFallos(int fallos) {
	this.fallos = fallos;
}
public long getId() {
	return id;
}
private int fallos;
}
