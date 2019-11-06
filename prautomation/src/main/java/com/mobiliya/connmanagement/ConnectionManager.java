package com.mobiliya.connmanagement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.mobiliya.utility.GithubConstants;

public class ConnectionManager {
	
	private String requestUrl;
	private RequestTypes requestType;
	private GithubSegmentType guthubSegmentType;
	private String bodyOfRequest;
	private HashMap<String, Object> parameters;
	
	
	
	public String getRequestUrl() {
		return requestUrl;
	}



	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}



	public RequestTypes getRequestType() {
		return requestType;
	}



	public void setRequestType(RequestTypes requestType) {
		this.requestType = requestType;
	}
	
	
	public GithubSegmentType getGuthubSegmentType() {
		return guthubSegmentType;
	}



	public void setGuthubSegmentType(GithubSegmentType guthubSegmentType) {
		this.guthubSegmentType = guthubSegmentType;
	}



	public String getBodyOfRequest() {
		return bodyOfRequest;
	}



	public void setBodyOfRequest(String bodyOfRequest) {
		this.bodyOfRequest = bodyOfRequest;
	}



	public HashMap<String, Object> getParameters() {
		return parameters;
	}



	public void setParameters(HashMap<String, Object> parameters) {
		this.parameters = parameters;
	}



	public GithubResponse createGETRequestGithub() throws IOException{
		if(this.requestType != null && this.requestUrl != null && this.guthubSegmentType != null) {
			URL urlForGetRequest = new URL(this.requestUrl);
			String readLine = null;
		    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		    conection.setRequestMethod(String.valueOf(this.requestType));
		    if(this.parameters != null && !this.parameters.isEmpty()) {
		    	String userId = (String) this.parameters.get(GithubConstants.GITHUB_SERIVICEACCOUNT_FIELDNAME);
		    	String password = (String) this.parameters.get(GithubConstants.GITHUB_SERIVICEACCOUNT_PASSWORD_FIELDNAME);
		    	if(userId != null && password != null) {
		    		 GithubResponse githubResponse = new GithubResponse();
		    		 conection.setRequestProperty(userId, password); // set userId its a sample here
		    		 int responseCode = conection.getResponseCode();
		    		 githubResponse.setReponseCode(responseCode);
		    		 githubResponse.setResponseStream(conection.getInputStream());
		    		 return githubResponse;
		    	}
		    }
		    
		}
		
		return null;	
	}
	
}
