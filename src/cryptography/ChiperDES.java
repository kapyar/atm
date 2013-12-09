package cryptography;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class ChiperDES {

	private static Cipher desCipher;
	private static boolean isInstanse = false;

	private static void instanse() {
		try {
			desCipher = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static byte[] encode(Object inObj, BigInteger key) {
		if (!isInstanse) {
			instanse();
			isInstanse = true;
		}

		SecretKey myDesKey;
		byte[] dataEncrypted = null;
		try {
			myDesKey = SecretKeyFactory.getInstance("DES").generateSecret(
					new DESKeySpec(key.toString().getBytes()));
			desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
			dataEncrypted = desCipher.doFinal(inObj.toString().getBytes());
		} catch (InvalidKeyException | InvalidKeySpecException
				| NoSuchAlgorithmException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataEncrypted;

	}

	public static byte[] decode(byte[] inObj, BigInteger key) {
		if (!isInstanse) {
			instanse();
			isInstanse = true;
		}
		SecretKey myDesKey;
		byte[] dataDecrypted = null;
		try {
			myDesKey = SecretKeyFactory.getInstance("DES").generateSecret(
					new DESKeySpec(key.toString().getBytes()));
			desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
			// dataDecrypted = desCipher.doFinal(inObj.toString().getBytes());
			dataDecrypted = desCipher.doFinal(inObj);
		} catch (InvalidKeyException | InvalidKeySpecException
				| NoSuchAlgorithmException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataDecrypted;

	}

}
