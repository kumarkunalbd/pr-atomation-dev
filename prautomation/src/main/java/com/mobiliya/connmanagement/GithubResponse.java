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
public class GithubResponse {
	
	private int reponseCode;
	private InputStream responseStream;
	
	
	
	public GithubResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GithubResponse(int reponseCode) {
		super();
		this.reponseCode = reponseCode;
	}

	public GithubResponse(int reponseCode, InputStream responseStream) {
		super();
		this.reponseCode = reponseCode;
		this.responseStream = responseStream;
	}
	

	public int getReponseCode() {
		return reponseCode;
	}
	public void setReponseCode(int reponseCode) {
		this.reponseCode = reponseCode;
	}
	public InputStream getResponseStream() {
		return responseStream;
	}
	public void setResponseStream(InputStream responseStream) {
		this.responseStream = responseStream;
	}
	
	@Override
	public String toString() {
		return "GithubResponse [reponseCode=" + reponseCode + ", responseStream=" + responseStream + ", toString()="
				+ super.toString() + "]";
	}
		
}
