package pl.sointeractive.isaacloud;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.json.JSONException;

import pl.sointeractive.isaacloud.connection.HttpResponse;
import pl.sointeractive.isaacloud.connection.HttpToken;
import pl.sointeractive.isaacloud.exceptions.InvalidConfigException;
import android.content.Context;
import android.util.Base64;
import android.util.Log;

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
	private String userEmail;
	private String userPassword;
	
	private SSLContext sslContext;

	/**
	 * Base constructor.
	 * 
	 * @param baseUrl
	 *            The base URL address of the API.
	 * @param oauthUrl
	 *            The OAuth URL of the API. Used to generate access token.
	 * @param version
	 *            Version of the API.
	 * @param config
	 *            COnfiguration parameters. Requires "clientId" and "secret"
	 *            keys and their respective values.
	 * @throws InvalidConfigException
	 *             Thrown when "clientId" or "secret" are not found in the
	 *             parameters.
	 */
	public Connector(Context appContext, String baseUrl, String oauthUrl,
			String version, Map<String, String> config)
			throws InvalidConfigException {
		this.baseUrl = baseUrl;
		this.oauthUrl = oauthUrl;
		this.setVersion(version);

		httpToken = new HttpToken();
		
		if (config.containsKey("clientId")) {
			this.clientId = config.get("clientId");
		} else {
			throw new InvalidConfigException("clientId");
		}

		if (config.containsKey("secret")) {
			this.clientSecret = config.get("secret");
		} else {
			throw new InvalidConfigException("secret");
		}
		
		/*
		if (config.containsKey("userEmail")) {
			this.userEmail = config.get("userEmail");
		} else {
			throw new InvalidConfigException("userEmail");
		}
		
		if (config.containsKey("userPassword")) {
			this.userPassword = config.get("userPassword");
		} else {
			throw new InvalidConfigException("userPassword");
		}
		*/
		
		// certificate hangling
		CertificateFactory cf;
		try {
			// Load trusted IsaaCloud certificate
			cf = CertificateFactory.getInstance("X.509");
			InputStream caInput = new BufferedInputStream(appContext
					.getResources().openRawResource(R.raw.ca));
			Certificate ca;
			ca = cf.generateCertificate(caInput);
			System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
			caInput.close();

			// Create a KeyStore containing our trusted CAs
			String keyStoreType = KeyStore.getDefaultType();
			KeyStore keyStore = KeyStore.getInstance(keyStoreType);
			keyStore.load(null, null);
			keyStore.setCertificateEntry("ca", ca);

			// Create a TrustManager that trusts the CAs in our KeyStore
			String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
			TrustManagerFactory tmf = TrustManagerFactory
					.getInstance(tmfAlgorithm);
			tmf.init(keyStore);

			// Create an SSLContext that uses our TrustManager
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, tmf.getTrustManagers(), null);
			
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Returns the authentication token.
	 * 
	 * @return Authentication header in format: <token_type> <access_token>
	 * @throws JSONException
	 *             Thrown when an error occurs during JSON operations
	 * @throws IOException
	 *             Thrown when an error occurs during IO operations
	 */
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
		//base64EncodedCredentials = Base64.encodeToString((userEmail + ":" + userPassword).getBytes("US-ASCII"),Base64.DEFAULT);
		base64EncodedCredentials = Base64.encodeToString((clientId + ":" + clientSecret).getBytes("US-ASCII"),Base64.DEFAULT);
		String auth = "Basic " + base64EncodedCredentials;
		// setup connection
		//URL url = new URL(this.oauthUrl + "/gamification/12/application/29/authorize");
		URL url = new URL(this.oauthUrl + "/token");
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setConnectTimeout(Config.TIMEOUT);
		connection.setReadTimeout(Config.TIMEOUT);
		// set socket
		connection.setSSLSocketFactory(sslContext.getSocketFactory());
		// setup headers
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		connection.setRequestProperty("Authorization", auth);
		//connection.setRequestProperty("Authorization", "Basic MTI6YmUzYWY5NDY5MmRkMjllY2JkZTAzNGUxNjBjOTMyZDE=");
		// set body
		OutputStream os = new BufferedOutputStream(connection.getOutputStream());
		os.write("grant_type=client_credentials".getBytes("UTF-8"));
		os.flush();
		os.close();
		// connect
		connection.connect();
		// check response code
		int responseCode = connection.getResponseCode();
		Log.d("TEST", "" + responseCode);
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

	/**
	 * Call required service from the API. For the future implementation of the
	 * wrapper: catch the SocketTimeoutException and MalformedURLException when
	 * using this method in order to gain more control over the exception
	 * handling process
	 * 
	 * Caution: In case an IOException is NOT thrown, but the http response code
	 * is still pointing at an error, an adequate information is stored in the
	 * HttpResponse.
	 * 
	 * @param uri
	 *            Uri of the method. Used together with the base Uri of the API
	 *            to get the whole address.
	 * @param methodName
	 *            Name of the method.
	 * @param parameters
	 *            Parameters to switch with the uri fragments.
	 * @param body
	 *            Request body.
	 * @return Request response in form of a HttpResponse class.
	 * @throws JSONException
	 *             Thrown when an error occurs during JSON operations
	 * @throws IOException
	 *             Thrown when an error occurs during IO operations
	 */
	public HttpResponse callService(String uri, String methodName,
			Map<String, Object> parameters, String body)
			throws SocketTimeoutException, MalformedURLException, IOException,
			JSONException {
		// parse parameters
		//String targetUri = baseUrl + "/" + version + uri;
		String targetUri = baseUrl + version + uri;
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
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		if (methodName.equals("POST")) {
			connection.setDoOutput(true);
		} else {
			connection.setDoOutput(false);
		}
		connection.setDoInput(true);
		connection.setRequestMethod(methodName);
		connection.setConnectTimeout(Config.TIMEOUT);
		connection.setReadTimeout(Config.TIMEOUT);
		// set socket
		connection.setSSLSocketFactory(sslContext.getSocketFactory());
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
		Log.d("TEST", "" + responseCode);
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

	/**
	 * Checks the validity of the token.
	 * 
	 * @return
	 */
	public static boolean isTokenValid() {
		long currentTime = new Date().getTime();
		if (currentTime > httpToken.getUpdateTime()
				+ httpToken.getTokenTimeToLive() * 1000) {
			return false;
		} else
			return true;
	}

}
