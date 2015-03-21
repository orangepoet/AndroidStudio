package studio.android.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static boolean validateInputStr(String str) {
		String pattern = "and|or|exec|execute|insert|select|delete|update|alter|create|drop|count|\\*|chr|char|asc|mid|substring|master|truncate|declare|xp_cmdshell|restore|backup|net +user|net +localgroup +administrators";

		try {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(str);
			if (!m.find())
				return true;
		} catch (Exception ex) {
			// TODO: ERROR LOG
		}
		return false;
	}
}
