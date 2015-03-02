package es.uniovi.asw.trivial;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import es.uniovi.asw.trivial.output.FileOutput;
import es.uniovi.asw.trivial.util.FileUtil;

public class OutputTest {

    // Esta carpeta es temporal y se borrará cuando acabe JUnit
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void fileUtilTest() throws IOException {
        // Creamos un archivo al azar
        String texto = "abc\ndef\n";
        String file = folder.newFile().getAbsolutePath();

        // Lo guardamos
        new FileOutput(file).output(texto);

        // Y lo obtenemos de vuelta
        String nuevoTexto = FileUtil.getFile(file);

        // Y su contenido debería ser el mismo
        assertEquals(texto, nuevoTexto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullTest() throws IOException {
        new FileOutput(null).output(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void readExceptionTest() throws IOException {
        // Creamos un archivo al azar que no existe
        String file = folder.newFile().getAbsolutePath() + "sdjkf";

        // Y lo intentamos obtener
        FileUtil.getFile(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void writeExceptionTest() throws IOException {
        // Creamos una carpeta
        String folderStr = folder.newFolder().getAbsolutePath();

        // Como es una carpeta, no se puede escribir como si fuera un fichero
        FileUtil.saveFile(folderStr, "ABCDE");
    }

}
