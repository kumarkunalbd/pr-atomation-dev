package com.mobiliya.parsemanagement;

import com.mobiliya.connmanagement.ConnectionManager;
import com.mobiliya.connmanagement.GithubResponse;

public class GithubResponseGsonParserLayer {
	
	public static Object parseResponseFromConnection(GithubResponse response, ConnectionManager connection) {
		Object statusTobeReturned = null;
		switch (connection.getGuthubSegmentType()) {
		case MERGE_REQUEST:
			
			break;

		default:
			break;
		}
		
		
		return statusTobeReturned;
	}

}
