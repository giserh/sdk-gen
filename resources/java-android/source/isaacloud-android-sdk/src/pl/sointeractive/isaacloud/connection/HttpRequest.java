package pl.sointeractive.isaacloud.connection;

import java.util.HashMap;

import org.json.JSONObject;

/**
 * Class represents the http request sent to the desired url.
 * @author Mateusz Renes
 *
 */
public class HttpRequest {

	private String url;
	private HttpMethod method;
	private HttpRequestHeaders headers;
	private HttpRequestBody body;
	
	public HttpRequest(){
		headers = new HttpRequestHeaders();
		body = new HttpRequestBody();
	}
	
	public HttpRequest(String url, HttpMethod method, HttpRequestHeaders headers, HttpRequestBody body){
		this.setUrl(url);
		this.setMethod(method);
		this.setHeaders(headers);
		this.setBody(body);
	}

	public HttpRequestHeaders getHeaders() {
		return headers;
	}
	
	public HashMap<String,String> getHeadersHashMap() {
		return headers.getHeaders();
	}

	public void setHeaders(HttpRequestHeaders headers) {
		this.headers = headers;
	}
	
	public void addHeader(String headerName, String headerValue){
		headers.addHeader(headerName, headerValue);
	}

	public HttpRequestBody getBody() {
		return body;
	}
	
	public byte[] getBodyBytes() {
		return body.getBodyBytes();
	}

	public void setBody(HttpRequestBody body) {
		this.body = body;
	}
	
	public void setBody(JSONObject json) {
		body.setBodyText(json);
	}
	
	public void setBody(String bodyText) {
		this.body = new HttpRequestBody(bodyText);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}
}
