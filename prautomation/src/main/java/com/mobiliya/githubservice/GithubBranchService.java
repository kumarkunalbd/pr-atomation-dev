/**
 * 
 */
package com.mobiliya.githubservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryBranch;
import org.eclipse.egit.github.core.service.RepositoryService;

import com.mobiliya.connmanagement.ConnectionManager;
import com.mobiliya.connmanagement.GithubResponse;
import com.mobiliya.connmanagement.GithubSegmentType;
import com.mobiliya.connmanagement.RequestTypes;
import com.mobiliya.parsemanagement.GithubPR;
import com.mobiliya.parsemanagement.GithubResponseGsonParserLayer;
import com.mobiliya.utility.BranchMergeRequestBody;
import com.mobiliya.utility.BranchMergeStatus;
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
	        aConManager.setRequestType(RequestTypes.POST);
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
	
	public static BranchMergeStatus mergeBranchOnRepo(String repoName, BranchMergeRequestBody reuqestBody) {
		
		BranchMergeStatus mergeStatus=null;
		String gitHubMergeUrl = (GithubConstants.GITHUB_RESTAPI_INITIAL+GithubConstants.GITHUB_SERIVICEACCOUNT+ "/")
        		.concat(repoName).concat("/merges");
		ConnectionManager aConManager = new ConnectionManager();
        aConManager.setRequestUrl(gitHubMergeUrl);
        System.out.println("Request Type::"+ RequestTypes.POST);
        aConManager.setRequestType(String.valueOf(RequestTypes.POST));
        aConManager.setGuthubSegmentType(GithubSegmentType.MERGE_REQUEST);
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put(GithubConstants.GITHUB_SERIVICEACCOUNT_FIELDNAME, GithubConstants.GITHUB_SERIVICEACCOUNT);
        params.put(GithubConstants.GITHUB_SERIVICEACCOUNT_PASSWORD_FIELDNAME,GithubConstants.GITHUB_SERIVICEACCOUNT_PASSWORD);
        params.put(GithubConstants.GITHUB_KEY_REQUESTBODY, reuqestBody);
        aConManager.setParameters(params);
        String readLine = null;
        try {
        	GithubResponse aGuthubResponse = aConManager.createPostRequestGithub();
        	/*BufferedReader in = new BufferedReader(
        			new InputStreamReader(aGuthubResponse.getResponseStream()));
        	StringBuffer response = new StringBuffer();
        	while ((readLine = in .readLine()) != null) {
        		response.append(readLine);
        	} in .close();
        	// print result
        	System.out.println("JSON String Result " + response.toString());*/
        	mergeStatus = GithubResponseGsonParserLayer.parseResponseFromConnection(aGuthubResponse, aConManager);
        	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return mergeStatus;
	}
	
	public static String getShaForBranchName(String branchName, Repository repo, RepositoryService service) {
		String sha = null;
		
		System.out.println(repo.getMasterBranch());
		List<RepositoryBranch> listBranches;
		try {
			listBranches = service.getBranches(repo);
			RepositoryBranch masterBranch = null;
			for (RepositoryBranch aBranch : listBranches) {
				if(aBranch.getName().equals(repo.getMasterBranch())) {
					masterBranch = aBranch;
					break;
				}
			}
			if(masterBranch != null) {
				sha = masterBranch.getCommit().getSha();
				System.out.println(sha);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sha;
	}

}
