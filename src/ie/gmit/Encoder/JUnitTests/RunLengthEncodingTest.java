package ie.gmit.Encoder.JUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import ie.gmit.Encoder.CustomExceptions.InvalidInputException;
import ie.gmit.Encoder.Runlength.Runlength;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 
public class RunLengthEncodingTest {
	private Runlength RLE;
 
	@Before
	public void createInstance() {
		RLE = new Runlength();		
	}

	@After
	public void destroyInstance() {
		RLE = null;
	}
	
	@Test
	public void successfullEncodingTest() throws InvalidInputException {
		assertEquals("1W1B1W1B1W1B1W1B1W1B1W1B1W1B", RLE.encode("WBWBWBWBWBWBWB"));
	}
 
	@Test
	public void successfullDecodingTest() throws InvalidInputException {
		assertEquals("WBWBWBWBWBWBWB", RLE.decode("1W1B1W1B1W1B1W1B1W1B1W1B1W1B"));
 
	}

	@Test(expected=InvalidInputException.class)
	public void failEncodingTest() throws InvalidInputException {
		assertNotNull(RLE.encode("1W")); //because runlength is only for alphabets
	}
	
	
}