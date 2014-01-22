package ie.gmit.Encoder;

import ie.gmit.Encoder.CustomExceptions.InvalidInputException;
import ie.gmit.Encoder.CustomExceptions.NoSuchCodingTypeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * @author A Gilani
 * This interface participates in the overall factory pattern of this API
 * <br>Many exceptions associated with the method might not even get thrown but
 * <br>for a safer execution of the overall system, almost all posibilities have been taken in account
 * <br>thus user has to account for the throwables.
 * <br><br>P.S: I could call this interface 'CoderInterface' but only coder seem nicer
 */
public interface Coder {
	
	/** Encodes the contents of a file
	 * @param file
	 * @return String
	 * @throws FileNotFoundException
	 * @throws IOException 
	 * @throws NoSuchCodingTypeException 
	 * @throws InvalidInputException 
	 */
	public String encode(File file) throws FileNotFoundException, IOException, NoSuchCodingTypeException, InvalidInputException;
	
	
	/** Encodes the contents of a URL
	 * @param url
	 * @return String
	 * @throws IOException
	 * @throws NoSuchCodingTypeException 
	 * @throws InvalidInputException 
	 */
	public String encode(URL url) throws IOException, NoSuchCodingTypeException, InvalidInputException;
	
	
    /** Encodes a string
     * @param string
     * @return String
     * @throws NoSuchCodingTypeException 
     * @throws IOException 
     * @throws InvalidInputException 
     */
    public String encode(String string) throws IOException, NoSuchCodingTypeException, InvalidInputException;

    
	/** Decodes the contents of the file
	 * @param file
	 * @return String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws NoSuchCodingTypeException
	 * @throws InvalidInputException
	 */
	public String decode(File file) throws FileNotFoundException, IOException, NoSuchCodingTypeException, InvalidInputException;
	
	
	/** Decodes the contents of a URL
	 * @param url
	 * @return String
	 * @throws IOException
	 * @throws NoSuchCodingTypeException
	 * @throws InvalidInputException
	 */
	public String decode(URL url) throws IOException, NoSuchCodingTypeException, InvalidInputException;
	
	
    /** Decode a string
     * @param string
     * @return String
     * @throws IOException
     * @throws NoSuchCodingTypeException
     * @throws InvalidInputException
     */
    public String decode(String string) throws IOException, NoSuchCodingTypeException, InvalidInputException;
}
