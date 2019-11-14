/**
 * 
 */
package com.mobiliya.githubservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import org.eclipse.egit.github.core.MergeStatus;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.PullRequestService;

import com.mobiliya.connmanagement.ConnectionManager;
import com.mobiliya.connmanagement.GithubResponse;
import com.mobiliya.connmanagement.GithubSegmentType;
import com.mobiliya.connmanagement.RequestTypes;
import com.mobiliya.parsemanagement.GithubPR;
import com.mobiliya.utility.GithubConstants;


/**
 * @author kumar
 * This is Github Pull Request Service provider class.
 * 
 */
public class GithubPRService {
	
	/**
	 * @author kumar
	 * This will return list of all pull requests based on state requested.
	 * 
	 */
	public static List<GithubPR> getPullRequests(String repository){
		
		 String githubPRsAllUrl = (GithubConstants.GITHUB_RESTAPI_INITIAL+GithubConstants.GITHUB_SERIVICEACCOUNT+ "/")
	        		.concat(repository).concat("/pulls?state=all");
	        ConnectionManager aConManager = new ConnectionManager();
	        aConManager.setRequestUrl(githubPRsAllUrl);
	        aConManager.setRequestType(String.valueOf(RequestTypes.GET));
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
	}
	
	/**
	 * @author kumar
	 * This will return list of all pull requests based on state requested.
	 * 
	 */
	
	
	public static List<PullRequest> getPullRequestsRepoNameState(String repoName, String state){
		List<PullRequest> listPRs = null;
		PullRequestService service = new PullRequestService();
		service.getClient().setCredentials(GithubConstants.GITHUB_SERIVICEACCOUNT, GithubConstants.GITHUB_SERIVICEACCOUNT_PASSWORD);
		RepositoryId repo = new RepositoryId(GithubConstants.GITHUB_SERIVICEACCOUNT, GithubConstants.GITHUB_Automation_Repository);
		try {
			listPRs = service.getPullRequests(repo, state);
			listPRs.forEach(aPullRequest -> {
				System.out.println(aPullRequest.getUrl());
				System.out.println(aPullRequest.getNumber());
			});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listPRs;
	}
	
	/**
	 * @author kumar
	 * This will merge the pull request. 
	 * Thsi is being done on behalf of Person merge(a Trial automation which will be finally removed)
	 * 
	 */
	
	public static MergeStatus mergePullRequest(PullRequest aPullRequest, String commitMessage, String repoName) {
		System.out.println("PR being mergerd::"+ aPullRequest.getUrl());
		
		PullRequestService service = new PullRequestService();
		service.getClient().setCredentials(GithubConstants.GITHUB_SERIVICEACCOUNT, GithubConstants.GITHUB_SERIVICEACCOUNT_PASSWORD);
		RepositoryId repo = new RepositoryId(GithubConstants.GITHUB_SERIVICEACCOUNT, repoName);
		try {
			
			MergeStatus returnedStatus = service.merge(repo, aPullRequest.getNumber(), commitMessage);
			return returnedStatus;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*if(aPullRequest.isMergeable()) {
			PullRequestService service = new PullRequestService();
			service.getClient().setCredentials(GithubConstants.GITHUB_SERIVICEACCOUNT, GithubConstants.GITHUB_SERIVICEACCOUNT_PASSWORD);
			RepositoryId repo = new RepositoryId(GithubConstants.GITHUB_SERIVICEACCOUNT, repoName);
			try {
				
				MergeStatus returnedStatus = service.merge(repo, aPullRequest.getNumber(), commitMessage);
				return returnedStatus;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("PR is not mergable");
		}*/
		return null;
		
	}


	
}
