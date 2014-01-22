package ie.gmit.Encoder;

import ie.gmit.Encoder.Base64.Base64;
import ie.gmit.Encoder.CustomExceptions.InvalidInputException;
import ie.gmit.Encoder.CustomExceptions.NoSuchCodingTypeException;
import ie.gmit.Encoder.Huffman.Huffman;
import ie.gmit.Encoder.Runlength.Runlength;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author A Gilani
 * This class participates in the overall Factory pattern at play
 * <br>But i have defined few auxiliary methods so user can initialize the class and invoke methods
 * <br>This other system would fall under proxy pattern. I think but not sure as i am still trying to learn
 * <br>to differentiate the small difference between different patterns.
 */
public class CoderFactory
{
	/**
	 * This property will hold the implementation of the Coder interface
	 */
	private static Coder coderType;
	
	/**
	 * Default constructor of this class
	 */
	public CoderFactory()
	{}
	
	/** This method returns the underlying concrete class which implements the coder interface
	 * The parameter is CodingTypes which is a an ENUM for tight coupling
	 * @param CodingTypes
	 * @return Coder
	 * @throws NoSuchCodingTypeException
	 */
	public static Coder getCoder(CodingTypes CodingTypes) throws NoSuchCodingTypeException
	{
		switch(CodingTypes)
		{
		case Base64:
			coderType = (Coder) new Base64();
			break;
		case Runlength:
			coderType = (Coder) new Runlength();
			break;
		case Huffman:
			coderType = (Coder) new Huffman();
			break;
		default:
			throw new NoSuchCodingTypeException("No such coding type");
		}
		
		return coderType;
	}
	
	/** This method returns the underlying concrete class which implements the coder interface
	 * <br>the parameter can be any of the 'Base64', 'Huffman', 'Runlength'
	 * @param CodingTypes which is a string
	 * @return Object which is a Coder
	 * @throws NoSuchCodingTypeException
	 */
	public static Coder getCoder(String CodingTypes) throws NoSuchCodingTypeException
	{
		switch(CodingTypes.trim().toLowerCase())
		{
		case "base64":
			coderType = (Coder) new Base64();
			break;
		case "runlength":
			coderType = (Coder) new Runlength();
			break;
		case "huffman":
			coderType = (Coder) new Huffman();
			break;
		default:
			throw new NoSuchCodingTypeException("No such coding type");
		}
		
		return coderType;
	}
	
	/** Decodes the contents of the File using the CodingType specified
	 * @param codingType
	 * @param file
	 * @return String
	 * @throws NoSuchCodingTypeException
	 * @throws IOException
	 * @throws InvalidInputException 
	 */
	public String encode(CodingTypes codingType, File file) throws NoSuchCodingTypeException, IOException, InvalidInputException
	{
		String output = null;
		
		switch(codingType)
		{
			case Base64:
				coderType = CoderFactory.getCoder(CodingTypes.Base64);
				output = coderType.encode(file);
				break;
			case Runlength:
				coderType = CoderFactory.getCoder(CodingTypes.Runlength);
				output = coderType.encode(file);
				break;
			case Huffman:
				coderType = CoderFactory.getCoder(CodingTypes.Huffman);
				output = coderType.encode(file);
				break;
			default:
				throw new NoSuchCodingTypeException("No such coding type");
		}
		
		return output;
		
	}
	
	/** Encodes the contents of the URL using the CodingType specified
	 * @param codingType
	 * @param url
	 * @return String
	 * @throws NoSuchCodingTypeException
	 * @throws IOException
	 * @throws InvalidInputException 
	 */
	public String encode(CodingTypes codingType, URL url) throws NoSuchCodingTypeException, IOException, InvalidInputException
	{
		String output = null;
		
		switch(codingType)
		{
			case Base64:
				coderType = CoderFactory.getCoder(CodingTypes.Base64);
				output = coderType.encode(url);
				break;
			case Runlength:
				coderType = CoderFactory.getCoder(CodingTypes.Runlength);
				output = coderType.encode(url);
				break;
			case Huffman:
				coderType = CoderFactory.getCoder(CodingTypes.Huffman);
				output = coderType.encode(url);
				break;
			default:
				throw new NoSuchCodingTypeException("No such coding type");
		}
		
		return output;
		
	}

	/** Encodes the String using the CodingType specified
	 * @param codingType
	 * @param string
	 * @return String
	 * @throws NoSuchCodingTypeException
	 * @throws IOException
	 * @throws InvalidInputException 
	 */
	public String encode(CodingTypes codingType, String string) throws NoSuchCodingTypeException, IOException, InvalidInputException
	{
		String output = null;
		
		switch(codingType)
		{
			case Base64:
				coderType = CoderFactory.getCoder(CodingTypes.Base64);
				output = coderType.encode(string);
				break;
			case Runlength:
				coderType = CoderFactory.getCoder(CodingTypes.Runlength);
				output = coderType.encode(string);
				break;
			case Huffman:
				coderType = CoderFactory.getCoder(CodingTypes.Huffman);
				output = coderType.encode(string);
				break;
			default:
				throw new NoSuchCodingTypeException("No such coding type");
		}
		
		return output;
		
	}
	
	/** Decodes the contents of the URL using the CodingType specified
	 * @param codingType
	 * @param url
	 * @return String
	 * @throws NoSuchCodingTypeException
	 * @throws IOException
	 * @throws InvalidInputException
	 */
	public String decode(CodingTypes codingType, URL url) throws NoSuchCodingTypeException, IOException, InvalidInputException
	{
		String output = null;
		
		switch(codingType)
		{
			case Base64:
				coderType = CoderFactory.getCoder(CodingTypes.Base64);
				output = coderType.decode(url);
				break;
			case Runlength:
				coderType = CoderFactory.getCoder(CodingTypes.Runlength);
				output = coderType.decode(url);
				break;
			case Huffman:
				coderType = CoderFactory.getCoder(CodingTypes.Huffman);
				output = coderType.decode(url);
				break;
			default:
				throw new NoSuchCodingTypeException("No such coding type");
		}
		
		return output;
		
	}	
	
	/** Decodes the contents of the File using the CodingType specified
	 * @param codingType
	 * @param file
	 * @return String
	 * @throws IOException
	 * @throws NoSuchCodingTypeException
	 * @throws InvalidInputException
	 */
	public String decode(CodingTypes codingType, File file) throws IOException, NoSuchCodingTypeException, InvalidInputException
	{
		String output = null;
		
		switch(codingType)
		{
			case Base64:
				coderType = CoderFactory.getCoder(CodingTypes.Base64);
				output = coderType.decode(file);
				break;
			case Runlength:
				coderType = CoderFactory.getCoder(CodingTypes.Runlength);
				output = coderType.decode(file);
				break;
			case Huffman:
				coderType = CoderFactory.getCoder(CodingTypes.Huffman);
				output = coderType.decode(file);
				break;
			default:
				throw new NoSuchCodingTypeException("No such coding type");
		}
		
		return output;
		
	}

	/** Decodes the String using the CodingType specified
	 * @param codingType
	 * @param string
	 * @return String
	 * @throws NoSuchCodingTypeException
	 * @throws IOException
	 * @throws InvalidInputException
	 */
	public String decode(CodingTypes codingType, String string) throws NoSuchCodingTypeException, IOException, InvalidInputException
	{
		String output = null;
		
		switch(codingType)
		{
			case Base64:
				coderType = CoderFactory.getCoder(CodingTypes.Base64);
				output = coderType.decode(string);
				break;
			case Runlength:
				coderType = CoderFactory.getCoder(CodingTypes.Runlength);
				output = coderType.decode(string);
				break;
			case Huffman:
				coderType = CoderFactory.getCoder(CodingTypes.Huffman);
				output = coderType.decode(string);
				break;
			default:
				throw new NoSuchCodingTypeException("No such coding type");
		}
		
		return output;
		
	}
	

}
