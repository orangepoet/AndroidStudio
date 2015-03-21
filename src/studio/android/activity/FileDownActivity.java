package studio.android.activity;

import java.io.File;

import studio.android.receiver.DownloadReceiver;
import studio.android.utils.FileUtils;
import studio.android.utils.LogUtility;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

public class FileDownActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_down);

		downloadMgr = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
		setRecevier();
	}

	/**
	 * ÎÄ¼þÏÂÔØ
	 * 
	 * @param view
	 */
	public void onDownload(View view) {
		try {
			File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File wallpaper = GetFakeImageFile(dir);

			Uri uri = Uri.parse(imgUrl);
			DownloadManager.Request request = new DownloadManager.Request(uri);
			request.setTitle("title").setDescription("description")
			// .setDestinationInExternalFilesDir(this,
			// Environment.DIRECTORY_DOWNLOADS, fileName);
					.setDestinationUri(Uri.fromFile(wallpaper));

			long downloadID = downloadMgr.enqueue(request);

			SharedPreferences sharedata = getSharedPreferences(DOWN_LOAD, 0);
			Editor edit = sharedata.edit();
			edit.putLong(DOWN_LOAD_ID, downloadID);
			edit.commit();
			LogUtility.WriteInfo(String.format("Download id: %d",
					sharedata.getLong(DOWN_LOAD_ID, -99)));

		} catch (Exception e) {
			Log.e("firstapp", e.getMessage());
			e.printStackTrace();
		}
	}

	public void onOtherdownload(View view) {
		FileUtils.download(imgUrl, FileDownActivity.this);
	}

	private File GetFakeImageFile(File dir) {
		String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
		File wallpaper = new File(dir, fileName);
		return wallpaper;
	}

	private void setRecevier() {
		IntentFilter filter = new IntentFilter(
				DownloadManager.ACTION_DOWNLOAD_COMPLETE);
		registerReceiver(receiver, filter);
	}

	private final String imgUrl = "http://d.hiphotos.baidu.com/image/w%3D2048/sign=88c40cf0b54543a9f51bfdcc2a2f8b82/0b7b02087bf40ad1590415e7552c11dfa8ecced6.jpg";

	private DownloadManager downloadMgr;

	private DownloadReceiver receiver = new DownloadReceiver();

	public static final String DOWN_LOAD = "download";

	public static final String DOWN_LOAD_ID = "downloadID";
}
