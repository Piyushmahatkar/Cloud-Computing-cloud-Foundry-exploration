package robocode.util;

import netscape.javascript.JSObject;

public class LogUtil {
	private static JSObject window;

	public static  JSObject getWindow() {
		return window;
	}

	public  static  void setWindow(JSObject window) {
		LogUtil.window = window;
		LogUtil.log("window Loaded");
	}
	public static void log(String s){
		if(window!=null)
		 window.call("writeSummary", new Object[] {s});
		else
			System.out.println(s);
	}
}
