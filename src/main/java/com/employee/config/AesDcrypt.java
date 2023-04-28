package com.employee.config;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AesDcrypt {
private static final String SECRET_KEY = "secret_Key";  
private static final String SALTVALUE = "abcdefg";  

public static String decrypt(String strToDecrypt){  
	try {  
		/* Declare a byte array. */  
		byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  
		IvParameterSpec ivspec = new IvParameterSpec(iv);  
		
		/* Create factory for secret keys. */  
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
		
		/* PBEKeySpec class implements KeySpec interface. */  
		KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);  
		SecretKey tmp = factory.generateSecret(spec);  
		SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");  
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);  
		
		/* Retruns decrypted value. */  
		return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));  
}   

	catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e)   
		{  
			System.out.println("Error occured during decryption: " + e.toString());  
		}  
		return null;  
}
}