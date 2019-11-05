package com.mobiliya.prautomation;

import com.mobiliya.connmanagement.GithubAuthentication;
import com.mobiliya.utility.GithubConstants;

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
    }
}
