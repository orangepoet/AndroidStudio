package studio.android.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class FileUtils {

	/**
	 * 流式写文件
	 * 
	 * @param file
	 * @param is
	 */
	public static void write(File file, InputStream is) {
		try {
			OutputStream os = new FileOutputStream(file);

			byte[] buffer = new byte[1024];
			int bytesRead;
			// read from is to buffer
			while ((bytesRead = is.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			is.close();
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getAlbumStorageDir(String albumName) {
		File file = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				albumName);
		if (!file.exists() && !file.mkdirs()) {
			Log.e("rsmapp", "Directory not created");
		}
		return file;
	}

	/**
	 * SD卡是否可以使用
	 * 
	 * @return
	 */
	public static boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/**
	 * 文件下载, 支持断点续传
	 * 
	 * @param url
	 * @param mContext
	 */
	public static long download(String url, Context mContext) {
		DownloadManager downloadMgr = (DownloadManager) mContext
				.getSystemService(Context.DOWNLOAD_SERVICE);
		Uri uri = Uri.parse(url);
		DownloadManager.Request request = new DownloadManager.Request(uri);
		request.setTitle("title")
				.setDescription("description")
				.setVisibleInDownloadsUi(false)
				.setDestinationInExternalPublicDir(
						Environment.DIRECTORY_PICTURES, "Wallpaper");
		long downloadID = downloadMgr.enqueue(request);
		return downloadID;
	}
}
