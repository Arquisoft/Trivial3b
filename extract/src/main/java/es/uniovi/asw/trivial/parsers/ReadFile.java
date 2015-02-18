package es.uniovi.asw.trivial.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public static BufferedReader getFile(String file) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(file);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			return br;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
		}
		return br;
	}

	public static String readFile(String file) {
		BufferedReader br = getFile(file);

		String out = "";

		try {
			String line;
			while ((line = br.readLine()) != null) {
				out += line + "\n";
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { br.close(); } catch (Exception e) { }
		}

		return out;
	}
}
