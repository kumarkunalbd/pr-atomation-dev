/**
 * 
 */
package com.mobiliya.parsemanagement;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobiliya.connmanagement.GithubResponse;
import com.mobiliya.utility.CommitBlob;
import com.mobiliya.utility.GithubComment;

/**
 * @author kumar
 *
 */
public class GithubResponseGsonIssueCommentParseService {
	
	public static GithubComment parseResponse(GithubResponse response) {
		GithubComment aComment = null;
		
		try {
			
			switch (response.getResponseCode()) {
			case HttpURLConnection.HTTP_CREATED:
				aComment = parseCreatedResponse(response.getResponseStream());
				break;
				
			case HttpURLConnection.HTTP_NOT_FOUND:
				String string2 = "{\r\n" + 
						"  \"message\": \"Base/Head does not exist\"\r\n" + 
						"}";
				//targetStream = new ByteArrayInputStream(string2.getBytes(Charset.forName("UTF-8")));
				aComment = parseMissingInformationResponseStream(response.getResponseStream());
				break;

			default:
				break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return aComment;
	}
	
	private static GithubComment parseMissingInformationResponseStream(InputStream responseStream) {
		// TODO Auto-generated method stub
		GithubComment aComment = new GithubComment();
		JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(responseStream));
		JsonObject jsonObject = jsonElement.getAsJsonObject();

		JsonElement messageElement = jsonObject.get("message");
		if(messageElement != null) {
			aComment.setBody(messageElement.toString());
		}
		return null;
	}

	public static GithubComment parseCreatedResponse(InputStream responseStream) {
		GithubComment aComment = new GithubComment();
		
		JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(responseStream));
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        
        if(jsonObject != null) {
        	JsonElement returnedBody = jsonObject.get("body");
        	if(returnedBody != null) {
        		aComment.setBody(returnedBody.toString());
        	}
        	
        	JsonElement returnedId = jsonObject.get("id");
        	if(returnedId != null) {
        		aComment.setId(returnedId.toString());
        	}
        	
        	JsonElement returnedNodeId = jsonObject.get("node_id");
        	if(returnedNodeId != null) {
        		aComment.setNode_id(returnedNodeId.toString());
        	}
        	
        	JsonElement returnedCreatedAt = jsonObject.get("created_at");
        	if(returnedCreatedAt != null) {
        		aComment.setCreated_at(returnedCreatedAt.toString());
        	}
        	
        	JsonElement returnedUpdatedAt = jsonObject.get("updated_at");
        	if(returnedUpdatedAt != null) {
        		aComment.setUpdated_at(returnedUpdatedAt.toString());
        	}
        	
        	JsonElement returnedUrl = jsonObject.get("url");
        	if(returnedUrl != null) {
        		aComment.setUrl(returnedUrl.toString());
        	}
        	
        	JsonElement returnedHtlUrl = jsonObject.get("html_url");
        	if(returnedHtlUrl != null) {
        		aComment.setHtml_url(returnedHtlUrl.toString());
        	}
        	
        }
		
		return aComment;
		
	}

}
