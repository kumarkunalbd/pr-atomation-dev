/**
 * 
 */
package com.mobiliya.utility;

/**
 * @author kumar
 *
 */
public class BranchMergeRequestBody {
	
	private String base;
	private String head;
	private String commit_message;
	
	public BranchMergeRequestBody(String base, String head, String commit_message) {
		this.base = base;
		this.head = head;
		this.commit_message = commit_message;
	}
	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getCommit_message() {
		return commit_message;
	}
	public void setCommit_message(String commit_message) {
		this.commit_message = commit_message;
	}
	
	// Creating toString 
    @Override
    public String toString() 
    { 
        return "Merge Request Body [base_branch_name="
            + this.base 
            + ", head_branch_name="
            + this.head 
            + ", commit_message="
            + this.commit_message + "]"; 
    } 
	
}
