package net.sauertek.acusticus.web.api;
 
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.UrlBinding;

import net.sauertek.acusticus.settings.Settings;
import net.sauertek.acusticus.record.Record;
import net.sauertek.acusticus.record.RecordDaoRedis;
import net.sauertek.acusticus.exceptions.RecordNotFoundException;
 
/**
* Acustic.us API
* @author Brian Sauer
*/

@UrlBinding("/api/{$event}/{id=-1}")
public class AcusticusAPIActionBean implements ActionBean {
    //context for request object
    private ActionBeanContext context;
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    //id param
    private int id;
    public int getId(){ return id; }
    public void setId(int i){ this.id = i; }
    //misc
    private Settings settings;
    private RecordDaoRedis recordDB;

    //handlers
    @DefaultHandler
    public Resolution version(){
	settings = new Settings();
    	return new StreamingResolution("application/json", "{'name':'acusticus', 'version': '" + settings.VERSION + "'}");
    }

    public Resolution record(){
	String method = this.getContext().getRequest().getMethod();
	if(method.equals("GET")){
	    int id = getId();
	    if(id > 0){
		recordDB = new RecordDaoRedis();
		Record record;
		try{
		    record = recordDB.getRecord(id);
		}
		catch (RecordNotFoundException e){
		    return new StreamingResolution("text", "not found");
		}
		return new StreamingResolution("application/json", "{'album':'"+record.album+"', 'artist': '" +record.artist + "'}");
	    }
	    else{
		return new StreamingResolution("text","not found");
	    }
	}
	else if(method.equals("DELETE")){
	    return new StreamingResolution("text", "delete");
	}
	else{
	    this.getContext().getResponse().setStatus(405);
	    return new StreamingResolution("application/json", "{'error':'Method not allowed.'}");
	}
    }
}
