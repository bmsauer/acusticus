package net.sauertek.acusticus.settings;

import java.lang.IllegalStateException;

public class Settings {
    public static final String VERSION = System.getenv("ACUSTICUS_VERSION");
    public static final String REDIS_HOST = System.getenv("ACUSTICUS_REDIS_HOST");
    
    private static Settings singleton = new Settings();

    public static Settings getInstance(){
	return singleton;
    }
    
    private Settings(){
	if(REDIS_HOST == null){
	    throw new IllegalStateException("ACUSTICUS_REDIS_HOST not set.");
	}
	if(VERSION == null){
	    throw new IllegalStateException("ACUSTICUS_VERSION not set.");
	}
	
    }
 
    
}
