package ejercicio1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

import util.UtilArchivo;
import util.UtilString;

public class TestStream {
	private static final String ARCHIVO_ENTRADA = "file/quijote.txt";
	private static final String ARCHIVO_SALIDA = "file/quijote.descifrado.txt";
	private static final String ARCHIVO_CIFRADO = "file/quijote.cifrado.txt";
	private static final String ALGORITMO = "AES";
	private Cipher cipher;
	private Key key;

	public TestStream() throws Exception {
		this.cipher = Cipher.getInstance(ALGORITMO);
		this.key = getKey();
		System.out.println("key = " + UtilString.getBase64(this.key.getEncoded()));
	}

	private Key getKey() throws Exception {
		return KeyGenerator.getInstance(ALGORITMO).generateKey();
	}
	
	public static void main(String[] args) throws Exception {
		new TestStream().iniciar();
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
		ByteArrayInputStream bis = new ByteArrayInputStream(cipherText);
		CipherInputStream cis = new CipherInputStream(bis, cipher);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte b = (byte)cis.read();
		while (b != -1) {
			baos.write(b);	
			b = (byte)cis.read();
		}
		cis.close();
		
		return baos.toByteArray();
	}

	private byte[] cifrar(byte[] plainText) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		CipherOutputStream cos = new CipherOutputStream(bos, cipher);
		
		cos.write(plainText);
		cos.close();
		
		return bos.toByteArray();
	}
}
