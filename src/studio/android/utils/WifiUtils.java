package studio.android.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiUtils {
	private WifiManager wifiManager;

	public WifiUtils(Context context) {
		wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
	}

	/**
	 * �����źŻ�ȡǰ3������Wifiɨ����
	 * 
	 * @return
	 */
	public List<ScanResult> getScanResult() {
		List<ScanResult> scanResults = new ArrayList<ScanResult>();
		// if (isWifiEnable()) {
		scanResults = wifiManager.getScanResults();
		Collections.sort(scanResults, new Comparator<ScanResult>() {

			@Override
			public int compare(ScanResult left, ScanResult right) {
				return ((Integer) left.level).compareTo((Integer) right.level);
			}
		});
		// }
		int size = scanResults.size();
		scanResults = scanResults.subList(0, size < 2 ? size : 2);
		return scanResults;
	}

	public boolean isWifiEnable() {
		return wifiManager.isWifiEnabled();
	}

	/**
	 * ��ȡSSID
	 * 
	 * @return
	 */
	public String getSSID() {
		WifiInfo connectionInfo = getConnectionInfo();
		if (connectionInfo != null) {
			return connectionInfo.getSSID();
		}
		return null;
	}

	/**
	 * ��ȡMac��ַ
	 * 
	 * @return
	 */
	public String getMacAddress() {
		WifiInfo connectionInfo = getConnectionInfo();
		if (connectionInfo != null) {
			return connectionInfo.getMacAddress();
		}
		return null;
	}

	/**
	 * ��ȡ��ǰ������Ϣ
	 * 
	 * @return
	 */
	public WifiInfo getConnectionInfo() {
		WifiInfo connectionInfo = null;
		if (isWifiEnable()) {
			connectionInfo = wifiManager.getConnectionInfo();
		}
		return connectionInfo;
	}
}
