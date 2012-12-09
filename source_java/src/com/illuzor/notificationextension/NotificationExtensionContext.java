package com.illuzor.notificationextension;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.illuzor.notificationextension.functions.*;

public class NotificationExtensionContext extends FREContext {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		Map<String, FREFunction> map = new HashMap<String, FREFunction>();
		map.put("showToast", new ShowToastFuntion());
		map.put("showAlert", new ShowAlertFuntion());
		map.put("showListDialog", new ShowListDialogFuntion());
		return map;
	}

}
