package ejercicio1;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import util.UtilArchivo;
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

		byte[] cipherText = cifrar(UtilArchivo.leerArchivo(ARCHIVO_ENTRADA));
		UtilArchivo.escribirArchivo(cipherText, ARCHIVO_CIFRADO);

		byte[] plainText = descifrar(UtilArchivo.leerArchivo(ARCHIVO_CIFRADO));
		UtilArchivo.escribirArchivo(plainText, ARCHIVO_SALIDA);
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
