package studio.android.utils;

/**
 * ��ȡ�豸��Ϣ
 * 
 * @author orange
 *
 */
public class DeviceUtils {

	/**
	 * ��ȡ����SSN
	 * 
	 * @return
	 */
	public static String getSSN() {
		// TODO:getSSN
		return "";
	}

	/**
	 * ��ȡ��������ϵͳ
	 * 
	 * @return
	 */
	public static String getOS() {
		return System.getProperty("os.version");
	}

	/**
	 * ��ȡ�����ͺ�
	 * 
	 * @return
	 */
	public static String getModel() {
		return android.os.Build.MODEL;
	}
}
