package es.uniovi.asw.trivial;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.trivial.parsers.GIFTParser;
import es.uniovi.asw.trivial.parsers.XMLParser;
import es.uniovi.asw.trivial.preguntas.Pregunta;

public class ExtractorTest {	

	@Test
	public void emptyList(){ // Comprueba que la lista no está vacía
		List<Pregunta> preguntasGIFT = new GIFTParser().parse("src/test/resources/preguntas.gift");
		assertNotEquals(0, preguntasGIFT.size());
		List<Pregunta> preguntasXML = new XMLParser().parse("src/test/resources/preguntas.xml");
		assertNotEquals(0, preguntasXML.size());

	}
	
	@Test
	public void preguntasNull(){ //Comprueba que no hay nulos en la lista
		List<Pregunta> preguntasGIFT = new GIFTParser().parse("src/test/resources/preguntas.gift");
		
		for(int i = 0; i < preguntasGIFT.size(); i++){
			Pregunta pregunta = preguntasGIFT.get(i);
			assertNotNull("Mensaje OPCIONAL por si es null", pregunta);
		}
	}
	
	@Test
	public void emptyQuest(){ //Comprueba que no hay preguntas vacias
		List<Pregunta> preguntasGIFT = new GIFTParser().parse("src/test/resources/preguntas.gift");
		
		for(int i = 0; i < preguntasGIFT.size(); i++){
			Pregunta pregunta = preguntasGIFT.get(i);
			assertTrue(pregunta.getQuestion()!="");
		}
	}	
	

	@Test
	public void correctAnswers(){
		List<Pregunta> preguntasGIFT = new GIFTParser().parse("src/test/resources/preguntas.gift");
		List<Pregunta> preguntasXML = new XMLParser().parse("src/test/resources/preguntas.xml");

		for (Pregunta pregunta: preguntasGIFT){
			assertNotNull(pregunta.getWrongAnswers());
				for (String respuesta: pregunta.getWrongAnswers()){
					assertTrue(respuesta!="");
				}
			assertTrue(pregunta.getCorrectAnswer()!="");			
		}

		for (Pregunta pregunta: preguntasXML) {
			assertNotNull(pregunta.getWrongAnswers());
				for (String respuesta: pregunta.getWrongAnswers()){
					assertTrue(respuesta!="");
				}
			assertTrue(pregunta.getCorrectAnswer()!="");			
		}
	}
}
