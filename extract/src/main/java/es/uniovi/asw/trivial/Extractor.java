package es.uniovi.asw.trivial;

import java.util.List;

import es.uniovi.asw.trivial.output.ConsoleOutput;
import es.uniovi.asw.trivial.output.FileOutput;
import es.uniovi.asw.trivial.output.MongoOutput;
import es.uniovi.asw.trivial.output.Output;
import es.uniovi.asw.trivial.parsers.GIFTParser;
import es.uniovi.asw.trivial.parsers.Parser;
import es.uniovi.asw.trivial.parsers.XMLParser;
import es.uniovi.asw.trivial.preguntas.Pregunta;
import es.uniovi.asw.trivial.serializers.JSONSerializer;
import es.uniovi.asw.trivial.serializers.Serializer;

public class Extractor {

	public void usage() {
		System.out.println("Bienvenido a Trivial Extractor");
		System.out .println("Uso: java -jar extract.jar formatoentrada entrada formatosalida tiposalida [ficherosalida]");
		System.out.println("\nLos formatos de entrada son: gift, xml");
		System.out.println("Los formatos de salida disponibles son: json");
		System.out.println("Los tipos de salida son: mongo, file, console");
		System.out.println("Para el tipo de salida file es necesario indicar un fichero de salida");
		System.out.println("\nEjemplo: java -jar extract.jar gift preguntas.gift file salida.json");
	}

	public String extract(String formatoEntrada, String entrada, String formatoSalida, String tipoSalida, String salida) {
		// Seleccionamos el parser en funcion de los parametros
		Parser parser = getParser(formatoEntrada);

		// Parseamos las preguntas
		List<Pregunta> preguntas = parser.parse(entrada);

		// Formato de salida
		Serializer serializer = getSerializer(formatoSalida);

		// Obtenemos las preguntas en JSON
		String preguntasJSON = serializer.serialize(preguntas);

		// Guardar las preguntas
		Output out = getOutput(tipoSalida, salida);
		out.save(preguntasJSON);

		return preguntasJSON;
	}

	private Parser getParser(String formato) {
		if (formato.equalsIgnoreCase("gift"))
			return new GIFTParser();

		else if (formato.equalsIgnoreCase("xml"))
			return new XMLParser();

		else
			throw new RuntimeException("Tipo de entrada no soportado");
	}

	private Serializer getSerializer(String formato) {
		if (formato.equalsIgnoreCase("json"))
			return new JSONSerializer();

		throw new IllegalArgumentException("Formato no soportado");
	}

	private Output getOutput(String tipoSalida, String salida) {
		if (tipoSalida.equalsIgnoreCase("console"))
			return new ConsoleOutput();

		else if (tipoSalida.equalsIgnoreCase("file"))
			return new FileOutput(salida);

		else if (tipoSalida.equalsIgnoreCase("mongo"))
			return new MongoOutput();

		else
			throw new RuntimeException("Tipo de salida no soportado");
	}

	public static void main(String[] args) {
		Extractor ex = new Extractor();

		if (args.length < 4) {
			ex.usage();
			return;
		}

		// El parametro salida prodría no existir,
		// si no existe pasamos null
		String salida = null;
		if (args.length == 5)
			salida = args[4];

		ex.extract(args[0], args[1], args[2], args[3], salida);
	}
}
