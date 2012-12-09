package com.illuzor.notificationextension {
	
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	/**
	 * ...
	 * @author illuzor  //  illuzor@gmail.com  //  illuzor.com
	 */
	
	public class AlertDialog {
		
		private var context:ExtensionContext;
		private var alertFunctions:Object;
		private var cancelFunctionExists:Boolean;
		
		public var title:String;
		public var message:String;
		public var cancelable:Boolean;
		
		public function AlertDialog(context:ExtensionContext, title:String, message:String, cancelable:Boolean = true) {
			this.cancelable = cancelable;
			this.message = message;
			this.title = title;
			this.context = context;
			alertFunctions = { };
			alertFunctions[AlertActionType.POSITIVE_BUTTOND] = { "label":"" };
			alertFunctions[AlertActionType.NEGATIVE_BUTTON] = { "label":"" };
			alertFunctions[AlertActionType.NEUTRAL_BUTTON] = { "label":"" };
			alertFunctions[AlertActionType.CANCELED] = { "label":"" };
		}
		
		public function setButton(type:String, label:String, func:Function = null):void {
			alertFunctions[type]["label"] = label;
			alertFunctions[type]["func"] = func;
		}
		
		public function set cancelFunction(value:Function):void {
			cancelFunctionExists = value != null;
			alertFunctions[AlertActionType.CANCELED]["func"] = value;
		}
		
		public function show():void {
			context.call("showAlert", title, message, cancelable, 
			alertFunctions[AlertActionType.POSITIVE_BUTTOND]["label"],
			alertFunctions[AlertActionType.NEGATIVE_BUTTON]["label"],
			alertFunctions[AlertActionType.NEUTRAL_BUTTON]["label"],
			cancelFunctionExists);
			context.addEventListener(StatusEvent.STATUS, onAlertStatus);
		}
		
		private function onAlertStatus(e:StatusEvent):void {
			context.removeEventListener(StatusEvent.STATUS, onAlertStatus);
			if (alertFunctions[e.code].hasOwnProperty("func") && alertFunctions[e.code]["func"] != null)
				alertFunctions[e.code]["func"].call();
		}
		
	}
}