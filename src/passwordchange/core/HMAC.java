package passwordchange.core;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class HMAC {

    public static String SHA256(String key, String text) 
    throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException  { 
		Mac mac = Mac.getInstance("HmacSHA256");
		// get the bytes of the hmac key and data string
		byte[] secretByte = key.getBytes("UTF-8");
		byte[] dataBytes = text.getBytes("UTF-8");
		SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");

		mac.init(secret);
		byte[] doFinal = mac.doFinal(dataBytes);
		byte[] hexB = new Hex().encode(doFinal);

        return CommonUtil.convertToHex(hexB);
    } 

    /**
	 * http://www.java-only.com/LoadTutorial.javaonly?id=62
	 */
	public static void main(String args[]) throws NoSuchAlgorithmException,
			KeyManagementException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {

		String macKey = "The HMAC key";
		String macData = "the data string";
		System.out.println("MACDATA:" + macData);

		Mac mac = Mac.getInstance("HmacSHA256");
		// get the bytes of the hmac key and data string
		byte[] secretByte = macKey.getBytes("UTF-8");
		byte[] dataBytes = macData.getBytes("UTF-8");
		SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");

		mac.init(secret);
		byte[] doFinal = mac.doFinal(dataBytes);
		byte[] hexB = new Hex().encode(doFinal);
		char[] checksum = Hex.encodeHex(doFinal);
	}
}
