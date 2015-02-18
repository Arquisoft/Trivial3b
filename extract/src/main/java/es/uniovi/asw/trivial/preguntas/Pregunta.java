package es.uniovi.asw.trivial.preguntas;

import java.util.Arrays;

public class Pregunta {

	private Category category;
	private String queryText;
	private String correctAnswer;
	private String[] wrongAnswers;
	
	
	public Pregunta(Category category, String queryText, String correctAnswer,
			String[] wrongAnswers) {
		super();
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
	
	
}
