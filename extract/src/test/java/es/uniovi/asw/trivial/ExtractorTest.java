package es.uniovi.asw.trivial;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.model.Pregunta;
import es.uniovi.asw.trivial.parsers.GIFTParser;
import es.uniovi.asw.trivial.parsers.XMLParser;

public class ExtractorTest {

    private static final String GIFT = "src/test/resources/preguntas.gift";
    private static final String XML = "src/test/resources/preguntas.xml";

    @Test
    public void emptyList() { // Comprueba que la lista no está vacía
        List<Pregunta> preguntasGIFT = new GIFTParser().parse(GIFT);
        assertNotEquals(0, preguntasGIFT.size());
        List<Pregunta> preguntasXML = new XMLParser().parse(XML);
        assertNotEquals(0, preguntasXML.size());

    }

    @Test
    public void preguntasNull() { // Comprueba que no hay nulos en la lista
        List<Pregunta> preguntasGIFT = new GIFTParser().parse(GIFT);

        for (int i = 0; i < preguntasGIFT.size(); i++) {
            Pregunta pregunta = preguntasGIFT.get(i);
            assertNotNull("Mensaje OPCIONAL por si es null", pregunta);
        }
    }

    @Test
    public void emptyQuest() { // Comprueba que no hay preguntas vacias
        List<Pregunta> preguntasGIFT = new GIFTParser().parse(GIFT);

        for (int i = 0; i < preguntasGIFT.size(); i++) {
            Pregunta pregunta = preguntasGIFT.get(i);
            assertTrue(pregunta.getQuestion() != "");
        }
    }

    @Test
    public void correctAnswers() {
        List<Pregunta> preguntasGIFT = new GIFTParser().parse(GIFT);
        List<Pregunta> preguntasXML = new XMLParser().parse(XML);

        for (Pregunta pregunta : preguntasGIFT) {
            assertNotNull(pregunta.getWrongAnswers());
            for (String respuesta : pregunta.getWrongAnswers()) {
                assertTrue(respuesta != "");
            }
            assertTrue(pregunta.getCorrectAnswer() != "");
        }

        for (Pregunta pregunta : preguntasXML) {
            assertNotNull(pregunta.getWrongAnswers());
            for (String respuesta : pregunta.getWrongAnswers()) {
                assertTrue(respuesta != "");
            }
            assertTrue(pregunta.getCorrectAnswer() != "");
        }
    }
}
