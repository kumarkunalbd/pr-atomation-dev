/**
 * 
 */
package com.mobiliya.connmanagement;

import java.io.InputStream;

/**
 * @author kumar
 * This class hold response code and input stream from github
 * for requests 
 *
 */
public class GithubResponse{
	
	private int responseCode;
	private InputStream responseStream;
	
	
	
	public GithubResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GithubResponse(int reponseCode) {
		super();
		this.responseCode = reponseCode;
	}

	public GithubResponse(int reponseCode, InputStream responseStream) {
		super();
		this.responseCode = reponseCode;
		this.responseStream = responseStream;
	}
	
	
	
	
	
	
	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public InputStream getResponseStream() {
		return responseStream;
	}

	public void setResponseStream(InputStream responseStream) {
		this.responseStream = responseStream;
	}

	@Override
	public String toString() {
		return "GithubResponse [reponseCode=" + this.responseCode + ", responseStream=" + this.responseStream + ", toString()="
				+ super.toString() + "]";
	}
		
}
