package pl.sointeractive.isaacloud.async;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import pl.sointeractive.isaacloud.connection.HttpMethod;
import pl.sointeractive.isaacloud.connection.HttpRequest;
import pl.sointeractive.isaacloud.connection.HttpResponse;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.util.Log;

/**
 * This class represents an asynchorized task for connecting to a required url.
 * Url parameters (address, headers, body, method type) are passed in the
 * HttpRequest class. The response of the connection is passed to the
 * HttpResponse class.
 * 
 * Use the class in the following way:
 * 
 * 1) Create an Activity that implements the HttpRequestAsyncTaskHandler
 * interface. The methods in this interface will determine what you will do with
 * the result of the task, after it is finished.
 * 
 * 2) Somewhere in the activity (usually in the onClick() method of a button
 * OnClickListener) create an instance of HttpRequest that specifies your
 * request. Setting the url, method type, header and body (in that order) is
 * recommended, for example:
 * 
 * HttpRequest request = new HttpRequest();
 * request.setUrl("http://oauth.isaacloud.com/token");
 * request.setMethod(HttpMethod.POST); request.addHeader("Content-Type",
 * "application/x-www-form-urlencoded"); request.addHeader("Authorization",
 * "Basic MTo1Yzc1ZjM2MjM0Yjk4ZmMwYmNmNTNkZTBkYWU3NTIyYw==");
 * request.setBody("grant_type=client_credentials");
 * 
 * 3) Create and execute the asynctask. Pass the current activity as an argument
 * for the constructor and the created HttpRequest as the argument for
 * execute(), for example:
 * 
 * new HttpRequestAsyncTask(this).execute(request);
 * 
 * 4) After the task is finished, a response is passed to the activity and
 * caught through the responseReceived(HttpResponse) method. Example
 * implementation of the method:
 * 
 * @Override public void responseReceived(HttpResponse response) {
 *           Toast.makeText(this, response.toString(),
 *           Toast.LENGTH_LONG).show(); }
 * 
 * @author Mateusz Renes
 * 
 */

public class HttpRequestAsyncTask extends
		AsyncTask<HttpRequest, Void, HttpResponse> {

	private static final String TAG = "HttpRequestAsyncTask";
	private Activity parent;
	private int responseCode;
	public HttpRequestAsyncTaskHandler responseTarget;

	public HttpRequestAsyncTask(Activity parent) {
		this.parent = parent;
		this.responseTarget = (HttpRequestAsyncTaskHandler) parent;
	}

	@Override
	protected HttpResponse doInBackground(HttpRequest... params) {
		Log.d(TAG, "HttpRequestAsyncTask started");
		HttpResponse result = new HttpResponse();
		HttpRequest request = params[0];
		result.setMethod(request.getMethod());
		try {
			// basic setup\
			Log.d(TAG, " create url");
			URL url = new URL(request.getUrl());
			Log.d(TAG, "open connection");
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			if (request.getMethod().equals(HttpMethod.POST)) {
				connection.setDoOutput(true);
			} else {
				connection.setDoOutput(false);
			}
			connection.setDoInput(true);
			connection.setRequestMethod(request.getMethod().toString());
			// header setup
			Log.d(TAG, "setup request properties");
			for (HashMap.Entry<String, String> entry : request
					.getHeadersHashMap().entrySet()) {
				connection.setRequestProperty(entry.getKey(), entry.getValue());
			}
			// connect
			Log.d(TAG, "connect");
			connection.connect();
			// body setup
			Log.d(TAG, "stream request body");
			if (request.getMethod().equals(HttpMethod.POST)) {
				OutputStream os = new BufferedOutputStream(
						connection.getOutputStream());
				os.write(request.getBodyBytes());
				os.flush();
				os.close();
			}
			// check response code
			responseCode = connection.getResponseCode();
			Log.d(TAG, "responseCode: " + responseCode);
			// get response
			Log.d(TAG, "create reader");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			Log.d(TAG, "read response");
			String response = reader.readLine();
			Log.d(TAG, "response string: " + response);
			if (response != null) {
				result.setHasJSON(true);
				// JSONObject json = new JSONObject(response);
				result.setResponseString(response);
			} else {
				result.setHasJSON(false);
				result.setResponseString("No JSON");
			}
			// disconnect
			Log.d(TAG, "disconnect");
			connection.disconnect();
		} catch (IOException e) {
			Log.e(TAG, "IOException");
			e.printStackTrace();
			result.setIsValid(false);
		} catch (NullPointerException e) {
			Log.e(TAG, "NullPointerException");
			e.printStackTrace();
			result.setIsValid(false);
		} catch (RuntimeException e) {
			Log.e(TAG, "RuntimeException");
			e.printStackTrace();
			result.setIsValid(false);
		}
		return result;
	}

	@Override
	protected void onPreExecute() {
		// lock screen orientation during async task
		int current_orientation = parent.getResources().getConfiguration().orientation;
		if (current_orientation == Configuration.ORIENTATION_LANDSCAPE) {
			parent.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			parent.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		// pre execute parent
		responseTarget.preExecute();
	}

	@Override
	protected void onPostExecute(HttpResponse result) {
		// unlock screen orientation after completing async task
		parent.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
		// send response to parent activity
		responseTarget.postExecute(result);
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

}
