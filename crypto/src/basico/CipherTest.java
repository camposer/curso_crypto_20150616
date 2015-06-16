package basico;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import util.UtilString;

public class CipherTest {
	private static final String ALGORITMO = "AES";
	private static final String PASSPHRASE = "123456";
	private static final String SALT = "123";
	private Cipher cipher;
	private Key key;

	public CipherTest() throws Exception {
		this.cipher = Cipher.getInstance(ALGORITMO);
		this.key = getKey1(PASSPHRASE);
		System.out.println("key = " + UtilString.getBase64(this.key.getEncoded()));
	}

	private Key getKey(String passphrase) throws Exception {
		return KeyGenerator.getInstance(ALGORITMO).generateKey();
	}
	
	private Key getKey1(String passphrase) throws Exception {
		byte[] innerKey = (SALT + ":" + PASSPHRASE).getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		innerKey = sha.digest(innerKey);
		innerKey = Arrays.copyOf(innerKey, 16); // use only first 128 bit
		
		return new SecretKeySpec(innerKey, "AES");
	}

	private Key getKey2(String passphrase) throws Exception {
		byte[] seed = new byte[16];
		seed = Arrays.copyOf(passphrase.getBytes(), 16);
				
		return new SecretKeySpec(seed, "AES");
	}

	public static void main(String[] args) throws Exception {
		new CipherTest().iniciar();
	}

	private void iniciar() throws Exception {
		System.out.println("Cifrar Hola mundo");
		byte[] cipherText = cifrar("Hola mundo");
		System.out.println("Texto cifrado = " + UtilString.getBase64(cipherText));
		byte[] plainText = descifrar(cipherText);
		
		System.out.println("Mensaje descifrado = " + new String(plainText));
	}

	private byte[] descifrar(byte[] cipherText) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(cipherText);
	}

	private byte[] cifrar(String string) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal("Hola mundo".getBytes());
	}
}
