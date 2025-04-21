package GenericUtilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Jason_Utility {
	
	public String FetchDataFromJsonFile(String key) throws FileNotFoundException, IOException, ParseException

	{
		JSONParser parser= new JSONParser();
		Object obj= parser.parse(new FileReader(".src/test/resources/vtiger_json.json.txt"));
		JSONObject js = (JSONObject) obj;
		String data = js.get(key).toString();
		return data;
		
	}

}
 