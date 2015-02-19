package es.uniovi.asw.trivial.parsers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.uniovi.asw.trivial.preguntas.Category;
import es.uniovi.asw.trivial.preguntas.Pregunta;

/**
 * Esta clase se encarga de parsear el formato XML de preguntas y respuestas
 * multiples. El formato es el siguiente:
 * 
 * <code>
 * 	<questiondata> 
		<questioncategory>Categoria</questioncategory> 
		<questiontext>Pregunta</questiontext>
		<questionanswer id="1" text="Respuesa 1"/>
		<questionanswer id="2" text="Respuesta 2"/>  
		<questionanswer id="3" text="Respuesta 3"/> 
		<questionanswer id="4" text="Respuesta 4"/>
		<correctanswerid>Id de la respuesta correcta</correctanswerid> 
	</questiondata> 
 * <code>
 * 
 * El orden de las respuestas no es importante.
 * odas las preguntas necesitan una categoria.
 * 
 * @author Jose Manuel Garcia Fernandez
 *
 */
public class XMLParser implements Parser {
	private Map<String, Pregunta> preguntas = new HashMap<String, Pregunta>();

	
	private Pregunta preguntaActual;
	private int numIncorrectas;
	private boolean preguntaInvalida;

	@Override
	public List<Pregunta> parse(String file) {
		InputStream is = null;
		   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = null;
	        Document doc = null;
	        XPathExpression expr = null;
	        Object result = null;
	        
		try {
			is = new FileInputStream(file);
			builder = factory.newDocumentBuilder();     
			doc = builder.parse(is);
			
			doc.getDocumentElement().normalize();

	 
	        XPath xpath = XPathFactory.newInstance().newXPath(); // XPath Query for
	                                                                // showing all
	                                                                // nodes value
	        
	        expr = xpath
			        .compile("/question/questiondata");
	        
	        result = expr.evaluate(doc, XPathConstants.NODESET);
			
		} catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e1) {
			System.out.println("Error en l aprertura del fichero");
		} 
		
        NodeList questionsXML = (NodeList) result;
        
        Element emp = null;
        for (int i = 0; i < questionsXML.getLength(); i++) {
            emp = (Element) questionsXML.item(i);
            
            // Reseteamos los valores
			numIncorrectas = 0;
			preguntaInvalida = false;
			
			// Y creamos una nueva pregunta
			preguntaActual = new Pregunta();
            
            String questionText=emp.getElementsByTagName("questiontext").item(0).getTextContent();
            String category=emp.getElementsByTagName("questioncategory").item(0).getTextContent();
            String idCorrect=emp.getElementsByTagName("correctanswerid").item(0).getTextContent();
            
            //comprobamos que los parametros anteriores sean correctos
            if(questionText==null || category==null || idCorrect==null)
            		preguntaInvalida=true;
            
            //ponemos el texto y l acategoria a la nueva pregunta
            preguntaActual.setQueryText(questionText);
			preguntaActual.setCategory(getCat(category));
            
            NodeList answers=emp.getElementsByTagName("questionanswer"); 
            Element ans = null;
            for(int j=0;j<answers.getLength();j++){
            	
            	ans = (Element) answers.item(j);
            	if(ans.getAttribute("id").charAt(0)==idCorrect.charAt(0))
            		preguntaActual.setCorrectAnswer(ans.getAttribute("text"));
            	else
            		preguntaActual.getWrongAnswers()[numIncorrectas++] = ans.getAttribute("text");
            }

            if (preguntaActual.getCorrectAnswer() == null) {
				System.err.println("La pregunta en linea " + i
						+ " no tiene respuesta correcta, saltando");
				preguntaInvalida = true;
			}

			if (numIncorrectas != Pregunta.NUM_ANSWERS - 1) {
				System.err.println("La pregunta en linea " + i
						+ " tiene un número incorrecto de respuestas, saltando");
				preguntaInvalida = true;
			}

			if (!preguntaInvalida) {
				if (preguntas.containsKey(preguntaActual.getQueryText())) {
					System.err.println("La pregunta en linea " + i + " es repetida, saltando");
				}

				preguntas.put(preguntaActual.getQueryText(), preguntaActual);
				preguntaActual = null;
			}
        }
        return new ArrayList<Pregunta>(preguntas.values());
	}
	
	private static Category getCat(String categ) {
		categ = categ.toLowerCase();

		if (categ.contains("arte"))
			return Category.ARTEYLITERATURA;

		if (categ.contains("ciencia"))
			return Category.CIENCIAYTECNOLOGIA;

		if (categ.contains("deportes"))
			return Category.DEPORTES;

		if (categ.contains("entretenimiento"))
			return Category.ESPECTACULOSYENTRETENIMIENTO;

		if (categ.contains("geograf"))
			return Category.GEOGRAFIA;

		if (categ.contains("historia"))
			return Category.HISTORIA;

		return null;
	}

}
