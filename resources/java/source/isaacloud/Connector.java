package isaacloud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Connector {

	private String baseUrl;
	private String oauthUrl;
	private String version;
	private String clientId;
	private String clientSecret;
	private long currentTokenTime = new Date().getTime();
	private String currentToken = "";

	/**
	 * 
	 * @param baseUrl
	 * @param oauthUrl
	 * @param version
	 * @param config
	 */
	public Connector(String baseUrl, String oauthUrl, String version,
			Map<String, String> config) {
		this.baseUrl=baseUrl;
		this.oauthUrl=oauthUrl;
		this.setVersion(version);

		if (config.containsKey("clientId")) {
			this.clientId = config.get("clientId");

		} else {
			System.out.println("You did not set clientId");
		}

		if (config.containsKey("secret")) {
			this.clientSecret = config.get("secret");
		} else {
			System.out.println("You did not set secret");
		}

	}

	/**
	 * Get the token, if it's outdated then retrieve it from the server.
	 * 
	 * @return Autroization value.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private String getAuthentication() {
		// Check the time
		long currentTime = new Date().getTime();

		if (currentTime > this.currentTokenTime) {

			HttpPost method = new HttpPost(this.oauthUrl + "/token");
			method.addHeader(
					"Authorization",
					"Basic "
							+ new String(Base64.encodeBase64((this.clientId
									+ ":" + this.clientSecret).getBytes())));

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("grant_type",
					"client_credentials"));
			try {
				method.setEntity(new UrlEncodedFormEntity(urlParameters));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			String result = makeRequest(method);
			JSONObject obj = new JSONObject(result);

			this.currentToken = obj.get("access_token").toString();
		}
		return "Bearer " + currentToken;
	}

	/**
	 * Make a request and write the string
	 * @param method
	 * @return
	 */
	private String makeRequest(HttpUriRequest method) {

		CloseableHttpClient client = HttpClients.createDefault();
		StringBuffer result = new StringBuffer();
		try {

			CloseableHttpResponse response = client.execute(method);

			HttpEntity entity1 = response.getEntity();

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					entity1.getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			EntityUtils.consume(entity1);
			response.close();
		} catch (Exception e) {
			// log
		}

		return result.toString();
	}

	/**
	 * 
	 * @param method
	 * @param uri
	 * @param parameters
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public String invokeMethod(String methodName, String uri,
			Map<String, Object> parameters, String body)
			throws ClientProtocolException, IOException, Exception {

		String wholeUri = this.baseUrl + uri;

		String regex = "\\{[a-zA-Z0-9,]+\\}";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(wholeUri);
		while (matcher.find()) {
			String id = matcher.group();
			String tmp = id.replace("{", "");
			tmp = tmp.replace("}", "");
			if (parameters.containsKey(tmp)) {
				String rep = parameters.get(tmp).toString();
				wholeUri = wholeUri.replace(id, rep);
				parameters.remove(tmp);
			}
		}

		if (!parameters.isEmpty())
			wholeUri = wholeUri + "?";
		for (Entry<String, Object> entry : parameters.entrySet()) {
			wholeUri = wholeUri + entry.getKey() + "=" + entry.getValue();
		}

		HttpUriRequest method = null;

		if ("GET".equals(methodName)) {
			method = new HttpGet(wholeUri);
		} else if ("DELETE".equals(methodName)) {
			method = new HttpDelete(wholeUri);
		} else if ("PUT".equals(methodName)) {
			method = new HttpPut(wholeUri);
		} else if ("POST".equals(methodName)) {
			method = new HttpPost(wholeUri);
		} else if ("PATCH".equals(methodName)) {
			method = new HttpPatch(wholeUri);
		} else
			throw new Exception("Method not supported");

		method.addHeader("Authorization", this.getAuthentication());
		method.addHeader("Content-Type", "application/json charset=utf-8");

		if (body != null) {
			((HttpEntityEnclosingRequestBase) method)
					.setEntity(new StringEntity(body,
							ContentType.APPLICATION_JSON));
		}

		String result = this.makeRequest(method);

		return result;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	

}
