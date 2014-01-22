package ie.gmit.Encoder.Base64;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import ie.gmit.Encoder.Coder;
import ie.gmit.Encoder.ReadContents;
import ie.gmit.Encoder.CustomExceptions.InvalidInputException;


/**
 * @author A Gilani
 * This class encodes and decodes data using Base64 encoding technique.
 *
 */
public class Base64 implements Coder {
	
	/**
	 * Stores the character uni code map
	 */
	private char[] map1 = Mapping.getInstanceOf().getUniCodeMapping();
 
    /**
     * Store Base64 map
     */
    private byte[] map2 = Mapping.getInstanceOf().getBase64Mapping();
 
    /**
     * Encodes a byte array into Base64 format. No blanks or line breaks are
     * inserted.
     *
     * @param in an array containing the data bytes to be encoded.
     * @param iLen number of bytes to process in <code>in</code>.
     * @return A character array with the Base64 encoded data.
     * @throws InvalidInputException 
     */
    public char[] encode(byte[] in, int iLen) throws InvalidInputException {
    	try
    	{
        int oDataLen = (iLen * 4 + 2) / 3;       // output length without padding
        int oLen = ((iLen + 2) / 3) * 4;         // output length including padding
        char[] out = new char[oLen];
        int ip = 0;
        int op = 0;
        while (ip < iLen) {
            int i0 = in[ip++] & 0xff;
            int i1 = ip < iLen ? in[ip++] & 0xff : 0;
            int i2 = ip < iLen ? in[ip++] & 0xff : 0;
            int o0 = i0 >>> 2;
            int o1 = ((i0 & 3) << 4) | (i1 >>> 4);
            int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
            int o3 = i2 & 0x3F;
            out[op++] = map1[o0];
            out[op++] = map1[o1];
            out[op] = op < oDataLen ? map1[o2] : '=';
            op++;
            out[op] = op < oDataLen ? map1[o3] : '=';
            op++;
        }
        return out;
    	}
    	catch(Exception e)
    	{
    		throw new InvalidInputException("Encoding operation failed.\r\n"+e.getMessage());
    	}
    }

    /**
     * Decodes a byte array from Base64 format. No blanks or line breaks are
     * allowed within the Base64 encoded data.
     *
     * @param in a character array containing the Base64 encoded data.
     * @return An array containing the decoded data bytes.
     * @throws InvalidInputException 
     * @throws IllegalArgumentException if the input is not valid Base64 encoded
     * data.
     */
    private byte[] decode(char[] in) throws InvalidInputException {
    	try
    	{
        int iLen = in.length;
        if (iLen % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        while (iLen > 0 && in[iLen - 1] == '=') {
            iLen--;
        }
        int oLen = (iLen * 3) / 4;
        byte[] out = new byte[oLen];
        int ip = 0;
        int op = 0;
        while (ip < iLen) {
            int i0 = in[ip++];
            int i1 = in[ip++];
            int i2 = ip < iLen ? in[ip++] : 'A';
            int i3 = ip < iLen ? in[ip++] : 'A';
            if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int b0 = map2[i0];
            int b1 = map2[i1];
            int b2 = map2[i2];
            int b3 = map2[i3];
            if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int o0 = (b0 << 2) | (b1 >>> 4);
            int o1 = ((b1 & 0xf) << 4) | (b2 >>> 2);
            int o2 = ((b2 & 3) << 6) | b3;
            out[op++] = (byte) o0;
            if (op < oLen) {
                out[op++] = (byte) o1;
            }
            if (op < oLen) {
                out[op++] = (byte) o2;
            }
        }
        return out;
    	}
    	catch(Exception e)
    	{
    		throw new InvalidInputException("Decoding operation failed.\r\n"+e.getMessage());
    	}
    }

    /**
     * Decodes a byte array from Base64 format.
     * @param s  a Base64 String to be decoded.
     * @return   An array containing the decoded data bytes.
     * @throws InvalidInputException 
     * @throws   IllegalArgumentException if the input is not valid Base64 encoded data.
     */
     public byte[] decodeStringToByteArray(String s) throws InvalidInputException {
        return decode(s.toCharArray()); }
     
     /**
     * Encodes a byte array into Base64 format.
     * No blanks or line breaks are inserted.
     * @param in  an array containing the data bytes to be encoded.
     * @return    A character array with the Base64 encoded data.
     * @throws InvalidInputException 
     */
     public char[] encode(byte[] in) throws InvalidInputException {
        return encode(in,in.length); }
    
     
/////////////////////////////////////////////////////
     
     
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
        return new String(encode( string.getBytes(), string.length()));
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
		return new String(decode(string.toCharArray()));
	}

	

}
