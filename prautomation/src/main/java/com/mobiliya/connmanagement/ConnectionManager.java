package com.mobiliya.connmanagement;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;

import com.google.gson.Gson;
import com.mobiliya.utility.BranchMergeRequestBody;
import com.mobiliya.utility.GithubConstants;

public class ConnectionManager {
	
	private String requestUrl;
	private String requestType;
	private GithubSegmentType guthubSegmentType;
	private String bodyOfRequest;
	private HashMap<String, Object> parameters;
	
	
	
	public String getRequestUrl() {
		return requestUrl;
	}



	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}



	public String getRequestType() {
		return requestType;
	}



	public void setRequestType(String string) {
		this.requestType = string;
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
	

	public GithubResponse createPostRequestGithub() throws IOException{
		if(this.requestType != null && this.requestUrl != null && this.guthubSegmentType != null) {
			System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
			URL urlForPostRequest = new URL(this.requestUrl);
			String readLine = null;
			
		    HttpURLConnection conection = (HttpURLConnection) urlForPostRequest.openConnection();
		    System.out.println("Request Method::" +String.valueOf(this.requestType));
		    conection.setRequestMethod(String.valueOf(this.requestType));
		    
		    if(this.parameters != null && !this.parameters.isEmpty()) {
		    	String userId = (String) this.parameters.get(GithubConstants.GITHUB_SERIVICEACCOUNT_FIELDNAME);
		    	String password = (String) this.parameters.get(GithubConstants.GITHUB_SERIVICEACCOUNT_PASSWORD_FIELDNAME);
		    	BranchMergeRequestBody requestBody = (BranchMergeRequestBody) this.parameters.get(GithubConstants.GITHUB_KEY_REQUESTBODY);
		    	String POST_PARAMS_BODY = new Gson().toJson(requestBody);
		    	System.out.println("POST_PARAMS_BODY::" +POST_PARAMS_BODY);
		    	System.out.println("ACTUAL REQUEST METHOD SET::" +conection.getRequestMethod());
		    	if(userId != null && password != null) {
		    		 GithubResponse githubResponse = new GithubResponse();
		    		 String baseAuthCred = userId+":"+password;
		    		 System.out.println("Base Auth CRed::" +baseAuthCred);
		    		 String encodingCred = Base64.getEncoder().encodeToString((baseAuthCred).getBytes("UTF-8"));
		    		 conection.setRequestProperty  ("Authorization", "Basic " + encodingCred);
		    		 System.out.println("Credentials::" +userId+","+password);
		    		 conection.setRequestProperty(GithubConstants.GITHUB_KEY_REQUESTPROPERTY_CONTENTTYPE, GithubConstants.GITHUB_VALUE_REQUESTPROPERTY_CONTENTTYPE_JSON_UTF8);
		    		 conection.setRequestProperty(GithubConstants.GITHUB_KEY_REQUESTPROPERTY_CONTENTTYPE_ACCEPT,GithubConstants.GITHUB_VALUE_REQUESTPROPERTY_CONTENTTYPE_JSON);
		    		 conection.setRequestProperty(GithubConstants.GITHUB_KEY_REQUESTPROPERTY_USERAGENT, GithubConstants.GITHUB_VALUE_REQUESTPROPERTY_USERAGENT_DEFAULT_AS_MOZILLA);
		    		 conection.setDoOutput(true);
		    		 OutputStream os = conection.getOutputStream();
		    		 /*byte[] input = POST_PARAMS_BODY.getBytes("utf-8");
		    		 os.write(input,0, input.length);*/
		    		 os.write(POST_PARAMS_BODY.getBytes());
		    		 os.flush();
		    		 os.close();
		    		 
		    		 System.out.println("Header Fields:"+conection.getHeaderFields());
		    		    
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
