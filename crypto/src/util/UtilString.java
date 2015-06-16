package util;

import java.util.Base64;

public class UtilString {

	public static String getBase64(byte[] cipherText) {
		return Base64.getEncoder().encodeToString(cipherText);
	}

}
