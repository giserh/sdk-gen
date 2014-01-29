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
	private static String baseUrl;
	private static String version;
	
	private HttpToken httpToken;

	private static String oauthUrl;
	private static String clientId;
	private static String clientSecret;
	private static long tokenRetrievalTime = 0;
	private static String currentToken = "";

	public Connector(String baseUrl, String oauthUrl, String version,
			Map<String, String> config) {
		Connector.baseUrl = baseUrl;
		Connector.oauthUrl = oauthUrl;
		this.setVersion(version);

		if (config.containsKey("clientId")) {
			Connector.clientId = config.get("clientId");
		} else {
			System.out.println("Did not define clientId");
		}

		if (config.containsKey("secret")) {
			Connector.clientSecret = config.get("secret");
		} else {
			System.out.println("Did not define secret");
		}

	}

	public static String getAuthentication() throws JSONException, IOException {
		long currentTime = new Date().getTime();
		if (tokenRetrievalTime==0 || currentTime > Connector.tokenRetrievalTime + 3000 * 1000) {
			//update token time
			Connector.tokenRetrievalTime = currentTime;
			// generate credentials
			String base64EncodedCredentials = null;
			base64EncodedCredentials = Base64.encodeToString(
					(clientId + ":" + clientSecret).getBytes("US-ASCII"),
					Base64.DEFAULT);
			String auth = "Basic " + base64EncodedCredentials;
			// setup connection
			URL url = new URL(Connector.oauthUrl + "/token");
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			// setup headers
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Authorization", auth);
			// set body
			OutputStream os = new BufferedOutputStream(
					connection.getOutputStream());
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
			// save token
			Connector.currentToken = response.getJSONObject().getString(
					"access_token");

		}
		System.out.println(currentToken);
		return "Bearer " + currentToken;
	}

	public HttpResponse callService(String uri, String methodName,
			Map<String, Object> parameters, String body) throws IOException,
			JSONException {
		// parse parameters
		String targetUri = Connector.baseUrl + uri;
		for (Entry<String, Object> entry : parameters.entrySet()) {
			String entryKey = "{"+entry.getKey()+"}";
			System.out.println(entryKey);
			if (targetUri.contains(entryKey))
				System.out.println("Replace "+ entryKey + " with "+ entry.getValue());
				targetUri = targetUri.replace(entryKey, entry.getValue().toString());
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
		connection.setRequestProperty("Authorization",
				Connector.getAuthentication());
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
		// check response code
		int responseCode = connection.getResponseCode();
		// get result string
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String resultString = reader.readLine();
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
		Connector.version = version;
	}
}
