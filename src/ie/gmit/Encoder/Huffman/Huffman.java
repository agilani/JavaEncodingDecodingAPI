package ie.gmit.Encoder.Huffman;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Map.Entry;

import ie.gmit.Encoder.Coder;
import ie.gmit.Encoder.ReadContents;
import ie.gmit.Encoder.Base64.Base64;
import ie.gmit.Encoder.CustomExceptions.InvalidInputException;
import ie.gmit.Encoder.CustomExceptions.NoSuchCodingTypeException;

/**
 * @author A Gilani
 * This class encodes and decodes data using huffman encoding technique.
 */
public final class Huffman implements Coder {

	/* (non-Javadoc)
	 * @see ie.gmit.Coder#encode(java.io.File)
	 */
	@Override
	public String encode(File file) throws IOException, NoSuchCodingTypeException, InvalidInputException {
		 String str = ReadContents.fromFile(file);
		 return encode(str);
	}

	/* (non-Javadoc)
	 * @see ie.gmit.Coder#encode(java.net.URL)
	 */
	@Override
	public String encode(URL url) throws IOException, NoSuchCodingTypeException, InvalidInputException {
		String str = ReadContents.fromURL(url);
		return encode(str);
	}

	/* (non-Javadoc)
	 * @see ie.gmit.Coder#encode(java.lang.String)
	 */
	@Override
	public String encode(String string) throws IOException, NoSuchCodingTypeException, InvalidInputException
	{
		freqTable = new FrequencyTable().calculate(string); // generate the frequency table

		priorityQueue = new PriorityQueue<Node>(1, new FrequencyComparator()); // make a priority queue to hold nodes

		int treeSize = 0; // to keep track for the size of tree

		// making a priority queue of nodes
		for (Character c : freqTable.keySet()) {
			Node node = new Node(); // create new node
			node.setAlpha(c); // assign the alphabet
			node.setFreq(freqTable.get(c)); // assign the frequency
			priorityQueue.add(node); // add the new node to the priority queue
			treeSize++;
		}

		Node root = buildHuffmanTree(treeSize); // get handle on the root of the tree

		buildHelperTables(root); //build char to code and code to character tables for encoding and decoding
		
		String encodedString = ""; // a string to store the encoded data and the header
		
		// map the original string's each char to the code using helper table
		for (int i = 0; i < string.length(); i++)
		{
			encodedString += charToCode.get(string.charAt(i));
		}

		/*
		 * 
		// prints out encoded data
		System.out.println("Encoded Data: " + encodedData+"\r\n\r\n1");
		*/
		
		/*
		 * 
		// prints out the entry set in the code to char table... 
		for(Iterator<Entry<String, Character>> i1 = codeToChar.entrySet().iterator(); i1.hasNext();)
		{
			Entry<String, Character> item1 =  i1.next();
			System.out.println("String -> " + item1.getKey() + " --- Char -> " + item1.getValue());
		}*/
		
	
		//DataSerializable ed = new DataSerializable(); // initializes the DataSerializeable class
		DataSerializable.getInstance().outputMap.put((HashMap<String, Character>) codeToChar, encodedString); // store codeToChar helper table as key and encoded message as value
		
		String encodedData = objectToString(DataSerializable.getInstance()); // serialize and convert the serialized object to a string using helper method 
		
		return encodedData; // return the string
	}
	
	/* (non-Javadoc)
	 * @see ie.gmit.Coder#decode(java.io.File)
	 */
	@Override
	public String decode(File file) throws IOException, NoSuchCodingTypeException, InvalidInputException {
		 String str = ReadContents.fromFile(file);
		 return decode(str);
	}

	/* (non-Javadoc)
	 * @see ie.gmit.Coder#decode(java.net.URL)
	 */
	@Override
	public String decode(URL url) throws IOException, NoSuchCodingTypeException, InvalidInputException {
		String str = ReadContents.fromURL(url);
		return decode(str);
	}

	/* (non-Javadoc)
	 * @see ie.gmit.Coder#decode(java.lang.String)
	 */
	@Override
	public String decode(String string) throws IOException, NoSuchCodingTypeException, InvalidInputException
	{
		DataSerializable.setInstance(objectFromString(string));
		
		String encodedString = "";
		
		for(Iterator<Entry<HashMap<String, Character>, String>> i = DataSerializable.getInstance().outputMap.entrySet().iterator(); i.hasNext(); )
		{
		    Entry<HashMap<String, Character>, String> item = i.next();
		    codeToChar = item.getKey();
		    encodedString = item.getValue();
		}
		
        String temp = new String();
        String result = new String();
 
        for(int i = 0; i < encodedString.length(); i++) {
            temp = temp + encodedString.charAt(i);
 
            if(codeToChar.containsKey(temp)) {
                result = result + codeToChar.get(temp);
                temp = new String();
            }
        }
 
        return result;
	}

	
	///////////////////////////////////////////////////////
	
	
	private static Map<Character, Integer> freqTable; // store the frequency table of letters in string
	private static Queue<Node> priorityQueue; // a queue to sort the frequency table data
	private static Map<Character, String> charToCode; // a helper table/map to store character and code for character
	private static Map<String, Character> codeToChar; //  a helper table/map to store code and character
	
	
	// This method builds the table for the compression and decompression
	private static void buildHelperTables(Node root) {
		charToCode = new HashMap<Character, String>(); //to encode
		codeToChar = new HashMap<String, Character>(); //to decode

		traverseInPostOrder(root, new String());
	}

	// This method is used to traverse from ROOT-to-LEAVES
	private static void traverseInPostOrder(Node n, String s)
	{
		if (n == null)
			return;
		//System.out.println("At node: " + n.getAlpha() + " - > " + n.getFreq() + " -> " + s);
		traverseInPostOrder(n.getLeft(), s + "0"); // keep going to the left
		traverseInPostOrder(n.getRight(), s + "1"); // keep going to the right
		// Visit only nodes with keys
		if (n.getAlpha() != '\0') {
			//System.out.println("\'" + n.getAlpha() + "\' -> " + s);
			charToCode.put(n.getAlpha(), s); // store character as key and mapped code as value in helper table
			codeToChar.put(s, n.getAlpha()); // store mapped code as key and character as value in helper table
		}
	}
	
	
	/* This method makes the huffman tree and return the root node
	// create a new node
	// poll the head of the priority queue and add to the left
	// poll the next head and add to the right
	// set new node's frequency as the combine value of both polled nodes
	// add new node to the priorityqueue
	// keep repeating untill the size -1 of the treesize
	// return the new tree
	 * 
	 */
	private static Node buildHuffmanTree(int n)
	{
		Node x, y;

		for (int i = 1; i <= n - 1; i++) {
			Node z = new Node();
			z.setLeft(x = priorityQueue.poll());
			z.setRight(y = priorityQueue.poll());
			z.setFreq(x.getFreq() + y.getFreq());
			priorityQueue.add(z);
			//System.out.println("Left -> \'" + x.getAlpha() + "\' -> " + x.getFreq());
			//System.out.println("Right -> \'" + y.getAlpha() + "\' -> " + y.getFreq());
		}

		return priorityQueue.poll();
	}
	
    /** Read the object from Base64 string. 
     * @throws NoSuchCodingTypeException
     * @throws InvalidInputException 
     * */
    private static DataSerializable objectFromString(String string) throws IOException, NoSuchCodingTypeException, InvalidInputException {
    	Base64 ab = new Base64();
    	byte[] data = ab.decodeStringToByteArray(string);
    	DataSerializable deserializedObject;
    	try
    	{
	    	ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
	        deserializedObject  = (DataSerializable) ois.readObject();
	        ois.close();
	        return deserializedObject;
    	}
    	catch(Exception e)
    	{
    		throw new InvalidInputException("Huffman header data not found. Cannot decode.");
    	}
    }

    
    /** Write the object to a Base64 string.
     * @throws NoSuchCodingTypeException 
     * @throws InvalidInputException 
     *  */
    private static String objectToString(Serializable object) throws IOException, NoSuchCodingTypeException, InvalidInputException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        oos.close();
        Base64 ab = new Base64();
        return new String( ab.encode( baos.toByteArray() ) );
    }
}
