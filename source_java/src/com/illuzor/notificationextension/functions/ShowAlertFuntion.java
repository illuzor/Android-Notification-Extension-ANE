package com.illuzor.notificationextension.functions;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.adobe.fre.*;
import com.illuzor.notificationextension.AlertActionType;


public class ShowAlertFuntion implements FREFunction{
	
	FREContext context;
	AlertDialog.Builder alertBuilder;

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		this.context = context;
		try {
			String alertTitle = args[0].getAsString();
			String alertMessage = args[1].getAsString();
			boolean cancelable = args[2].getAsBool();
			String posButtonLabel = args[3].getAsString();
			String negButtonLabel = args[4].getAsString();
			String neuButtonLabel = args[5].getAsString();
			boolean cancelFuntion = args[6].getAsBool();
			
			alertBuilder = new AlertDialog.Builder(context.getActivity());

			if(alertTitle != "") alertBuilder.setTitle(alertTitle);
			if(alertMessage != "")alertBuilder.setMessage(alertMessage);
			if(posButtonLabel != "") addButton(1, posButtonLabel);
			if(negButtonLabel != "") addButton(2, negButtonLabel);
			if(neuButtonLabel != "") addButton(3, neuButtonLabel);
			alertBuilder.setCancelable(cancelable);
			if(cancelFuntion) addCancelAction();

			AlertDialog alertDialog = alertBuilder.create();
			alertDialog.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private void addButton(int buttonNum, String buttonTitle){
		switch(buttonNum){
				
		case 1:
			alertBuilder.setPositiveButton(buttonTitle, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					context.dispatchStatusEventAsync(AlertActionType.POSITIVE_BUTTON, "");
				}
			});
		break;
			
		case 2:
			alertBuilder.setNegativeButton(buttonTitle, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					context.dispatchStatusEventAsync(AlertActionType.NEGATIVE_BUTTON, "");
				}
			});
		break;
		
		case 3:
			alertBuilder.setNeutralButton(buttonTitle, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					context.dispatchStatusEventAsync(AlertActionType.NEUTRAL_BUTTON, "");
				}
			});
		break;
		}
	}
	
	private void addCancelAction(){
		alertBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
			public void onCancel(DialogInterface dialog) {
				context.dispatchStatusEventAsync(AlertActionType.CANCELED, "");
			}
		});
	}

}
