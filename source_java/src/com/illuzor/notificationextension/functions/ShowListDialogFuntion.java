package com.illuzor.notificationextension.functions;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.adobe.fre.*;

public class ShowListDialogFuntion implements FREFunction {
	
	CharSequence[] items;

	@Override
	public FREObject call(final FREContext context, FREObject[] args) {
		try {
			String title = args[0].getAsString();
			String buttonList = args[1].getAsString();
			boolean cancelable = args[2].getAsBool();
			boolean cancelFuntion = args[3].getAsBool();
			items = buttonList.split(",");
			
			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context.getActivity());
			alertBuilder.setTitle(title);
			
			alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int buttonNumber) {
	            	context.dispatchStatusEventAsync((String.valueOf(buttonNumber)), "");
	           }
	       });
			alertBuilder.setCancelable(cancelable);
			
			if(cancelFuntion){
				alertBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
					public void onCancel(DialogInterface dialog) {
						context.dispatchStatusEventAsync("cancel", "");
					}
				});
			}
			
			alertBuilder.create();
			alertBuilder.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
