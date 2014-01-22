package ie.gmit.Encoder.Runner;

import ie.gmit.Encoder.Coder;
import ie.gmit.Encoder.CoderFactory;
import ie.gmit.Encoder.CodingTypes;
import ie.gmit.Encoder.CustomExceptions.InvalidInputException;
import ie.gmit.Encoder.CustomExceptions.NoSuchCodingTypeException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Runner {

	public static void main(String[] args){

		//This is my runner class, just to show the usuage of the underlying classes
		
	
		//////////////Functionality using with factory pattern and CodingTypes enum
		//////////////Base 64 encoding of the contents of a URL and decoding of the result of encoding and decoding
		Coder myCoder;
		try {
			myCoder = CoderFactory.getCoder(CodingTypes.Base64);
			String result = myCoder.encode(new URL("http://www.agilani.me/"));
			System.out.println("Test 1:\r\nFunctionality using with factory pattern and CodingTypes enum\r\nEncoding Result: " + result);
			
			result = myCoder.decode(result); //takes a string and decodes it.
			System.out.println("Decoding Result: " + result); 
		} catch (NoSuchCodingTypeException e) {
			// this gets thrown when encoding type specified is not recognized but in this case never 
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// This gets thrown if URL is not valid
			e.printStackTrace();
		} catch (IOException e) {
			// This gets thrown if contents at URL cannot be read 
			e.printStackTrace();
		} catch (InvalidInputException e) {
			// This gets thrown when data being decoded was not encoded using the requested coding type
			e.printStackTrace();
		}
		
		
		//////////////Functionality using with factory pattern and CodingTypes String
		//////////////Huffman encoding of the string and decoding of the result of encoding and decoding
		
		try {
			myCoder = CoderFactory.getCoder("huffman");
			String result = myCoder.encode("abc");
			System.out.println("\r\n\r\nTest 2:\r\nFunctionality using with factory pattern and string as codingtype\r\nEncoding Result: " + result);
			
			result = myCoder.decode(result);
			System.out.println("Decoding Result: " + result);
		} catch (NoSuchCodingTypeException e) {
			// this gets thrown when encoding type specified is not recognized but in this case never 
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// This gets thrown if URL is not valid
			e.printStackTrace();
		} catch (IOException e) {
			// This gets thrown if contents at URL cannot be read 
			e.printStackTrace();
		} catch (InvalidInputException e) {
			// This gets thrown when data being decoded was not encoded using the requested coding type
			e.printStackTrace();
		}

		//////////////Functionality using with initializing of CoderFactory class and invoking methods. Proxy pattern at play
		//////////////Runlength encoding of the contents of a URL and decoding of the result of encoding and decoding
		//////////////The method invocation takes two parameter CodingType and data source
		 
		try {
			CoderFactory cf = new CoderFactory();
			String result = cf.encode(CodingTypes.Runlength, "aaaaaaabbbbbbbbccccccccdddddddd");
			System.out.println("\r\n\r\nTest 2:\r\nFunctionality using with initializing of CoderFactory class and invoking methods. Proxy pattern at play\r\nThe method invocation takes two parameter CodingType and data source\r\nEncoding Result: " + result);
			
			result = cf.decode(CodingTypes.Runlength, result);
			System.out.println("Decoding Result: " + result);
		} catch (NoSuchCodingTypeException e) {
			// this gets thrown when encoding type specified is not recognized but in this case never 
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// This gets thrown if URL is not valid
			e.printStackTrace();
		} catch (IOException e) {
			// This gets thrown if contents at URL cannot be read 
			e.printStackTrace();
		} catch (InvalidInputException e) {
			// This gets thrown when data being encoded or decoded is not possible using the coding type
			e.printStackTrace();
		}
		
	}

}


