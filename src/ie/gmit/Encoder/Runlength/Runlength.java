package ie.gmit.Encoder.Runlength;

import ie.gmit.Encoder.Coder;
import ie.gmit.Encoder.ReadContents;
import ie.gmit.Encoder.CustomExceptions.InvalidInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author A Gilani
 *This class encodes and decodes data using Run-Length encoding technique.
 */
public class Runlength implements Coder {

	/* (non-Javadoc)
	 * @see ie.gmit.Coder#encode(java.io.File)
	 */
	@Override
	public String encode(File file) throws FileNotFoundException, InvalidInputException {
		String str = ReadContents.fromFile(file);
		return encode(str);
	}

	/* (non-Javadoc)
	 * @see ie.gmit.Coder#encode(java.net.URL)
	 */
	@Override
	public String encode(URL url) throws IOException, InvalidInputException {
		String str = ReadContents.fromURL(url);
		return encode(str);
	}

	/* (non-Javadoc)
	 * @see ie.gmit.Coder#encode(java.lang.String)
	 */
	@Override
	public String encode(String string) throws InvalidInputException {
		
		if(!isAlphaOnly(string))
		{
			throw new InvalidInputException("Numbers cannot be encoded");
		}
		
		
        StringBuffer dest = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            int runLength = 1;
            while (i+1 < string.length() && string.charAt(i) == string.charAt(i+1)) {
                runLength++;
                i++;
            }
            dest.append(runLength);
            dest.append(string.charAt(i));
        }
        return dest.toString();
	}

	/* (non-Javadoc)
	 * @see ie.gmit.Coder#decode(java.io.File)
	 */
	@Override
	public String decode(File file) throws FileNotFoundException, InvalidInputException {
		 String str = ReadContents.fromFile(file);
		 return decode(str);
	}

	/* (non-Javadoc)
	 * @see ie.gmit.Coder#decode(java.net.URL)
	 */
	@Override
	public String decode(URL url) throws IOException, InvalidInputException {
		String str = ReadContents.fromURL(url);
		return decode(str);
	}

	/* (non-Javadoc)
	 * @see ie.gmit.Coder#decode(java.lang.String)
	 */
	@Override
	public String decode(String string) throws InvalidInputException {
		try
		{
        StringBuffer dest = new StringBuffer();
        Pattern pattern = Pattern.compile("[0-9]+|[\\W\\w]");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
       		//System.out.println("Char: " + matcher.group());
            int number = Integer.parseInt(matcher.group());
            matcher.find();
            while (number-- != 0) {
                dest.append(matcher.group());
            }
        }
        return dest.toString();
		}
		catch(Exception e)
		{
			throw new InvalidInputException("Decoding operation failed.\r\n"+e.getMessage());
		}
	}

	
	private boolean isAlphaOnly(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }

	    return true;
	}

}
