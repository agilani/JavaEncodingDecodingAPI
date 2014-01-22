package ie.gmit.Encoder.Base64;

public class Mapping {

	private static Mapping m; //instance of this class
	
	private char[] unicodeMap = new char[64]; //to store the unicode letters

    private byte[] base64Map = new byte[128]; //to store the base64 letters
	
	/** This is default constructor which calls the createMapping() method to create maps
	 * 
	 */
	private Mapping() {
		createMaping();
	}
	
	/** This method returns an initialized object of Mapping class
	 * @return Mapping
	 */
	public static Mapping getInstanceOf()
	{
		//if m which is a Mapping object is null, first initialize it.
		if(m==null)
			m = new Mapping();

		return m;
	}
	
	/** This method creates the Unicode map and Base64 map
	 * @return nothing
	 * @param nothing
	 */
	private void createMaping()
	{
        int i = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            unicodeMap[i++] = c;
        }
        for (char c = 'a'; c <= 'z'; c++) {
            unicodeMap[i++] = c;
        }
        for (char c = '0'; c <= '9'; c++) {
            unicodeMap[i++] = c;
        }
        unicodeMap[i++] = '+';
        unicodeMap[i++] = '/';
        
        
        //Mapping table from Base64 characters to 6-bit nibbles.
        for (i = 0; i < base64Map.length; i++)
        {
            base64Map[i] = -1;
        }
        
        for (i = 0; i < 64; i++) {
            base64Map[unicodeMap[i]] = (byte) i;
        }
		
	}
	
	/** This method returns the UniCode Mapping
	 * @return char[]
	 */
	public char[] getUniCodeMapping()
	{
		return unicodeMap;
	}
	
	/** This method returns the Base64 Mapping
	 * @return byte[]
	 */
	public byte[] getBase64Mapping()
	{
		return base64Map;
	}

	
	
}
