package ie.gmit.Encoder.Huffman;


import java.util.Hashtable;

/**
 * @author A Gilani
 * This class holds the frequency table of the characters in string provided by user
 */
public class FrequencyTable {

	/**
	 * a hashtable to store character and it's occurrence in integer in the string
	 */
	private Hashtable<Character,Integer> freq;
	
	/**
	 * Default constructor of this class. Initializes the freq.
	 */
	public FrequencyTable() {
		freq = new Hashtable<Character, Integer>();
	}

	/** this method counts the frequency of occurrence of same characters in the string and make a hashmap
	 * @param str
	 * @return Hashtable<Character,Integer> key is character and value is integer
	 */
	public Hashtable<Character,Integer> calculate(String str)
	{
		for(int i=0; i<str.length(); i++)
		{
			char key = str.charAt(i);
			if(freq.containsKey(key))
			{
				int value = freq.get(key);
				value += 1;
				freq.put(key,value);
			}
			else
			{
				freq.put(key,1);
			}
		}
		
		//freq = sortValue(freq);
		return freq;
	}
}
