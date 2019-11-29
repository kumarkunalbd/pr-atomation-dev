/**
 * 
 */
package com.mobiliya.githubservice;

import java.io.IOException;
import java.util.HashMap;

import com.mobiliya.connmanagement.ConnectionManager;
import com.mobiliya.connmanagement.GithubResponse;
import com.mobiliya.connmanagement.GithubSegmentType;
import com.mobiliya.connmanagement.RequestTypes;
import com.mobiliya.parsemanagement.GithubResponseGsonMergeParseService;
import com.mobiliya.parsemanagement.GithubResponseGsonParserLayer;
import com.mobiliya.utility.CommentRequestBody;
import com.mobiliya.utility.GithubComment;
import com.mobiliya.utility.GithubConstants;

/**
 * @author kumar
 *
 */
public class IssueCommentService {
	
	private String serviceAccountName;
	private String serviceAccountPassword;
	private String repoOwnerName;
	
	
	public IssueCommentService() {
		super();
	}
	
	
	
	/**
	 * @param serviceAccountName
	 * @param serviceAccountPassword
	 * @param repoOwnerName
	 */
	public IssueCommentService(String serviceAccountName, String serviceAccountPassword, String repoOwnerName) {
		super();
		this.serviceAccountName = serviceAccountName;
		this.serviceAccountPassword = serviceAccountPassword;
		this.repoOwnerName = repoOwnerName;
	}



	public GithubComment createComment(String urlComment, CommentRequestBody commentBody) {
		GithubComment gitComment=null;
		
		if(urlComment != null && commentBody != null) {
			ConnectionManager aConManager = new ConnectionManager();
	        aConManager.setRequestUrl(urlComment);
	        aConManager.setRequestType(String.valueOf(RequestTypes.POST));
	        aConManager.setGuthubSegmentType(GithubSegmentType.ISSUE_COMMENT);
	        HashMap<String, Object> params = new HashMap<String, Object>();
	        params.put(GithubConstants.GITHUB_SERIVICEACCOUNT_FIELDNAME, this.serviceAccountName);
	        params.put(GithubConstants.GITHUB_SERIVICEACCOUNT_PASSWORD_FIELDNAME,this.serviceAccountPassword);
	        params.put(GithubConstants.GITHUB_KEY_REQUESTBODY, commentBody);
	        aConManager.setParameters(params);
	        
	        try {
				GithubResponse aGithubResponse = aConManager.createPostRequestGithub();
				gitComment = GithubResponseGsonParserLayer.parseResponseIssueComment(aGithubResponse, aConManager);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return gitComment;
	}

}
