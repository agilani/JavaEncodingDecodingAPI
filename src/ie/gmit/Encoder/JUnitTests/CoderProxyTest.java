package ie.gmit.Encoder.JUnitTests;

import static org.junit.Assert.assertEquals;
import ie.gmit.Encoder.CoderFactory;
import ie.gmit.Encoder.CodingTypes;
import ie.gmit.Encoder.CustomExceptions.InvalidInputException;
import ie.gmit.Encoder.CustomExceptions.NoSuchCodingTypeException;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoderProxyTest {

	private CoderFactory coderFactory;
	 
	@Before
	public void createInstance() {
		coderFactory = new CoderFactory();		
	}

	@After
	public void destroyInstance() {
		coderFactory = null;
	}
	
	@Test
	public void successfullEncodingTest() throws InvalidInputException, IOException, NoSuchCodingTypeException {
		assertEquals("rO0ABXNyAChpZS5nbWl0LkVuY29kZXIuSHVmZm1hbi5EYXRhU2VyaWFsaXphYmxlAAAAAAAAAAECAAFMAAlvdXRwdXRNYXB0AA9MamF2YS91dGlsL01hcDt4cHNyABNqYXZhLnV0aWwuSGFzaHRhYmxlE7sPJSFK5LgDAAJGAApsb2FkRmFjdG9ySQAJdGhyZXNob2xkeHA/QAAAAAAACHcIAAAACwAAAAFzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAYdwgAAAAgAAAADnQABDAwMTFzcgATamF2YS5sYW5nLkNoYXJhY3RlcjSLR9lrGiZ4AgABQwAFdmFsdWV4cABodAADMDExc3EAfgAIAGl0AAUxMTAxMHNxAH4ACABwdAADMDAwc3EAfgAIAGF0AAUxMTEwMHNxAH4ACABldAAFMTExMTBzcQB+AAgAbHQABDExMDBzcQB+AAgAdHQABTExMTAxc3EAfgAIAG10AAUxMTExMXNxAH4ACABndAADMDEwc3EAfgAIAC50AAUxMTAxMXNxAH4ACABudAAEMDAxMHNxAH4ACAA6dAADMTAxc3EAfgAIAHd0AAMxMDBzcQB+AAgAL3h0AFIwMDExMTEwMDExMDAxMTAxMDAwMTAxMDAxMDAxMDExMDExMDEwMTAwMDAxMTExMTAxMTExMTEwMDAwMTEwMTEwMTEwMTAxMTEwMTExMTAwMTAweA==", coderFactory.encode(CodingTypes.Huffman ,"http://www.agilani.me/"));
	}
 
	@Test
	public void successfullDecodingTest() throws InvalidInputException, IOException, NoSuchCodingTypeException {
		assertEquals("abc", coderFactory.decode(CodingTypes.Huffman, "rO0ABXNyAChpZS5nbWl0LkVuY29kZXIuSHVmZm1hbi5EYXRhU2VyaWFsaXphYmxlAAAAAAAAAAECAAFMAAlvdXRwdXRNYXB0AA9MamF2YS91dGlsL01hcDt4cHNyABNqYXZhLnV0aWwuSGFzaHRhYmxlE7sPJSFK5LgDAAJGAApsb2FkRmFjdG9ySQAJdGhyZXNob2xkeHA/QAAAAAAACHcIAAAACwAAAAFzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAMdwgAAAAQAAAAA3QAAjEwc3IAE2phdmEubGFuZy5DaGFyYWN0ZXI0i0fZaxomeAIAAUMABXZhbHVleHAAYnQAATBzcQB+AAgAYXQAAjExc3EAfgAIAGN4dAAFMDEwMTF4"));
	}

	@Test(expected=InvalidInputException.class)
	public void failEncodingTest() throws InvalidInputException, IOException, NoSuchCodingTypeException {
		assertEquals("abcde", coderFactory.decode(CodingTypes.Huffman, "http://www.agilani.me/")); //fails because header info not found
	}
}
