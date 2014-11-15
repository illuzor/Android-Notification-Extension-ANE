Android-Notification-Extension-ANE
==================================
Native Extension for Adobe AIR

Features:

	- toast
	- alert dialog (without buttons or with from 1 to 3 buttons)
	- list dialog (with any number of buttons)
	
![notif_ane.png](http://download.illuzor.com/images/github/ane/notif_ane.png)

Important! Be shure you are using latest version of AIR SDK. This ANE builded for 15.0.356
	
How to use:

Connect com.illuzor.extensions.NotificationExtension.ane file to your android air project.
Imports: 
import com.illuzor.notificationextension.NotificationExtension;
import com.illuzor.notificationextension.ToastDuration;
import com.illuzor.notificationextension.ToastGravity;

1) Toast:

	import com.illuzor.notificationextension.ToastDuration;
	
	NotificationExtension.showToast("Toast text");
	// or
	NotificationExtension.showToast("Toast text", ToastDuration.TOAST_SHORT);
	// or
	NotificationExtension.showToast("Toast text", ToastDuration.TOAST_LONG);
	// also you can add toast gravity:
	NotificationExtension.showToast("Toast text", ToastDuration.TOAST_LONG, ToastGravity.TOP) // or ToastGravity.BOTTOM, or ToastGravity.LEFT etc.
	
2) Alert dialog:

Example 1, Alert without buttons:

	import com.illuzor.notificationextension.AlertDialog;
	
	var alert:AlertDialog = NotificationExtension.getAlertDialog("Alert title", "Alert Message");
	alert.show();
	
Example 2, Alert with OK button
	
	import com.illuzor.notificationextension.AlertDialog;
	import com.illuzor.notificationextension.AlertActionType;

	var alert:AlertDialog = NotificationExtension.getAlertDialog("Alert title", "Alert Message");
	alert.setButton(AlertActionType.NEUTRAL_BUTTON, "OK");
	alert.show();
	
Example 3, Alert with three buttons with callback functions:

	import com.illuzor.notificationextension.AlertDialog;
	import com.illuzor.notificationextension.AlertActionType;

	var alert:AlertDialog = NotificationExtension.getAlertDialog("Alert title", "Alert Message");
	alert.setButton(AlertActionType.NEGATIVE_BUTTON, "Negative Button", button1Pressed);
	alert.setButton(AlertActionType.POSITIVE_BUTTOND, "Positive Button", button2Pressed);
	alert.setButton(AlertActionType.NEUTRAL_BUTTON, "Neutral Button", button3Pressed);
	alert.show();
	
	private function button1Pressed():void {
		trace("Negative Button Pressed");
	}
	
	private function button2Pressed():void {
		trace("Positive Button Pressed");
	}
	
	private function button3Pressed():void {
		trace("Neutral Button Pressed");
	}
	
	
You can add three buttons maximum with callback functions or without.

Parameters:

	alert.title = "title";
	alert.message = "message";
	alert.cancelable = true;
	
	// also you can add callback function for cancel:
	
	alert.cancelFunction = cancelFunc;
	
	private function cancelFunc():void {
		trace("Alert Canceled");
	}
	
3) List Dialog:
	
	import com.illuzor.notificationextension.ListDialog;
	
	var dialog:ListDialog = NotificationExtension.getListDialog(titleText.text, cancelableBox.value);
	var buttonsNumber:uint = 5;
	
	for (var i:int = 0; i < buttonsNumber; i++) {
		dialog.addButton("Button ".concat(i+1));
	}
	
	dialog.show();
	dialog.addEventListener(Event.SELECT, onSelected);
	
	private function onSelected(e:Event):void {
		e.target.removeEventListener(Event.SELECT, onSelected);
		trace("Button " + String(e.target.selectedID + 1) + " Pressed");
	}
	
	// you can add cancel callback function:
	dialog.cancelable = true;
	
	private function cancelFunc():void {
		dialog.removeEventListener(Event.SELECT, onSelected);
		trace("Dialog Canceled");
	}

4) Dispose. If you don`t need extension after use, dispose it:

	NotificationExtension.dispose();

Demo app - http://yadi.sk/d/Se_LR8fm1lgsD

![demoAppQR.gif](http://download.illuzor.com/images/github/ane/demoAppQR.gif)