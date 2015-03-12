package es.uniovi.asw.trivial;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.uniovi.asw.trivial.preguntas.Category;
import es.uniovi.asw.trivial.preguntas.Pregunta;
import es.uniovi.asw.trivial.serializers.JSONSerializer;
import es.uniovi.asw.trivial.serializers.Serializer;
import es.uniovi.asw.trivial.util.FileUtil;

public class SerializerTest {

    @Test
    public void test1() {
        final String jsonFile = "src/test/resources/testSerializer1.json";

        // Creamos una pregunta
        Pregunta preg = new Pregunta();
        preg.setQuestion("Question");
        preg.setCategory(Category.DEPORTES);
        preg.setCorrectAnswer("Correct");
        preg.setWrongAnswers(new String[] { "Wrong1", "Wrong2", "Wrong3" });

        List<Pregunta> preguntas = new ArrayList<Pregunta>();
        preguntas.add(preg);

        // La serializamos
        Serializer serial = new JSONSerializer();

        String json = serial.serialize(preguntas);

        // Y la comparamos con la esperada (quitando \r y dejando solo \n)
        String esperada = FileUtil.getFile(jsonFile).replaceAll("\r", "");
        assertEquals(esperada, json);
    }
}
