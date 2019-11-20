package com.mobiliya.prautomation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;


import org.eclipse.egit.github.core.MergeStatus;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryBranch;
import org.eclipse.egit.github.core.RepositoryIssue;
import org.eclipse.egit.github.core.service.RepositoryService;

import com.mobiliya.connmanagement.ConnectionManager;
import com.mobiliya.connmanagement.GithubAuthentication;
import com.mobiliya.connmanagement.GithubResponse;
import com.mobiliya.connmanagement.GithubSegmentType;
import com.mobiliya.connmanagement.RequestTypes;
import com.mobiliya.githubservice.GithubBranchService;
import com.mobiliya.githubservice.GithubPRService;
import com.mobiliya.parsemanagement.GithubPR;
import com.mobiliya.parsemanagement.GithubResponseGsonMergeParseService;
import com.mobiliya.utility.BranchMergeRequestBody;
import com.mobiliya.utility.BranchMergeStatus;
import com.mobiliya.utility.CommandLineOptions;
import com.mobiliya.utility.GithubConstants;
import com.mobiliya.utility.GithubProperties;
import com.mobiliya.utility.GithubPropertiesService;
import com.mobiliya.utility.PrAutoSingletonThSf;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello PR Automation!" );
        
        GithubAuthentication gitHubAuthentication = new GithubAuthentication(GithubConstants.GITHUB_SERIVICEACCOUNT,
        		GithubConstants.GITHUB_SERIVICEACCOUNT_PASSWORD);
        gitHubAuthentication.performAutnetication();
        gitHubAuthentication.listRepositories();
        
        //List<GithubPR> prList = GithubPRService.getPullRequests(GithubConstants.GITHUB_Automation_Repository);
        
        //List<PullRequest> prListStateAll = GithubPRService.getPullRequestsRepoNameState(GithubConstants.GITHUB_Automation_Repository,"open");
        
        mergeGithubBaseBranchPRs(args);
       
       
      
        
        
        //A trail merge on behalf of person for a PR
        /*PullRequest trialPR = null;
        if(!prListAll.isEmpty()) {
        	trialPR = prListAll.get(1);
        	
        	MergeStatus mergeStatus =  GithubPRService.mergePullRequest(trialPR,"This is the merge from Java Utility", GithubConstants.GITHUB_Automation_Repository);
        	if(mergeStatus != null) {
        		System.out.println("is mergerd::"+ mergeStatus.isMerged());
    			System.out.println("Sha Id::" + mergeStatus.getSha());
    			System.out.println("Message::" + mergeStatus.getMessage());
        	}
        	
        }*/
        
        
      //Merge 2 branches
        /*for (PullRequest aPR : prListAll) {
        	String prHeadSha = aPR.getHead().getRef();
        	String commitMessage  = "A trial merge from master to"+prHeadSha;
        	BranchMergeRequestBody branchMergeBody = new BranchMergeRequestBody(prHeadSha, "b9fa17dd6ac6f3369dc74bcccdd8cd7a18ea25e0", commitMessage);
            BranchMergeStatus mergeStatus = GithubBranchService.mergeBranchOnRepo(GithubConstants.GITHUB_Automation_Repository, branchMergeBody);
        }*/
        /*BranchMergeRequestBody branchMergeBody = new BranchMergeRequestBody("ftr9kmrkunal", "master", "Trial merger from master to ftr9kmrkunal");
        BranchMergeStatus mergeStatus = GithubBranchService.mergeBranchOnRepo(GithubConstants.GITHUB_Automation_Repository, branchMergeBody);*/
        
      // MergeResponse Test
        /*BranchMergeStatus statusMerge = GithubResponseGsonMergeParseService.parseTheResponse(new GithubResponse(201));
        System.out.print(statusMerge.toString());*/
        
    }
    
    public static void mergeGithubBaseBranchPRs(String[] args) {

    	CommandLineOptions cmdOptions = new CommandLineOptions(args);
    	String attributeFilePath = cmdOptions.valueOfOption("AttributeFile");
    	System.out.println(attributeFilePath);
    	
    	PrAutoSingletonThSf prSingletonObj = PrAutoSingletonThSf.getInstance();
    	
    	GithubPropertiesService githubPropertiesService  = new GithubPropertiesService();
    	GithubProperties gitHubProps = githubPropertiesService.setGithubPropertiesFromFile(attributeFilePath);
    	System.out.println(gitHubProps);
    	prSingletonObj.setPropertiesGithub(gitHubProps);
    			
    	GithubPRService aGitHubPrService = new GithubPRService();
    	aGitHubPrService.setPrRepositoryOwnerName(PrAutoSingletonThSf.getInstance().getPropertiesGithub().getServiceAccount());
    	aGitHubPrService.setPrRepositoryName(PrAutoSingletonThSf.getInstance().getPropertiesGithub().getRepoName());
    	aGitHubPrService.setStatusForPRs(PrAutoSingletonThSf.getInstance().getPropertiesGithub().getStateOfPRsForMerge());
    	aGitHubPrService.mergeDefaultBranchShaToPRs();

    }
    

}
