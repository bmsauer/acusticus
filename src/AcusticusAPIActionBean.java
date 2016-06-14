package net.sauertek.acusticus.web.api;
 
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
 
/**
* Acustic.us API
* @author Brian Sauer
*/
public class AcusticusAPIActionBean implements ActionBean {
    private ActionBeanContext context;
 
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    
    //@DefaultHandler
    //public Resolution tmp() {
    //    return new ForwardResolution("/quickstart/tmp.jsp");
    //}
    @DefaultHandler
    public Resolution tmp(){
    	return new StreamingResolution("application/json", "{'name':'brian'}");
    }
} 
