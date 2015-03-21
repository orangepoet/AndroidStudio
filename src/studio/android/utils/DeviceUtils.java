package studio.android.utils;

/**
 * 获取设备信息
 * 
 * @author orange
 *
 */
public class DeviceUtils {

	/**
	 * 获取样机SSN
	 * 
	 * @return
	 */
	public static String getSSN() {
		// TODO:getSSN
		return "";
	}

	/**
	 * 获取样机操作系统
	 * 
	 * @return
	 */
	public static String getOS() {
		return System.getProperty("os.version");
	}

	/**
	 * 获取样机型号
	 * 
	 * @return
	 */
	public static String getModel() {
		return android.os.Build.MODEL;
	}
}
