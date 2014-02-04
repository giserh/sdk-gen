package pl.sointeractive.isaacloud.exceptions;

public class InvalidConfigException extends Exception{

	private static final long serialVersionUID = 1L;
	
	String missingConfig;
	public InvalidConfigException(String missingConfig){
		this.missingConfig = missingConfig;
	}

	public String getMissingConfig(){
		return missingConfig;
	}
}
