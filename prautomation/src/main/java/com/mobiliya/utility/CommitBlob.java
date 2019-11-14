/**
 * 
 */
package com.mobiliya.utility;

/**
 * @author kumar
 *
 */
public class CommitBlob {
	private String sha;
	private String url;
	private String html_url;
	
	
	
	public CommitBlob(String sha) {
		super();
		this.sha = sha;
	}

	public CommitBlob(String sha, String url, String html_url) {
		super();
		this.sha = sha;
		this.url = url;
		this.html_url = html_url;
	}
	
	public String getSha() {
		return sha;
	}
	public void setSha(String sha) {
		this.sha = sha;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	@Override
	public String toString() {
		return "CommitBlob [sha=" + sha + ", url=" + url + ", html_url=" + html_url + "]";
	}
	
}
