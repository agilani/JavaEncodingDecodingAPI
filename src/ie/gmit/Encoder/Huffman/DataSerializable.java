package ie.gmit.Encoder.Huffman;

import ie.gmit.Encoder.CustomExceptions.InvalidInputException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author A Gilani
 * This singleton class is to have a serialize able map
 */
public class DataSerializable implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private static DataSerializable ds;
	
	/**
	 * a map to include header and encoded data, will be serialized
	 */
	public Map<HashMap<String, Character>,String> outputMap = new Hashtable<HashMap<String,Character>, String>();
	
	/**
	 * Default but private Constructor of the class
	 */
	private DataSerializable()
	{
	}
	
	/** This method provides a thread safe instance of this class
	 * @return DataSerializable
	 */
	public static synchronized DataSerializable getInstance()
	{
		if(ds==null)
			ds = new DataSerializable();
		return ds;
	}
	
	/** This method set instace of this class to the provided object
	 * @param object object has to be instance of DataSerializable
	 * @throws InvalidInputException 
	 */
	public static synchronized void setInstance(DataSerializable object) throws InvalidInputException
	{
		try
		{
			ds = object;
		}
		catch(Exception e)
		{
			throw new InvalidInputException("The data provided has no huffman header attached to it");
		}
	}
}
