package pl.sointeractive.isaacloud.connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpResponse {
	
	private boolean isValid;
	private String responseString;
	private HttpMethod method;
	private boolean hasJSON;
	
	public HttpResponse(){
		this.setIsValid(true);
	}
	
	public HttpResponse(HttpMethod method, boolean hasJSON){
		this.setMethod(method);
		this.setHasJSON(hasJSON);
	}

	//public JSONObject getJson() {
	//	return json;
	//}

	//public void setJson(JSONObject json) {
	//	this.json = json;
	//}

	public boolean hasJSON() {
		return hasJSON;
	}

	public void setHasJSON(boolean hasJSON) {
		this.hasJSON = hasJSON;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}
	
	@Override
	public String toString(){
		String s = "";
		s += "HttpResponse" + "\n";
		s += "Method: " + method.toString() + "\n";
		s += "JSON available: " + hasJSON;
		if(hasJSON){
			s += "\n" + "JSON: " + responseString;
		}
		return s;
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
	
	public JSONObject getJSONObject(){
		JSONObject json = null;
		try {
			json = new JSONObject(responseString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public JSONArray getJSONArray(){
		JSONArray json = null;
		try {
			json = new JSONArray(responseString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}
}
