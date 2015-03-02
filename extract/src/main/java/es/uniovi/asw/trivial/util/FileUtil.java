package es.uniovi.asw.trivial.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {

	public static String getFile(String location) {
		try {
			return FileUtils.readFileToString(new File(location), "UTF-8");
		} catch (IOException e) {
			throw new IllegalArgumentException("No es posible abrir el archivo");
		}
	}

	public static void saveFile(String location, String content) {
		try {
			FileUtils.writeStringToFile(new File(location), content, "UTF-8");
		} catch (IOException e) {
			System.err.println("No ha sido posible guardar su fichero");
		}
	}
}
