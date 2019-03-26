package dust;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Parser {
	public void Parsing(String json, ValueDTO value) throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(json);
		JSONArray arr = (JSONArray)obj.get("list");
		for(int i = 0;i < arr.size(); i++) {
			JSONObject arrobj = (JSONObject)arr.get(i);
			value.setPm10Value(i, arrobj.get("pm10Value").toString());
			value.setPm25Value(i, arrobj.get("pm25Value").toString());
		}
	}
}
