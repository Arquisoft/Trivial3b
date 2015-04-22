package util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class FileUtil {

    public static final String getFile(String location) {
        try {
            InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(location);
            String fileString = IOUtils.toString(is);
            IOUtils.closeQuietly(is);

            return fileString;
        } catch (IOException e) {
            throw new IllegalArgumentException("No se puede abrir el archivo");
        }
    }
}
