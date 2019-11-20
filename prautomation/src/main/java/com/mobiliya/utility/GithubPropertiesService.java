/**
 * 
 */
package com.mobiliya.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;;

/**
 * @author kumar
 *
 */
public class GithubPropertiesService {
		
	@SuppressWarnings("unused")
	public GithubProperties setGithubPropertiesFromFile(String sourceFile) {
		GithubProperties gitHubProperties = null;
		
		try {
			gitHubProperties = new GithubProperties(sourceFile);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		
		return gitHubProperties;
	}
	

}
