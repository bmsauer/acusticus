package net.sauertek.acusticus.ext;

import net.sourceforge.stripes.controller.NameBasedActionResolver;

public class AcusticusActionResolver extends NameBasedActionResolver {
    @Override
    protected String getBindingSuffix() {
	return "";
    }
    @Override
    protected String getUrlBinding(String actionBeanName) {
	String result = super.getUrlBinding(actionBeanName);
	return "/api" + result;
    }
}
