/**
 * 
 */
package com.mobiliya.utility;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author kumar
 * merge status model class.
 *
 */
public class BranchMergeStatus {
	
	private boolean merged;
	private CommitBlob currentCommit;
	private String message;
	private ArrayList<CommitBlob> arrParentCommits;
	private MergeBranchStatusType nonMergeType;
	
	
	public BranchMergeStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BranchMergeStatus(boolean merged, CommitBlob currentCommit, String message, ArrayList<CommitBlob> arrParents) {
		super();
		this.merged = merged;
		this.currentCommit = currentCommit;
		this.message = message;
		this.arrParentCommits = arrParents;
	}

	public boolean isMerged() {
		return merged;
	}

	public void setMerged(boolean merged) {
		this.merged = merged;
	}

	public CommitBlob getCurrentCommit() {
		return currentCommit;
	}

	public void setCurrentCommit(CommitBlob currentCommit) {
		this.currentCommit = currentCommit;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<CommitBlob> getArrParents() {
		return this.arrParentCommits;
	}

	public void setArrParents(ArrayList<CommitBlob> arrParents) {
		this.arrParentCommits = arrParents;
	}
	
	

	public MergeBranchStatusType getNonMergeType() {
		return nonMergeType;
	}

	public void setNonMergeType(MergeBranchStatusType nonMergeType) {
		this.nonMergeType = nonMergeType;
	}

	@Override
	public String toString() {
		if(currentCommit != null) {
			return "BranchMergeStatus [merged=" + merged + ", currentCommit=" + currentCommit.toString() + ", message=" + message
					+ ", arrParents=" + this.arrParentCommits.toString() + "]";
		}else {
			return "BranchMergeStatus [merged=" + merged + ", message=" + message
					+ "]";
		}
		
	} 
	

}
