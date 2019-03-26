package dust;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIConnection {
	private String json;
	public APIConnection() {}
	public String getJson() throws Exception {
		BufferedReader rd;
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append("API URL주소");
		urlBuilder.append("?ServiceKey=서비스키");
		urlBuilder.append("&pageNo=1");
		urlBuilder.append("&sidoName=대전");
		urlBuilder.append("&searchCondition=HOUR");
		urlBuilder.append("&_returnType=json");
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		json = rd.readLine();
		return json;
	}
}
