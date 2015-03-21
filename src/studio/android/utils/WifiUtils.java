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
	 * 按照信号获取前3个可用Wifi扫描结果
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
	 * 获取SSID
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
	 * 获取Mac地址
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
	 * 获取当前连接信息
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
