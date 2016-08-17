package net.sauertek.acusticus.settings;
import org.ini4j.Wini;
import java.io.File;
import java.io.IOException;

import java.lang.IllegalStateException;

public class Settings {
    public String VERSION;
    public String REDIS_HOST;
    
    private static Settings singleton = new Settings();

    public static Settings getInstance(){
	return singleton;
    }
    
    private Settings(){
	try{
	    Wini ini = new Wini(new File("WEB-INF/config/config.ini"));
	    REDIS_HOST = ini.get("acusticus", "REDIS_HOST", String.class);
	    VERSION = ini.get("acusticus", "VERSION", String.class);
	}
	catch(IOException e){
	    System.out.println("Failed to load configuration file.");
	    REDIS_HOST="localhost";
	    VERSION="1.0";
	    }
    }
 
    
}
