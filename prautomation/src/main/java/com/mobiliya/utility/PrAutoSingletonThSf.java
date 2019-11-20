package com.mobiliya.utility;

public class PrAutoSingletonThSf {
	
	private static PrAutoSingletonThSf prAutomtaionSingleInstance;
	private GithubProperties propertiesGithub;
	
	private PrAutoSingletonThSf() {
		super();
	}
	
	public GithubProperties getPropertiesGithub() {
		return propertiesGithub;
	}

	public void setPropertiesGithub(GithubProperties propertiesGithub) {
		this.propertiesGithub = propertiesGithub;
	}



	public static PrAutoSingletonThSf getInstance() {
		if(PrAutoSingletonThSf.prAutomtaionSingleInstance == null) {
			synchronized (PrAutoSingletonThSf.class) {
				if(PrAutoSingletonThSf.prAutomtaionSingleInstance == null) {
					prAutomtaionSingleInstance = new PrAutoSingletonThSf();
				}
			}
			
		}
		return prAutomtaionSingleInstance;
	}

	@Override
	public String toString() {
		return "PrAutoSingletonThSf [propertiesGithub=" + propertiesGithub + "]";
	}
	
}
