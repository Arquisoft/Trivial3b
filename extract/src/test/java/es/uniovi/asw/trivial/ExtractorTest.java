package es.uniovi.asw.trivial;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.trivial.parsers.XMLParser;
import es.uniovi.asw.trivial.preguntas.Pregunta;

public class ExtractorTest {
	
	@Test
	public void emptyExtractor() {
		String args[] = {};
		Extractor ext = new Extractor();
	   // assertThat(ext.run(args)).isEqualTo(0);

		
		XMLParser parser=new XMLParser();
		List<Pregunta> a = parser.parse("src/main/resources/preguntas.xml");
		
		for (Pregunta question : a) {

			System.out.println(question.getJSON());
		}
		
	  }
//Prueba

}
