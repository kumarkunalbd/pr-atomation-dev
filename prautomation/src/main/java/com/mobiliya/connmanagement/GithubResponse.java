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
	
}
