package ejercicio1;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

import util.UtilArchivo;

public class TestPK {
	private static final String ARCHIVO_ENTRADA = "file/quijote.txt";
	private static final String ARCHIVO_SALIDA = "file/quijote.descifrado.txt";
	private static final String ARCHIVO_CIFRADO = "file/quijote.cifrado.txt";
	private static final String ALGORITMO = "RSA"; // http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#impl
	public static final int MAX_BLOQUE_CIFRADO = 117; 
	public static final int MAX_BLOQUE_DESCIFRADO = 128;	
	private Cipher cipher;
	private Key publicKey;
	private Key privateKey;
	

	public TestPK() throws Exception {
		this.cipher = Cipher.getInstance(ALGORITMO);
		generateKeys();
	}

	private void generateKeys() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator
				.getInstance(ALGORITMO);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		this.publicKey = keyPair.getPublic();
		this.privateKey = keyPair.getPrivate();
	}

	public static void main(String[] args) throws Exception {
		new TestPK().iniciar();
	}

	private void iniciar() throws Exception {
		System.out.println("Cifrando quijote.txt");

		byte[] cipherText = cifrar(UtilArchivo.leerArchivo(ARCHIVO_ENTRADA));
		UtilArchivo.escribirArchivo(cipherText, ARCHIVO_CIFRADO);

		byte[] plainText = descifrar(UtilArchivo.leerArchivo(ARCHIVO_CIFRADO));
		UtilArchivo.escribirArchivo(plainText, ARCHIVO_SALIDA);
	}

	private byte[] descifrar(byte[] cipherText) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		int bloques = (int) Math.ceil((double) cipherText.length
				/ MAX_BLOQUE_DESCIFRADO);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int maxBloqueDescifradoLen = MAX_BLOQUE_DESCIFRADO;
		for (int i = 0; i < bloques; i++) {
			if (i + 1 == bloques)
				maxBloqueDescifradoLen = cipherText.length
						- (i * MAX_BLOQUE_DESCIFRADO);

			byte[] bloque = cipher.doFinal(cipherText, i * MAX_BLOQUE_DESCIFRADO,
					maxBloqueDescifradoLen);

			baos.write(bloque);
		}

		return baos.toByteArray();
	}

	private byte[] cifrar(byte[] plainText) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		int bloques = (int) Math.ceil((double) plainText.length
				/ MAX_BLOQUE_CIFRADO);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int maxBloqueCifradoLen = MAX_BLOQUE_CIFRADO;

		for (int i = 0; i < bloques; i++) {
			if (i + 1 == bloques)
				maxBloqueCifradoLen = plainText.length
						- (i * MAX_BLOQUE_CIFRADO);

			byte[] bloqueCifrado = cipher.doFinal(plainText, i
					* MAX_BLOQUE_CIFRADO, maxBloqueCifradoLen);

			baos.write(bloqueCifrado);
		}

		return baos.toByteArray();
	}
}
