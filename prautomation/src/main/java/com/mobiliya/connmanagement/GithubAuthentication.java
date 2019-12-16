package com.mobiliya.connmanagement;

import java.io.IOException;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;


/**
 * @author kumar
 * Will purpose the authentication mechanism.
 * for requests 
 *
 */

public class GithubAuthentication {
	private String serviceAccount;
	private String serviceAccountPassowrd;
	
	public GithubAuthentication(String serviceAccnt, String passwd) {
		// TODO Auto-generated constructor stub
		this.serviceAccount = serviceAccnt;
		this.serviceAccountPassowrd = passwd;
	}
	
	public String getServiceAccount() {
		return serviceAccount;
	}



	public void setServiceAccount(String serviceAccount) {
		this.serviceAccount = serviceAccount;
	}



	public String getServiceAccountPassowrd() {
		return serviceAccountPassowrd;
	}



	public void setServiceAccountPassowrd(String serviceAccountPassowrd) {
		this.serviceAccountPassowrd = serviceAccountPassowrd;
	}



	public void performAutnetication() {
		//Basic authentication
		GitHubClient client = new GitHubClient();
		client.setCredentials(this.serviceAccount,this.serviceAccountPassowrd);
	}
	
	public void listRepositories() {
		RepositoryService service = new RepositoryService();
		try {
			for (Repository repo : service.getRepositories(this.serviceAccount))
			  System.out.println(repo.getName() + " Watchers: " + repo.getWatchers());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
