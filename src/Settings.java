package net.sauertek.acusticus.settings;
import org.ini4j.Wini;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.lang.IllegalStateException;

public class Settings {
    public String VERSION;
    public String REDIS_HOST;
    
    final static Logger logger = Logger.getRootLogger();
    
    public Settings(){
	InputStream filein = null;
	try{
	    //filein = context.getResourceAsStream("/WEB-INF/config/config.ini");
	    filein = this.getClass().getClassLoader().getResourceAsStream("config.ini");
	    //if(filein == null){
	    //filein = new FileInputStream("WEB-INF/config/config.ini"); //for tests
	    //}
	    Wini ini = new Wini(filein);
	    REDIS_HOST = ini.get("acusticus", "ACUSTICUS_REDIS_HOST", String.class);
	    VERSION = ini.get("acusticus", "ACUSTICUS_VERSION", String.class);
	}
	catch(IOException e){
	    logger.warn("Failed to load configuration file: " + e.getMessage());
	    REDIS_HOST = "localhost";
	    VERSION="0.0 :)";
	}
	finally{
	    try{
		if(filein != null){
		    filein.close();
		}
	    }
	    catch(IOException e){
		logger.warn("Failure closing stream: " + e.getMessage());
	    }
	}
    }
 
    
}
