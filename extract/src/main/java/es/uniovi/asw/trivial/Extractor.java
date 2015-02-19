package es.uniovi.asw.trivial;

import java.util.List;

import es.uniovi.asw.trivial.parsers.GIFTParser;
import es.uniovi.asw.trivial.parsers.Parser;
import es.uniovi.asw.trivial.parsers.XMLParser;
import es.uniovi.asw.trivial.preguntas.Pregunta;
import es.uniovi.asw.trivial.serializers.JSONSerializer;
import es.uniovi.asw.trivial.serializers.Serializer;

public class Extractor {

	public void usage() {
		System.out.println("Welcome to Trivial Extractor");
	}

	public String extract(String entrada, String formato, String salida) {
		Parser parser;

		// Seleccionamos el parser en funcion de los parametros
		if (formato.equalsIgnoreCase("gift"))
			parser = new GIFTParser();
		else if (formato.equalsIgnoreCase("xml"))
			parser = new XMLParser();
		else
			throw new RuntimeException("Formato no soportado");

		// Parseamos las preguntas
		List<Pregunta> preguntas = parser.parse(entrada);

		// Por ahora solo hay un tipo de salida, pero si hubiera mas irian aqui
		Serializer serializer = new JSONSerializer();

		// Obtenemos las preguntas en JSON
		String preguntasJSON = serializer.serialize(preguntas);

		return preguntasJSON;
	}

	public static void main(String[] args) {
		Extractor ex = new Extractor();

		if (args.length < 3) {
			ex.usage();
			return;
		}

		String preguntas = ex.extract(args[0], args[1], args[2]);

		System.out.println(preguntas);
	}
}
