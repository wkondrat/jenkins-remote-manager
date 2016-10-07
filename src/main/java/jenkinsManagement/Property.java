package jenkinsManagement;

public class Property {
	private String url;
	private String user;
	private String token;	

	Property(String url, String user) {
		this.url = url;
		this.user = user;
	}
	
	Property(String url, String user, String token) {
		this.url = url;
		this.user = user;
		this.token = token;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
