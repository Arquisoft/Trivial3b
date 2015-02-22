package es.uniovi.asw.trivial;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.trivial.parsers.GIFTParser;
import es.uniovi.asw.trivial.parsers.XMLParser;
import es.uniovi.asw.trivial.preguntas.Pregunta;
import es.uniovi.asw.trivial.saver.MongoSaver;
import es.uniovi.asw.trivial.serializers.JSONSerializer;

public class ExtractorTest {	

	@Test
	public void emptyList(){ // Comprueba que la lista no est� vac�a
		List<Pregunta> preguntasGIFT = new GIFTParser().parse("src/main/resources/preguntas.gift");
		assertEquals(0, preguntasGIFT.size());
	}
	
	@Test
	public void preguntasNull(){ //Comprueba que no hay nulos en la lista
		List<Pregunta> preguntasGIFT = new GIFTParser().parse("src/main/resources/preguntas.gift");
		
		for(int i = 0; i < preguntasGIFT.size(); i++){
			Pregunta pregunta = preguntas.get(i);
			assertNull("Mensaje OPCIONAL por si es null", pregunta);
		}
	}
	
	@Test
	public void emptyQuest(){ //Comprueba que no hay preguntas vacias
		List<Pregunta> preguntasGIFT = new GIFTParser().parse("src/main/resources/preguntas.gift");
		
		for(int i = 0; i < preguntasGIFT.size(); i++){
			Pregunta pregunta = preguntas.get(i);
			assertTrue(pregunta.getQueryText()!="");
		}
	}	
	
	@Test
	public void emptyExtractor() {
		// GIFT
		List<Pregunta> preguntasGIFT = new GIFTParser().parse("src/main/resources/preguntas.gift");
		String preguntasGIFTenJSON = new JSONSerializer().serialize(preguntasGIFT);
		
		System.out.println(preguntasGIFTenJSON);

		// XML
		List<Pregunta> preguntasXML = new XMLParser().parse("src/main/resources/preguntas.xml");
		String preguntasXMLenJSON = new JSONSerializer().serialize(preguntasXML);

		System.out.println(preguntasXMLenJSON);

		// MongoDB
		new MongoSaver().save(preguntasGIFTenJSON);
	}
}