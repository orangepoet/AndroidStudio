package studio.android.utils;

import android.util.Log;

public class LogUtility {

	public static void WriteInfo(Object msg) {
		Log.i(tag, String.valueOf(msg));
	}

	public static void WriteError(Object msg) {
		Log.e(tag, String.valueOf(msg));
	}

	private static final String tag = "AndroidStudio";
}
