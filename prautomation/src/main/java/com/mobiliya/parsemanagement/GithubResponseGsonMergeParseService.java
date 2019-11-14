/**
 * 
 */
package com.mobiliya.parsemanagement;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.ByteArrayInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobiliya.connmanagement.GithubResponse;
import com.mobiliya.utility.BranchMergeStatus;
import com.mobiliya.utility.CommitBlob;

/**
 * @author kumar
 *
 */
public class GithubResponseGsonMergeParseService {
	
	public static BranchMergeStatus parseTheResponse(GithubResponse response) {
		BranchMergeStatus status = null;
		
		File initialFile = new File("src/main/resources/Merging-JSON-Response.json");
	    try {
			InputStream targetStream = new FileInputStream(initialFile);
			switch (response.getReponseCode()) {
			case HttpURLConnection.HTTP_CREATED:
				status = parseCreatedResponseStream(targetStream);
				
				break;

			case HttpURLConnection.HTTP_NO_CONTENT:
				status = parseNOResponseStream();
				break;

			case HttpURLConnection.HTTP_CONFLICT:
				String string = "{\r\n" + 
						"  \"message\": \"Merge Conflict\"\r\n" + 
						"}";
				targetStream = new ByteArrayInputStream(string.getBytes(Charset.forName("UTF-8")));
				status = parseConflictResponseStream(targetStream);
				break;
				
			case HttpURLConnection.HTTP_NOT_FOUND:
				String string2 = "{\r\n" + 
						"  \"message\": \"Base/Head does not exist\"\r\n" + 
						"}";
				targetStream = new ByteArrayInputStream(string2.getBytes(Charset.forName("UTF-8")));
				status = parseMissingHeadBaseResponseStream(targetStream);
				break;
				
			default:
				break;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status; 
	}
	
	public static BranchMergeStatus parseCreatedResponseStream(InputStream responseStream) {
		BranchMergeStatus mergeStatus = new BranchMergeStatus();
		
		JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(responseStream));
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        
        JsonElement currentCommitSha = jsonObject.get("sha");                
        CommitBlob currentCommit = null;
        
        if(currentCommitSha != null ) {
        	mergeStatus.setMerged(true);
        	currentCommit = new CommitBlob(currentCommitSha.toString());
        	JsonElement currentCommitUrl = jsonObject.get("url");
            JsonElement currentCommitHtmlUrl = jsonObject.get("html_url");
            if(currentCommitUrl != null && currentCommitHtmlUrl != null) {
            	currentCommit.setUrl(currentCommitUrl.toString());
            	currentCommit.setHtml_url(currentCommitHtmlUrl.toString());
            }
            mergeStatus.setCurrentCommit(currentCommit);
        }
        
        JsonElement commitMessage = jsonObject.get("commit").getAsJsonObject().get("message");
        if(commitMessage != null) {
        	mergeStatus.setMessage(commitMessage.toString());
        }
        
        JsonArray jsonArr = (JsonArray) jsonObject.get("parents");
        Iterator<JsonElement> arrCommitBlobs = jsonArr.iterator();
        
        ArrayList<CommitBlob> arrParentCommitBlob = new ArrayList<CommitBlob>();
        
        while (arrCommitBlobs.hasNext()) {
			JsonObject aJsonObj = (JsonObject) arrCommitBlobs.next();
			JsonElement commitSha = aJsonObj.get("sha");
			
			if(commitSha != null) {
				CommitBlob commitBlobParent = new CommitBlob(commitSha.toString());
				JsonElement commitUrl = aJsonObj.get("url");
	            JsonElement commitHtmlUrl = aJsonObj.get("html_url");
	            if(commitUrl != null && commitHtmlUrl != null) {
	            	commitBlobParent.setUrl(commitUrl.toString());
	            	commitBlobParent.setHtml_url(commitHtmlUrl.toString());
	            }
	            
	            arrParentCommitBlob.add(commitBlobParent);  				
			}
			
		}
        
        mergeStatus.setArrParents(arrParentCommitBlob);
        
		return mergeStatus;
	}
	
	public static BranchMergeStatus parseConflictResponseStream(InputStream responseStream) {
		BranchMergeStatus mergeStatus = new BranchMergeStatus();
		mergeStatus.setMerged(false);
		
		JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(responseStream));
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        
        JsonElement messageElement = jsonObject.get("message");
        if(messageElement != null) {
        	mergeStatus.setMessage(messageElement.toString());
        }
		
		return mergeStatus;
	}
	
	public static BranchMergeStatus parseMissingHeadBaseResponseStream(InputStream responseStream) {
		BranchMergeStatus mergeStatus =  new BranchMergeStatus();
		mergeStatus.setMerged(false);

		JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(responseStream));
		JsonObject jsonObject = jsonElement.getAsJsonObject();

		JsonElement messageElement = jsonObject.get("message");
		if(messageElement != null) {
			mergeStatus.setMessage(messageElement.toString());
		}

		return mergeStatus;
	}
	
	public static BranchMergeStatus parseNOResponseStream() {
		BranchMergeStatus mergeStatus = new BranchMergeStatus();
		mergeStatus.setMerged(false);
		mergeStatus.setMessage("Base already contains the head, nothing to merge");
		
		return mergeStatus;
	}


}
