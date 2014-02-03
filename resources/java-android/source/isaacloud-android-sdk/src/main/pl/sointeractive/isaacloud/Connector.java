package pl.sointeractive.isaacloud;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;

import pl.sointeractive.isaacloud.connection.HttpResponse;
import pl.sointeractive.isaacloud.connection.HttpToken;
import android.util.Base64;

/**
 * Connector class for the Android SDK.
 * 
 * @author Mateusz Renes
 * 
 */
public class Connector {
	private String baseUrl;
	private String version;

	private static HttpToken httpToken;

	private String oauthUrl;
	private String clientId;
	private String clientSecret;
	

	public Connector(String baseUrl, String oauthUrl, String version,
			Map<String, String> config) {
		this.baseUrl = baseUrl;
		this.oauthUrl = oauthUrl;
		this.setVersion(version);
		httpToken = new HttpToken();

		if (config.containsKey("clientId")) {
			this.clientId = config.get("clientId");
		} else {
			System.out.println("Did not define clientId");
		}

		if (config.containsKey("secret")) {
			this.clientSecret = config.get("secret");
		} else {
			System.out.println("Did not define secret");
		}

	}

	// I think this method could be private. The authentication process should
	// transparent for the user, he doesnt need to know how it is done or when.
	// He will never have to get authentication himself, the getService method
	// will do that for him. Please verify.
	public String getAuthentication() throws JSONException, IOException {
		if (!isTokenValid()) {
			getAccessTokenData();
		}
		System.out.println(httpToken.getAuthorizationHeader());
		return httpToken.getAuthorizationHeader();
	}

	public void getAccessTokenData() throws JSONException, IOException {
		// generate credentials
		String base64EncodedCredentials = null;
		base64EncodedCredentials = Base64.encodeToString(
				(clientId + ":" + clientSecret).getBytes("US-ASCII"),
				Base64.DEFAULT);
		String auth = "Basic " + base64EncodedCredentials;
		// setup connection
		URL url = new URL(this.oauthUrl + "/token");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		// setup headers
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.setRequestProperty("Authorization", auth);
		// set body
		OutputStream os = new BufferedOutputStream(connection.getOutputStream());
		os.write("grant_type=client_credentials".getBytes("UTF-8"));
		os.flush();
		os.close();
		// connect
		connection.connect();
		// check response code
		int responseCode = connection.getResponseCode();
		// get result string
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String resultString = reader.readLine();
		// build response
		HttpResponse.Builder responseBuilder = new HttpResponse.Builder();
		responseBuilder.setMethod("POST");
		responseBuilder.setResponseCode(responseCode);
		if (resultString != null) {
			responseBuilder.setResponseString(resultString);
			responseBuilder.setIsValid(true);
		} else {
			responseBuilder.setIsValid(false);
		}
		HttpResponse response = responseBuilder.build();
		// disconnect
		connection.disconnect();
		// update token time
		long currentTime = new Date().getTime();
		httpToken.setUpdateTime(currentTime);
		// save token data
		httpToken.setTokenTimeToLive(response.getJSONObject().getInt(
				"expires_in"));
		httpToken.setAccessToken(response.getJSONObject().getString(
				"access_token"));
		httpToken
				.setTokenType(response.getJSONObject().getString("token_type"));
	}

	public HttpResponse callService(String uri, String methodName,
			Map<String, Object> parameters, String body) throws IOException,
			JSONException {
		// parse parameters
		String targetUri = baseUrl + uri;
		for (Entry<String, Object> entry : parameters.entrySet()) {
			String entryKey = "{" + entry.getKey() + "}";
			System.out.println(entryKey);
			if (targetUri.contains(entryKey))
				System.out.println("Replace " + entryKey + " with "
						+ entry.getValue());
			targetUri = targetUri
					.replace(entryKey, entry.getValue().toString());
		}
		System.out.println(targetUri);
		// setup connection
		URL url = new URL(targetUri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		if (methodName.equals("POST")) {
			connection.setDoOutput(true);
		} else {
			connection.setDoOutput(false);
		}
		connection.setDoInput(true);
		connection.setRequestMethod(methodName);
		// setup headers
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Authorization", getAuthentication());
		// setup body (optional)
		if (body != null) {
			OutputStream os = new BufferedOutputStream(
					connection.getOutputStream());
			os.write(body.getBytes("UTF-8"));
			os.flush();
			os.close();
		}
		// connect
		connection.connect();
		// get result string
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String resultString = reader.readLine();
		// check response code
		int responseCode = connection.getResponseCode();
		// disconnect
		connection.disconnect();
		// build response
		HttpResponse.Builder responseBuilder = new HttpResponse.Builder();
		responseBuilder.setMethod(methodName);
		responseBuilder.setResponseCode(responseCode);
		if (resultString != null) {
			responseBuilder.setResponseString(resultString);
			responseBuilder.setIsValid(true);
		} else {
			responseBuilder.setIsValid(false);
		}
		HttpResponse response = responseBuilder.build();
		// return response
		return response;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	// Same as getAuthentication(), this method could be private. Please verify.
	public static boolean isTokenValid() {
		long currentTime = new Date().getTime();
		if (currentTime > httpToken.getUpdateTime()
				+ httpToken.getTokenTimeToLive() * 1000) {
			return false;
		} else
			return true;
	}

}
