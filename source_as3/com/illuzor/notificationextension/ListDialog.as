package com.illuzor.notificationextension {
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	/**
	 * ...
	 * @author illuzor
	 */
	
	[Event(name = "select", type = "flash.events.Event")]
	
	public class ListDialog extends EventDispatcher {
		
		private var context:ExtensionContext;
		private var buttonTitles:Vector.<String> = new Vector.<String>();
		private var _cancelFunction:Function;
		private var _selectedID:int = -1;

		public var cancelable:Boolean;
		public var title:String;
		
		public function ListDialog(context:ExtensionContext, title:String, cancelable:Boolean) {
			this.cancelable = cancelable;
			this.title = title;
			this.context = context;
		}
		
		public function addButton(title:String):void {
			buttonTitles.push(title);
		}
		
		public function set cancelFunction(value:Function):void {
			_cancelFunction = value;
		}
		
		public function show():void {
			context.call("showListDialog", title, buttonTitles.join(","), cancelable, _cancelFunction != null);
			context.addEventListener(StatusEvent.STATUS, onAlertStatus);
		}
		
		private function onAlertStatus(e:StatusEvent):void {
			context.removeEventListener(StatusEvent.STATUS, onAlertStatus);
			if (String(e.code) == "cancel") {
				if (_cancelFunction != null) _cancelFunction.call();
			} else {
				_selectedID = int(e.code);
				dispatchEvent(new Event(Event.SELECT));
			}
		}
		
		public function get selectedID():int {
			if (_selectedID == -1) {
				throw new Error("Needed to wait for Event.SELECT before get selectedID");
				return;
			}
			return _selectedID;
		}
		
	}
}