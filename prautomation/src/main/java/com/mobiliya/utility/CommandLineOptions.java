package com.mobiliya.utility;

import java.util.ArrayList;

public class CommandLineOptions {
	
	private ArrayList<String> listArgs;
	
	public CommandLineOptions(String[] args) {
		listArgs = new ArrayList<String>();
		
		for(String anArg : args) {
			listArgs.add(anArg);
		}
	}

	public ArrayList<String> getListArgs() {
		return listArgs;
	}

	public void setListArgs(ArrayList<String> listArgs) {
		this.listArgs = listArgs;
	}
	
	public boolean hasOption(String anOption) {		
		boolean hasValue = false;
		
		for(String anArg : this.listArgs) {
			if(anArg.equalsIgnoreCase(anOption)) {
				hasValue = true;
				break;
			}
		}
		
		return hasValue;
	}
	
	public String valueOfOption(String anOption) {
		String optionValue = null;
		
		int counter=0;
		for(String anArg : this.listArgs) {
			if(anArg.equalsIgnoreCase(anOption)) {
				optionValue = this.listArgs.get(counter+1);
				break;
			}
			counter = counter+1;
		}
		
		return optionValue;
	}

}
