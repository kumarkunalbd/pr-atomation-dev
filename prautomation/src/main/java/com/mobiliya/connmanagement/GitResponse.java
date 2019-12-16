package com.mobiliya.connmanagement;

import java.io.InputStream;

/**
 * @author kumar
 * This class represtent the Github Response.
 * for requests 
 *
 */

public class GitResponse {
	
	public int responseCode;
	public InputStream responseStream;
	public GitResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public GitResponse(int responseCode) {
		super();
		this.responseCode = responseCode;
	}
	

	public GitResponse(InputStream responseStream) {
		super();
		this.responseStream = responseStream;
	}


	public GitResponse(int responseCode, InputStream responseStream) {
		super();
		this.responseCode = responseCode;
		this.responseStream = responseStream;
	}


	@Override
	public String toString() {
		return "GitResponse [responseCode=" + responseCode + ", responseStream=" + responseStream + "]";
	}
	
	
}
