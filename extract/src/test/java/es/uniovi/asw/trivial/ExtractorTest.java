package es.uniovi.asw.trivial;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.trivial.parsers.GIFTParser;
import es.uniovi.asw.trivial.parsers.XMLParser;
import es.uniovi.asw.trivial.preguntas.Pregunta;
import es.uniovi.asw.trivial.serializers.JSONSerializer;

public class ExtractorTest {
	
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
	}
}
