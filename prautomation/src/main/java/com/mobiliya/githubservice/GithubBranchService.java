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
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.service.CommitService;
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
import com.mobiliya.utility.PrAutoSingletonThSf;

/**
 * @author kumar
 * This class is being used to get Branch information from Git REST APIs.
 *
 */
public class GithubBranchService {
	
	private String gitRestApiUrlPrefix;
	private String serviceAccountName;
	private String serviceAccountPassword;
	private String repoOwnerName;
	
	
	
	
	public GithubBranchService(String gitRestApiUrlPrefix, String serviceAccountName, String serviceAccountPassword, String repoOwner) {
		super();
		this.gitRestApiUrlPrefix = gitRestApiUrlPrefix;
		this.serviceAccountName = serviceAccountName;
		this.serviceAccountPassword = serviceAccountPassword;
		this.repoOwnerName = repoOwner;
	}
	
	public String getGitRestApiUrlPrefix() {
		return gitRestApiUrlPrefix;
	}


	public void setGitRestApiUrlPrefix(String gitRestApiUrlPrefix) {
		this.gitRestApiUrlPrefix = gitRestApiUrlPrefix;
	}


	public String getServiceAccountName() {
		return serviceAccountName;
	}


	public void setServiceAccountName(String serviceAccountName) {
		this.serviceAccountName = serviceAccountName;
	}


	public String getServiceAccountPassword() {
		return serviceAccountPassword;
	}


	public void setServiceAccountPassword(String serviceAccountPassword) {
		this.serviceAccountPassword = serviceAccountPassword;
	}
	
	public String getRepoOwnerName() {
		return repoOwnerName;
	}

	public void setRepoOwnerName(String repoOwnerName) {
		this.repoOwnerName = repoOwnerName;
	}
	

	
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
	

	/**
	 * @author kumar
	 * This method will merge Branches
	 * @Params
	 * repoName : Repository Name
	 * reuqestBody: Body of POST request
	 * 
	 */
	
	public BranchMergeStatus mergeBranchOnRepo(String repoName, BranchMergeRequestBody reuqestBody) {
		
		BranchMergeStatus mergeStatus=null;
		
		if(this.gitRestApiUrlPrefix != null && this.serviceAccountName!= null 
				&& this.serviceAccountPassword != null && this.repoOwnerName != null) {
			
			String gitHubMergeUrl = (this.gitRestApiUrlPrefix+this.repoOwnerName+ "/")
	        		.concat(repoName).concat("/merges");
			ConnectionManager aConManager = new ConnectionManager();
	        aConManager.setRequestUrl(gitHubMergeUrl);
	        System.out.println("Request Type::"+ RequestTypes.POST);
	        aConManager.setRequestType(String.valueOf(RequestTypes.POST));
	        aConManager.setGuthubSegmentType(GithubSegmentType.MERGE_REQUEST);
	        HashMap<String, Object> params = new HashMap<String, Object>();
	        params.put(GithubConstants.GITHUB_SERIVICEACCOUNT_FIELDNAME, this.serviceAccountName);
	        params.put(GithubConstants.GITHUB_SERIVICEACCOUNT_PASSWORD_FIELDNAME,this.serviceAccountPassword);
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
			
		}
		
		return mergeStatus;
	}
	
	/**
	 * @author kumar
	 * This method will get latest commit id on the specific branch
	 * @Params
	 * repo : Repository Object
	 * branchName: name of Branch for latest commit id
	 * service: Repository Service
	 * 
	 */
	
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
	
	/**
	 * @author kumar
	 * This method will get commit message for particular commit ID
	 * @Params
	 * commitSha : Commit Id whose message need to be taken
	 * repo: Repository Object
	 * 
	 */
	
	public static String getCommitMessageForCommitSha(String commitSha, Repository repo) {
		String commitMessage = null;
		//RepositoryService service = new RepositoryService();
		CommitService commitService = new CommitService();
		try {
			RepositoryCommit aCommit = commitService.getCommit(repo, commitSha);
			commitMessage = aCommit.getCommit().getMessage();
			System.out.println(commitMessage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return commitMessage;
	}

}
