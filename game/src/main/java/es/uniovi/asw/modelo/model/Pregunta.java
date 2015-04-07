package es.uniovi.asw.modelo.model;

import java.util.Arrays;

/**
 * Clase Pregunta que almacena los siguientes atributos de la pregunta:
 * <ul>
 * <li>Texto
 * <li>Categoria
 * <li>Respuesta correcta
 * <li>Respuesta incorrecta
 * </ul>
 *
 * @author Jose Manuel Garcia Fernandez
 *
 */
public class Pregunta {

    // Numero de respuestas de la pregunta, incluyedo la respuesta correcta
    public static final int NUM_ANSWERS = 4;

    private String id;
    private Category category;
    private String question;
    private String correctAnswer;
    private String[] wrongAnswers;

    public Pregunta() {
        wrongAnswers = new String[NUM_ANSWERS - 1];
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pregunta [category=" + category + ", question=" + question
                + ", correctAnswer=" + correctAnswer + ", wrongAnswers="
                + Arrays.toString(wrongAnswers) + "]";
    }
}
