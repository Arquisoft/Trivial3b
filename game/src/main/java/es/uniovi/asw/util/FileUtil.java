package es.uniovi.asw.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {

    public static final String getFile(String location) {
        try {
            return FileUtils.readFileToString(new File(location), "UTF-8");
        } catch (IOException e) {
            throw new IllegalArgumentException("No se puede abrir el archivo");
        }
    }

    public static final void saveFile(String location, String content) {
        try {
            FileUtils.writeStringToFile(new File(location), content, "UTF-8");
        } catch (IOException e) {
            throw new IllegalArgumentException("No ha sido posible "
                    + "guardar su fichero");
        }
    }
}
