package com.mobiliya.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GithubProperties {
	
	private String sourceFileName;
	private String repoOwnerName;
	private String repoName;
	private String serviceAccount;
	private String serviceAccountPasswrod;
	private String serviceAccountAuthToken;
	private String gitRestApiUrlPrefix;
	private String mainBranch;
	private String sourceBranchToMergeInPRBranches;
	private String filterWords_PullRequests;
	private String filterType;
	private String stateOfPRsForMerge;
	private boolean isBasePRMergingCyclic;
	private String prefixCommentOnPr;
	
	
	/*public GithubProperties() {
		super();
	}*/
	
	public GithubProperties(String sourceFileName) {
		super();
		this.sourceFileName = sourceFileName;
		this.setPropetiesValues();
	}
	

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getRepoOwnerName() {
		return repoOwnerName;
	}
	public void setRepoOwnerName(String repoOwnerName) {
		this.repoOwnerName = repoOwnerName;
	}
	public String getRepoName() {
		return repoName;
	}
	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}
	public String getServiceAccount() {
		return serviceAccount;
	}
	public void setServiceAccount(String serviceAccount) {
		this.serviceAccount = serviceAccount;
	}
	public String getServiceAccountPasswrod() {
		return serviceAccountPasswrod;
	}
	public void setServiceAccountPasswrod(String serviceAccountPasswrod) {
		this.serviceAccountPasswrod = serviceAccountPasswrod;
	}
	public String getServiceAccountAuthToken() {
		return serviceAccountAuthToken;
	}
	public void setServiceAccountAuthToken(String serviceAccountAuthToken) {
		this.serviceAccountAuthToken = serviceAccountAuthToken;
	}
	public String getGitRestApiUrlPrefix() {
		return gitRestApiUrlPrefix;
	}
	public void setGitRestApiUrlPrefix(String gitRestApiUrlPrefix) {
		this.gitRestApiUrlPrefix = gitRestApiUrlPrefix;
	}
	public String getMainBranch() {
		return mainBranch;
	}
	public void setMainBranch(String mainBranch) {
		this.mainBranch = mainBranch;
	}
	public String getSourceBranchToMergeInPRBranches() {
		return sourceBranchToMergeInPRBranches;
	}
	public void setSourceBranchToMergeInPRBranches(String sourceBranchToMergeInPRBranches) {
		this.sourceBranchToMergeInPRBranches = sourceBranchToMergeInPRBranches;
	}
	public String getFilterWords_PullRequests() {
		return filterWords_PullRequests;
	}
	public void setFilterWords_PullRequests(String filterWords_PullRequests) {
		this.filterWords_PullRequests = filterWords_PullRequests;
	}
	public String getFilterType() {
		return filterType;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	public String getStateOfPRsForMerge() {
		return stateOfPRsForMerge;
	}
	public void setStateOfPRsForMerge(String stateOfPRsForMerge) {
		this.stateOfPRsForMerge = stateOfPRsForMerge;
	}


	public boolean isBasePRMergingCyclic() {
		return isBasePRMergingCyclic;
	}
	public void setBasePRMergingCyclic(boolean isBasePRMergingCyclic) {
		this.isBasePRMergingCyclic = isBasePRMergingCyclic;
	}
	
	
	
	public String getPrefixCommentOnPr() {
		return prefixCommentOnPr;
	}


	public void setPrefixCommentOnPr(String prefixCommentOnPr) {
		this.prefixCommentOnPr = prefixCommentOnPr;
	}


	

	
	@Override
	public String toString() {
		return "GithubProperties [sourceFileName=" + sourceFileName + ", repoOwnerName=" + repoOwnerName + ", repoName="
				+ repoName + ", serviceAccount=" + serviceAccount + ", serviceAccountPasswrod=" + serviceAccountPasswrod
				+ ", serviceAccountAuthToken=" + serviceAccountAuthToken + ", gitRestApiUrlPrefix="
				+ gitRestApiUrlPrefix + ", mainBranch=" + mainBranch + ", sourceBranchToMergeInPRBranches="
				+ sourceBranchToMergeInPRBranches + ", filterWords_PullRequests=" + filterWords_PullRequests
				+ ", filterType=" + filterType + ", stateOfPRsForMerge=" + stateOfPRsForMerge
				+ ", isBasePRMergingCyclic=" + isBasePRMergingCyclic + ", prefixCommentOnPr=" + prefixCommentOnPr + "]";
	}


	public void setPropetiesValues() {
		InputStream inputStream = null;
		
		try {
			Properties aProp = new Properties();
			File initialFile = new File(this.sourceFileName);
			inputStream = new FileInputStream(initialFile);
			//inputStream = getClass().getClassLoader().getResourceAsStream(this.sourceFileName);
			if(inputStream != null) {
				aProp.load(inputStream);
				this.repoOwnerName = aProp.getProperty("repoOwnerName");
				this.repoName = aProp.getProperty("repoName");
				this.serviceAccount = aProp.getProperty("serviceAccount");
				this.serviceAccountPasswrod = aProp.getProperty("serviceAccountPasswrod");
				this.serviceAccountAuthToken = aProp.getProperty("serviceAccountAuthToken");
				this.mainBranch = aProp.getProperty("mainBranch");
				this.gitRestApiUrlPrefix = aProp.getProperty("gitRestApiUrlPrefix");
				this.sourceBranchToMergeInPRBranches = aProp.getProperty("sourceBranchToMergeInPRBranches");
				this.filterWords_PullRequests = aProp.getProperty("filterWords_PullRequests");
				this.filterType = aProp.getProperty("filterType");
				this.stateOfPRsForMerge = aProp.getProperty("stateOfPRsForMerge");
				//String isCyclic = aProp.getProperty("isBasePRMergingCyclic");
				this.isBasePRMergingCyclic = Boolean.valueOf(aProp.getProperty("isBasePRMergingCyclic"));
				this.prefixCommentOnPr = aProp.getProperty("prefixCommentOnPr");
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}
	
}
