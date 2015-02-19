package es.uniovi.asw.trivial.preguntas;

import java.util.Arrays;

/**
 * Clase Pregunta que almacena los siguientes atributos de la pregunta:
 * 	-Texto
 * 	-Categoria
 * 	-Respuesta correcta
 * 	-Respuesta incorrecta
 * @author Jose Manuel Garcia Fernandez
 *
 */
public class Pregunta {

	// Numero de respuestas de la pregunta, incluyedo la respuesta correcta
	public static final int NUM_ANSWERS = 4;

	private Category category;
	private String queryText;
	private String correctAnswer;
	private String[] wrongAnswers;

	public Pregunta() {
		wrongAnswers = new String[NUM_ANSWERS - 1];
	}

	public Pregunta(Category category, String queryText, String correctAnswer, String[] wrongAnswers) {
		this.category = category;
		this.queryText = queryText;
		this.correctAnswer = correctAnswer;
		this.wrongAnswers = wrongAnswers;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public String getQueryText() {
		return queryText;
	}


	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}


	public String getCorrectAnswer() {
		return correctAnswer;
	}


	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}


	public String[] getWrongAnswers() {
		return wrongAnswers;
	}


	public void setWrongAnswers(String[] wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}


	@Override
	public String toString() {
		return "Pregunta [category=" + category + ", queryText=" + queryText
				+ ", correctAnswer=" + correctAnswer + ", wrongAnswers="
				+ Arrays.toString(wrongAnswers) + "]";
	}
	
	/**
	 * Devuelve un String en formato JSON con todos los datos de la pregunta
	 * @return String en formato JSON
	 */
	public String getJSON(){
		String cadena="{\ncategory: "+getCategory()+",\ntext: "+getQueryText()+",\ncorrectAnswer: "+getCorrectAnswer();
		for (String s : wrongAnswers) {
			cadena+=",\nwrongAnswer: "+s;
		}
		cadena+="\n}";
		return cadena;
	}
	
}
