package es.uniovi.asw.trivial.output;

import es.uniovi.asw.trivial.util.FileUtil;

public class FileOutput implements Output {

	private String file;

	public FileOutput(String file) {
		if (file == null)
			throw new IllegalArgumentException(
					"La salida en fichero requiere un fichero de salida!");

		this.file = file;
	}

	@Override
	public void output(String content) {
		FileUtil.saveFile(file, content);
	}
}
