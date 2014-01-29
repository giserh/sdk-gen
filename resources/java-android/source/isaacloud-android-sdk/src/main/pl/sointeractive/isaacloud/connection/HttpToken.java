package pl.sointeractive.isaacloud.connection;

public class HttpToken {

	private String tokenType;
	private String expiresIn;
	private String accessToken;
	
	public HttpToken(String tokenType, String expiresIn, String accessToken){
		this.setTokenType(tokenType);
		this.setExpiresIn(expiresIn);
		this.setAccessToken(accessToken);
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
