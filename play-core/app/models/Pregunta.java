package models;

import game.Category;

import java.io.Serializable;

public class Pregunta implements Serializable{

    private String id;

    // Numero de respuestas de la pregunta, incluyedo la respuesta correcta
    public static final int NUM_ANSWERS = 4;

    private Category category;
    private String question;
    private String correctAnswer;
    private String[] wrongAnswers;

    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getWrongAnswers() {
        return wrongAnswers;
    }

    @org.codehaus.jackson.annotate.JsonProperty("_id")
    void setId(String id) {
        this.id = id;
    }

    void setCategory(Category category) {
        this.category = category;
    }

    void setQuestion(String question) {
        this.question = question;
    }

    void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    void setWrongAnswers(String[] wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

}
