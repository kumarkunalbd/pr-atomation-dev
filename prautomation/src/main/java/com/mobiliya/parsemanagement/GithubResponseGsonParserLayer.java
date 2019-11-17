package com.mobiliya.parsemanagement;



import com.mobiliya.connmanagement.ConnectionManager;
import com.mobiliya.connmanagement.GithubResponse;
import com.mobiliya.utility.BranchMergeStatus;

public class GithubResponseGsonParserLayer {
	
	public Object aResponse;
	
	public GithubResponseGsonParserLayer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public GithubResponseGsonParserLayer(Object aResponse) {
		super();
		this.aResponse = aResponse;
	}
	
	
	public Object getaResponse() {
		return aResponse;
	}



	public void setaResponse(Object aResponse) {
		this.aResponse = aResponse;
	}



	public static BranchMergeStatus parseResponseFromConnection(GithubResponse response, ConnectionManager connection) {
		BranchMergeStatus statusTobeReturned = null;
		switch (connection.getGuthubSegmentType()) {
		case MERGE_REQUEST:
			statusTobeReturned = parseResponse(response);
			break;

		default:
			break;
		}
		
		
		return statusTobeReturned;
	}
	
	public static BranchMergeStatus parseResponse(GithubResponse response) {
		BranchMergeStatus mergeStatus = GithubResponseGsonMergeParseService.parseTheResponse(response);
		return mergeStatus;
	}

}
