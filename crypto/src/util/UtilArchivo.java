package util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class UtilArchivo {

	public static void escribirArchivo(byte[] text, String archivo) throws Exception {
		Files.write(new File(archivo).toPath(), text, StandardOpenOption.TRUNCATE_EXISTING);
	}

	public static byte[] leerArchivo(String archivo) throws Exception {
		return Files.readAllBytes(new File(archivo).toPath());
	}

}
