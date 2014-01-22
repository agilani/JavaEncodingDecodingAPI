package ie.gmit.Encoder.JUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import ie.gmit.Encoder.Coder;
import ie.gmit.Encoder.CoderFactory;
import ie.gmit.Encoder.CodingTypes;
import ie.gmit.Encoder.CustomExceptions.InvalidInputException;
import ie.gmit.Encoder.CustomExceptions.NoSuchCodingTypeException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoderFactoryTest {

	private Coder coder;
	 
	@Before
	public void createInstance() throws NoSuchCodingTypeException {
		coder = CoderFactory.getCoder(CodingTypes.Base64);		
	}

	@After
	public void destroyInstance() {
		coder = null;
	}
	
	@Test
	public void successfullEncodingTest() throws InvalidInputException, IOException, NoSuchCodingTypeException {
		assertEquals("aHR0cDovL3d3dy5hZ2lsYW5pLm1lLw==", coder.encode("http://www.agilani.me/"));
	}
 
	@Test
	public void successfullDecodingTest() throws InvalidInputException, IOException, NoSuchCodingTypeException {
		assertEquals("adeel gilani", coder.decode("YWRlZWwgZ2lsYW5p"));
	}

	@Test(expected=InvalidInputException.class)
	public void failEncodingTest() throws InvalidInputException, IOException, NoSuchCodingTypeException {
		assertNotNull(coder.decode("1W"));
	}
	
	@Test(expected=NoSuchCodingTypeException.class)
	public void wrongCodingTypeTest() throws NoSuchCodingTypeException {
		coder = CoderFactory.getCoder("Dummy Coding Type");
	}
	
}
