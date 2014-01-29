package pl.sointeractive.isaacloud.async;

import pl.sointeractive.isaacloud.connection.HttpResponse;

/**
 * This interface must be implemented in the activity in which you want to
 * receive the HttpResponse from the HttpRequesAsyncTask.
 * 
 * @author Mateusz Renes
 * 
 */
public interface HttpRequestAsyncTaskHandler {
	/**
	 * Decide what you want to to before the request is sent. Usually a progress
	 * dialog implementation will take place here.
	 */
	void preExecute();

	/**
	 * Decide what you want to do with the response from the request. If a
	 * progress dialog was created in the preExecute() method, remember to
	 * dismiss it here.
	 * 
	 * @param response
	 *            Response from the http request.
	 */
	void postExecute(HttpResponse response);
}
