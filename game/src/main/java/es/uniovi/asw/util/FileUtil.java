package es.uniovi.asw.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class FileUtil {

    public static final String getFile(String location) {
        try {
            InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(location);
            String fileString = IOUtils.toString(is, Charsets.UTF_8);
            IOUtils.closeQuietly(is);

            return fileString;
        } catch (IOException e) {
            throw new IllegalArgumentException("No se puede abrir el archivo");
        }
    }

    public static BufferedImage getImage(String string) {
        try {
            InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(string);
            BufferedImage bi = ImageIO.read(is);
            IOUtils.closeQuietly(is);

            return bi;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
