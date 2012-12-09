package com.illuzor.notificationextension;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class NotificationExtension implements FREExtension {
	
	private NotificationExtensionContext context;

	@Override
	public FREContext createContext(String arg0) {
		context = new NotificationExtensionContext();
		return context;
	}

	@Override
	public void dispose() {
		context.dispose();
		context = null;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

}
