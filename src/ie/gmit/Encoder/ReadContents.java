package ie.gmit.Encoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



public class ReadContents {
	
	public static String fromURL(URL url) throws IOException
	{
		/*
		InputStream is = url.openStream();
		ByteArrayOutputStream outputDoc = new ByteArrayOutputStream();
		byte buf[]=new byte[1024];
		int len;
		while((len=is.read(buf))>0)
		{
			outputDoc.write(buf,0, len);
		}
		outputDoc.close();
		
		return new String(buf);
		*/
		
		Document d = Jsoup.connect(url.toString()).get();
		return d.text();
	}
	
	public static String fromFile(File file) throws FileNotFoundException
	{
	    StringBuilder fileContents = new StringBuilder((int)file.length());
		Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");
        while(scanner.hasNextLine()) {        
            fileContents.append(scanner.nextLine() + lineSeparator);
        }
        scanner.close();
        return fileContents.toString();
	}	
}
