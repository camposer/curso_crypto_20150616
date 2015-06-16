package basico;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

import util.UtilString;

public class TestPK {
	private static final String ALGORITMO = "RSA";
	private Cipher cipher;
	private Key publicKey;
	private Key privateKey;

	public TestPK() throws Exception {
		this.cipher = Cipher.getInstance(ALGORITMO);
		generateKeys();
	}

	private void generateKeys() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		this.publicKey = keyPair.getPublic();
		this.privateKey = keyPair.getPrivate();
	}
	
	public static void main(String[] args) throws Exception {
		new TestPK().iniciar();
	}

	private void iniciar() throws Exception {
		System.out.println("Cifrar Hola mundo");
		String text = "Hola mundo";
		
		byte[] cipherText = cifrar(text);
		System.out.println("Texto cifrado = " + UtilString.getBase64(cipherText));

		byte[] plainText = descifrar(cipherText);
		System.out.println("Mensaje descifrado = " + new String(plainText));
	}

	private byte[] descifrar(byte[] cipherText) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(cipherText);
	}

	private byte[] cifrar(String string) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(string.getBytes());
	}
}
