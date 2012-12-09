package com.illuzor.notificationextension {
	
	import flash.external.ExtensionContext;
	
	/**
	 * ...
	 * @author illuzor  //  illuzor@gmail.com  //  illuzor.com
	 */
	
	public class NotificationExtension {
		
		private static var context:ExtensionContext;
		
		private static function init():void {
			context = ExtensionContext.createExtensionContext("com.illuzor.extensions.NotificationExtension", null);
		}
		
		public static function showToast(toastText:String, duration:uint = 0):void {
			if (!context) init();
			context.call("showToast", toastText, duration);
		}
		
		public static function getAlertDialog(title:String, message:String, cancelable:Boolean = false):AlertDialog {
			if (!context) init();
			return new AlertDialog(context, title, message, cancelable);
		}
		
		public static function getListDialog(title:String,cancelable:Boolean = true):ListDialog {
			if (!context) init();
			return new ListDialog(context, title, cancelable);
		}
		
		public static function dispose():void {
			if (context) {
				context.dispose();
				context = null;
			}
		}
		
	}
}