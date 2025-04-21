package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Property_Utility {
	
	public String FetchDataFromPropFile(String key) throws IOException {
		
		FileInputStream fis= new FileInputStream("./src/test/resources/CommonData.properties");
		
		Properties p = new Properties();
		p.load(fis);
		String data = p.getProperty(key);
		return data;
		
	}
	
	public void WritedDataBackToPropFile(String key, String value) throws IOException {
		//fetch the data from properties file
		FileInputStream fis= new FileInputStream("./src/test/resources/CommonData.properties");
		
		//Create properties object
		Properties p = new Properties();
		//Store an empty container
		p.load(fis);
		p.put(key, value);
		FileOutputStream fos= new FileOutputStream("./src/test/resources/CommonData.properties");
		p.store(fos, "update successfully");
		System.out.println("Data inserted into property file");
		
		
	}

}
