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
	private String question;
	private String correctAnswer;
	private String[] wrongAnswers;

	public Pregunta() {
		wrongAnswers = new String[NUM_ANSWERS - 1];
	}

	public Pregunta(Category category, String question, String correctAnswer, String[] wrongAnswers) {
		this.category = category;
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.wrongAnswers = wrongAnswers;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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
		return "Pregunta [category=" + category + ", question=" + question + ", correctAnswer="
				+ correctAnswer + ", wrongAnswers=" + Arrays.toString(wrongAnswers) + "]";
	}
}
