package pl.sointeractive.isaacloud.connection;

/**
 * Enum for keeping every possible http method type.
 * @author Mateusz Renes
 *
 */

public enum HttpMethod {
	POST("POST"),
	GET("GET");
	
	private final String method;
	
	HttpMethod(final String method){
		this.method = method;
	}
	
	@Override
    public String toString() {
        return method;
    }
}
