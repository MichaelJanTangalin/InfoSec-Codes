package infoSec;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Scanner;

import javax.crypto.Cipher;


public class InfoSec {

	public static void main(String[] args) throws Exception{
		
		Scanner inputText = new Scanner(System.in);
		System.out.println("Encryption / Decription Activity");
		System.out.print("\nEnter Text: ");
		String text = inputText.nextLine();
		
		
		   //Creating a Signature object
	      Signature sign = Signature.getInstance("SHA256withRSA");
	      
	      //Creating KeyPair generator object
	      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
	      
	      //Initializing the key pair generator
	      keyPairGen.initialize(2048);
	      
	      //Generate the pair of keys
	      KeyPair pair = keyPairGen.generateKeyPair();   
	      
	      //Getting the public key from the key pair
	      PublicKey publicKey = pair.getPublic();  

	      //Getting the private key from the key pair
	      PrivateKey privateKey = pair.getPrivate();  
	      
	      //Creating a Cipher object
	      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

	      //Initializing a Cipher object
	      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		  
	      //Add data to the cipher
	      byte[] input = text.getBytes();	  
	      cipher.update(input);
		  
	      //encrypting the data
	      byte[] cipherText = cipher.doFinal();	 
	      
	      System.out.println("Encrypted Text:\n");
	      System.out.println( new String(cipherText, "UTF8"));

	      System.out.println();
	      //Initializing the same cipher for decryption
	      
	      System.out.println("Decrypted Text: ");
	      cipher.init(Cipher.DECRYPT_MODE, privateKey);
	      
	      //Decrypting the text
	      byte[] decipheredText = cipher.doFinal(cipherText);
	      System.out.println(new String(decipheredText));
	      
	      // Signing the encrypted text
	      sign.initSign(privateKey);
	      sign.update(cipherText);
	      byte[] signature = sign.sign();
	      
	      System.out.println("\nSignature: ");
	      System.out.println(new String(signature, "UTF8"));
	      
	      inputText.close();
	   }
	}