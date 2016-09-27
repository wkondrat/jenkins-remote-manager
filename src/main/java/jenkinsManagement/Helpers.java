package jenkinsManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class Helpers {

	static Logger log = Logger.getLogger(Helpers.class.getName());

	public String sendPostRequest(String address, String user) throws IOException {
		String line;
		String response = "";
		String authEncoded = Base64.getEncoder().encodeToString(user.getBytes());

		URL url = new URL(address);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Authorization", "Basic " + authEncoded);
		InputStream content = (InputStream) connection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(content));
		while ((line = in.readLine()) != null) {
			response += line;
		}
		return response;
	}

	public String getMapValues(HashMap<String, String> parameters) {
		String output = "";

		Iterator<Entry<String, String>> entries = parameters.entrySet().iterator();

		while (entries.hasNext()) {
			Entry thisEntry = entries.next();
			String value = (String) thisEntry.getValue();
			output += value;
			if (entries.hasNext()) {
				output += "&";
			}
		}
		return output;
	}

	public String parseMapToString(HashMap<String, String> parameters) {
		String output = "";

		Iterator<Entry<String, String>> entries = parameters.entrySet().iterator();

		while (entries.hasNext()) {
			Entry thisEntry = entries.next();
			String param = (String) thisEntry.getKey();
			String value = (String) thisEntry.getValue();
			output += param + "=" + value;
			if (entries.hasNext()) {
				output += "&";
			}
		}
		return output;
	}

}
