package es.uniovi.asw.trivial.output;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileOutput implements Output {

	private String file;

	public FileOutput(String file) {
		if (file == null)
			throw new IllegalArgumentException(
					"La salida en fichero requiere un fichero de salida!");

		this.file = file;
	}

	@Override
	public void save(String out) {
		try {
			FileUtils.writeStringToFile(new File(file), out);
		} catch (IOException e) {
			System.err.println("No ha sido posible guardar su fichero");
		}
	}
}
