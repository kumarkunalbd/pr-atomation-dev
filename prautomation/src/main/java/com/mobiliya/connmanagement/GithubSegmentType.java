/**
 * 
 */
package com.mobiliya.connmanagement;

/**
 * @author kumar
 * This enum provide information about the which segment of github 
 * is beung sent request
 *
 */
public enum GithubSegmentType {
	GET_PULL_REQUESTS,
	MERGE_PULL_REQUESTS,
	CHECK_MERGIBILITY_PULLREQUEST,
	MERGE_REQUEST,
	ISSUE_COMMENT

}
