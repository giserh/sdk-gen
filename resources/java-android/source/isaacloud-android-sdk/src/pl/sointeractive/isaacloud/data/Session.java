package pl.sointeractive.isaacloud.data;

public class Session {

	public static String token;
	public static String tokenType;
	public static int time;

	public static String getAuthenticationHeader() {
		return tokenType + " " + token;
	}
}
