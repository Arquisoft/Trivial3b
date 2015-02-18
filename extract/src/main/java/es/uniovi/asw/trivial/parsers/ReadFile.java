package es.uniovi.asw.trivial.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFile {

	public static BufferedReader getFile(String file, Parser parser ) {
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
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return br;
	}

}
