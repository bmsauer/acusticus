package net.sauertek.acusticus.web.api;
 
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.UrlBinding;

import net.sauertek.acusticus.settings.Settings;
 
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

    //handlers
    @DefaultHandler
    public Resolution version(){
	settings = new Settings();
    	return new StreamingResolution("application/json", "{'name':'acusticus', 'version': '" + settings.VERSION + "'}");
    }

    public Resolution record(){
	String method = this.getContext().getRequest().getMethod();
	if(method.equals("GET")){
	    return new StreamingResolution("application/json", "{'name':'record', 'id': '" + Integer.toString(getId()) + "'}");
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
