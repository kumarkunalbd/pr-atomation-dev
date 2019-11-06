/**
 * 
 */
package com.mobiliya.githubservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import com.mobiliya.connmanagement.ConnectionManager;
import com.mobiliya.connmanagement.GithubResponse;
import com.mobiliya.connmanagement.GithubSegmentType;
import com.mobiliya.connmanagement.RequestTypes;
import com.mobiliya.parsemanagement.GithubPR;
import com.mobiliya.utility.GithubConstants;

/**
 * @author kumar
 *
 */
public class GithubBranchService {
	
	
	/*public static List<GithubPR> mergeBranches(String baseBranch, String headBranch){
		
		 String githubPRsAllUrl = (GithubConstants.GITHUB_RESTAPI_INITIAL+GithubConstants.GITHUB_SERIVICEACCOUNT+ "/")
	        		.concat(repository).concat("/pulls?state=all");
	        ConnectionManager aConManager = new ConnectionManager();
	        aConManager.setRequestUrl(githubPRsAllUrl);
	        aConManager.setRequestType(RequestTypes.GET);
	        aConManager.setGuthubSegmentType(GithubSegmentType.GET_PULL_REQUESTS);
	        HashMap<String, Object> params = new HashMap<String, Object>();
	        params.put(GithubConstants.GITHUB_SERIVICEACCOUNT_FIELDNAME, GithubConstants.GITHUB_SERIVICEACCOUNT);
	        params.put(GithubConstants.GITHUB_SERIVICEACCOUNT_PASSWORD_FIELDNAME,GithubConstants.GITHUB_SERIVICEACCOUNT_PASSWORD);
	        aConManager.setParameters(params);
	        String readLine = null;
	        try {
				GithubResponse aGuthubResponse = aConManager.createGETRequestGithub();
				 BufferedReader in = new BufferedReader(
				            new InputStreamReader(aGuthubResponse.getResponseStream()));
				        StringBuffer response = new StringBuffer();
				        while ((readLine = in .readLine()) != null) {
				            response.append(readLine);
				        } in .close();
				        // print result
				        System.out.println("JSON String Result " + response.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		return null;
	}*/

}
