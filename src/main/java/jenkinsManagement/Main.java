package jenkinsManagement;

import org.apache.log4j.Logger;

public class Main {
	
	static Logger log = Logger.getLogger(Main.class.getName());

	
	public static void main(String[] args) {
		String branch = "master";
		JenkinsBuild test = new JenkinsBuild();
		//test.startNewBuild();
		//test.startBuildWithParameters(branch);
		log.debug(test.checkBuildStatus());
	}
}
