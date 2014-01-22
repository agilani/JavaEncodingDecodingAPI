package ie.gmit.Encoder.JUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import ie.gmit.Encoder.Base64.Base64;
import ie.gmit.Encoder.CustomExceptions.InvalidInputException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Base64EncodingTest {
	private Base64 B64E;
	 
	@Before
	public void createInstance() {
		B64E = new Base64();		
	}

	@After
	public void destroyInstance() {
		B64E = null;
	}
	
	@Test
	public void successfullEncodingTest() throws InvalidInputException {
		assertEquals("aHR0cDovL3d3dy5hZ2lsYW5pLm1lLw==", B64E.encode("http://www.agilani.me/"));
	}
 
	@Test
	public void successfullDecodingTest() throws InvalidInputException {
		assertEquals("adeel gilani", B64E.decode("YWRlZWwgZ2lsYW5p"));
	}

	@Test(expected=InvalidInputException.class)
	public void failEncodingTest() throws InvalidInputException {
		assertNotNull(B64E.decode("1W"));
	}
	
}
