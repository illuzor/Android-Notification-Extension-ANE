package com.illuzor.notificationextension.functions;

import android.widget.Toast;

import com.adobe.fre.*;

public class ShowToastFuntion implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		try {
			String message = args[0].getAsString();
			int duration = args[1].getAsInt();
			int gravity = args[2].getAsInt();
			Toast toast = Toast.makeText(context.getActivity(), message, duration);
			if(gravity != 0) toast.setGravity(gravity, 0, 0);
			toast.show();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
