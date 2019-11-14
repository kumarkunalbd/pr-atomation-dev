/**
 * 
 */
package com.mobiliya.connmanagement;

import java.security.PublicKey;

/**
 * @author kumar
 * This class is for detecting what is the the
 * request types.
 */
public enum RequestTypes {
	GET{
		public String toString() {
			return "GET";
		}
	},
	POST{
		public String toString() {
			return "POST";
		}

	},
	PUT{
		public String toString() {
			return "PUT";
		}
	};

}
