package jenkinsManagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

public class JenkinsBuild {

	static Logger log = Logger.getLogger(JenkinsBuild.class.getName());
	
	public void startNewBuild() {
		Helpers helpers = new Helpers();
		HashMap<String, String> parameters = new HashMap<String, String>();
		String user = getProperty().getUser();
		String baseURL = getProperty().getUrl();
		String token = getProperty().getToken();
		String branch = "master";
		parameters.put("token", token);
		parameters.put("branch", branch);
		String params = helpers.parseMapToString(parameters);
		try {
			helpers.sendPostRequest(baseURL + "buildWithParameters?" + params, user);
		} catch (IOException e) {
			log.debug("Can not build project");
		}
	}

	public void startBuildWithParameters(String branch) {
		Helpers helpers = new Helpers();
		HashMap<String, String> parameters = new HashMap<String, String>();
		String user = getProperty().getUser();
		String baseURL = getProperty().getUrl();
		String token = getProperty().getToken();
		parameters.put("token", token);
		parameters.put("branch", branch);
		String params = helpers.parseMapToString(parameters);
		try {
			helpers.sendPostRequest(baseURL + "buildWithParameters?" + params, user);
		} catch (IOException e) {
			log.debug("Can not build project");
		}
	}

	public String checkBuildStatus() {
		Helpers helpers = new Helpers();
		String user = getProperty().getUser();
		String url = getProperty().getUrl();
		String output = "";
		try {
			output = helpers.sendPostRequest(url + "lastBuild/api/json", user);
		} catch (IOException e) {
			log.debug("Problem with checking build status");
		}
		
		return getBuildStatus(output);
	}

	private Property getProperty() {
		String user = "";
		String url = "";
		String token = "";
		Properties prop = new Properties();
		FileInputStream input = null;
		try {
			input = new FileInputStream("src/main/resources/jenkins.properties");
			prop.load(input);
			user = prop.getProperty("user");
			url = prop.getProperty("url");
			token = prop.getProperty("token");
		} catch (IOException ex) {
			log.debug("Can not open property file");
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.debug("Can not open property file");
				}
			}
		}
		Property property = new Property(url, user, token);
		return property;
	}
	
	private String getBuildStatus(String buildData) {
		String statusOfBuild = "";
		String resultOfBuild = "result";

		if (buildData.contains(resultOfBuild)) {
			int startingLocationOfSearchingPhrase = buildData.indexOf(resultOfBuild) - 1;
			int endingLocationOfSearchingPhrase = buildData.indexOf(',', startingLocationOfSearchingPhrase);
			statusOfBuild = buildData.substring(startingLocationOfSearchingPhrase, endingLocationOfSearchingPhrase);
		}

		return statusOfBuild;
	}


}
