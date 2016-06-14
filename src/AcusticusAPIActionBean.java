package net.sauertek.acusticus.web.api;
 
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.UrlBinding;
 
/**
* Acustic.us API
* @author Brian Sauer
*/

@UrlBinding("/api/core/{$event}/{version=0.1}")
public class AcusticusAPIActionBean implements ActionBean {
    private ActionBeanContext context;
 
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    private float version;
    public float getVersion(){ return version; }
    public void setVersion(float v){ this.version = v; }
    
    @DefaultHandler
    public Resolution version(){
    	return new StreamingResolution("application/json", "{'name':'acusticus', 'version': '" + Float.toString(getVersion()) + "'}");
    }
} 
