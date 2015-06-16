package ejercicio1;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import util.UtilString;

public class TestSK {
	private static final String ARCHIVO_ENTRADA = "file/quijote.txt";
	private static final String ARCHIVO_SALIDA = "file/quijote.descifrado.txt";
	private static final String ARCHIVO_CIFRADO = "file/quijote.cifrado.txt";
	private static final String ALGORITMO = "AES";
	private Cipher cipher;
	private Key key;

	public TestSK() throws Exception {
		this.cipher = Cipher.getInstance(ALGORITMO);
		this.key = getKey();
		System.out.println("key = " + UtilString.getBase64(this.key.getEncoded()));
	}

	private Key getKey() throws Exception {
		return KeyGenerator.getInstance(ALGORITMO).generateKey();
	}
	
	public static void main(String[] args) throws Exception {
		new TestSK().iniciar();
	}

	private void iniciar() throws Exception {
		System.out.println("Cifrando quijote.txt");

		byte[] cipherText = cifrar(leerArchivo(ARCHIVO_ENTRADA));
		escribirArchivo(cipherText, ARCHIVO_CIFRADO);

		byte[] plainText = descifrar(cipherText);
		escribirArchivo(plainText, ARCHIVO_SALIDA);
	}

	private void escribirArchivo(byte[] text, String archivoSalida) throws Exception {
		Files.write(new File(archivoSalida).toPath(), text, StandardOpenOption.TRUNCATE_EXISTING);
	}

	private byte[] leerArchivo(String archivo) throws Exception {
		return Files.readAllBytes(new File(ARCHIVO_ENTRADA).toPath());
	}

	private byte[] descifrar(byte[] cipherText) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(cipherText);
	}

	private byte[] cifrar(byte[] plainText) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(plainText);
	}
}
