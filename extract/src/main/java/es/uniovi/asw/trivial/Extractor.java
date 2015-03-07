package es.uniovi.asw.trivial;

import java.util.List;

import es.uniovi.asw.model.Pregunta;
import es.uniovi.asw.trivial.output.FileOutput;
import es.uniovi.asw.trivial.output.Output;
import es.uniovi.asw.trivial.parsers.GIFTParser;
import es.uniovi.asw.trivial.parsers.Parser;
import es.uniovi.asw.trivial.parsers.XMLParser;
import es.uniovi.asw.trivial.serializers.JSONSerializer;
import es.uniovi.asw.trivial.serializers.Serializer;
import es.uniovi.asw.trivial.upload.MongoUpload;
import es.uniovi.asw.trivial.upload.Uploader;
import es.uniovi.asw.trivial.util.FileUtil;

public class Extractor {

    public static void main(String[] args) {
        Extractor ex = new Extractor();
        ex.chooseOption(args);
    }

    public void chooseOption(String[] args) {
        if (args.length < 2) {
            usage();
            return;
        }

        String mode = args[0].toLowerCase();
        String input = args[1];

        if (mode.equals("extract")) {
            if (args.length < 5) {
                System.out.println("En el modo extract todos "
                        + "los parámetros son necesarios");
                return;
            }

            String inputFormat = args[2].toLowerCase();
            String output = args[3];
            String outputFormat = args[4].toLowerCase();

            extract(inputFormat, input, outputFormat, output);

        } else if (mode.equals("upload")) {

            String server = args.length < 3 ? null : args[2];
            upload(input, server);

        } else {
            System.out.println("Modo inválido");
        }
    }

    public void usage() {
        System.out.println("Bienvenido a Trivial Extractor");
        System.out.println("Uso: java -jar extract.jar <modo> <parametros>");
        System.out.println("\nModos:");
        System.out.println(" - extract: Extrae las preguntas a un fichero");
        System.out.println("\t - Parámetros: <entrada> <formatoentrada> "
                + "<salida> <formatosalida>");

        System.out.println("\t Los formatos de entrada son: gift, xml");
        System.out.println("\t Los formatos de salida son: json");

        System.out.println("\n - upload: Sube las preguntas extraídas a la bd");
        System.out.println("\t - Parámetros: <entrada> <servidor>");

        System.out.println("\t El servidor es de tipo url:puerto");
        System.out.println("\t En caso de omisión se supondrá localhost:27017");

    }

    public String extract(String formatoEntrada, String entrada,
            String formatoSalida, String salida) {
        // Seleccionamos el parser en funcion de los parametros
        Parser parser = getParser(formatoEntrada);

        // Parseamos las preguntas
        List<Pregunta> preguntas = parser.parse(entrada);

        // Formato de salida
        Serializer serializer = getSerializer(formatoSalida);

        // Obtenemos las preguntas en JSON
        String preguntasJSON = serializer.serialize(preguntas);

        // Guardar las preguntas
        Output out = new FileOutput(salida);
        out.output(preguntasJSON);

        return preguntasJSON;
    }

    public String upload(String file, String server) {
        // Obtenemos las preguntas
        String preguntasJSON = FileUtil.getFile(file);

        // Creamos el cargador
        Uploader up = new MongoUpload(server);

        // Y las subimos
        up.upload(preguntasJSON);

        return preguntasJSON;
    }

    private Parser getParser(String formato) {
        if (formato.equalsIgnoreCase("gift")) {
            return new GIFTParser();
        } else if (formato.equalsIgnoreCase("xml")) {
            return new XMLParser();
        } else {
            throw new RuntimeException("Tipo de entrada no soportado");
        }
    }

    private Serializer getSerializer(String formato) {
        if (formato.equalsIgnoreCase("json")) {
            return new JSONSerializer();
        }

        throw new IllegalArgumentException("Formato no soportado");
    }
}
