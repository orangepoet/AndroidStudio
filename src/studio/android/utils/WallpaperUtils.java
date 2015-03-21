package studio.android.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class WallpaperUtils {
	/**
	 * 设置壁纸
	 * 
	 * @param file
	 *            壁纸文件
	 * @param context
	 * @return 成功返回true, 失败返回false
	 */
	public static boolean setupWallpaper(File file, Context context) {
		if (file == null) {
			throw new IllegalArgumentException("file is null");
		}

		Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
		WallpaperManager wallpaperManager = WallpaperManager
				.getInstance(context);
		try {
			wallpaperManager.setBitmap(bitmap);
			return true;
		} catch (IOException e) {
			Log.e("rsmapp", "WallpaperThread:" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 从网络获取壁纸
	 * 
	 * @deprecated 请使用可以断点续传的 {@link downNetWallpaper} instead.
	 * 
	 * @param file
	 *            保存文件
	 * @param url
	 *            网络地址
	 * @return 返回保存结果, 成功返回true, 失败返回false
	 */
	@Deprecated
	public static File downWallpaper(String url) {
		String filename = null;
		if (url.lastIndexOf(".") == -1) {
			throw new IllegalArgumentException("url is not correct!");
		}
		filename = url.substring(url.lastIndexOf(".") + 1);
		File file = new File(getWallpaperDir(), filename);
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			InputStream is = new URL(url).openStream();
			FileUtils.write(file, is);

		} catch (IOException e) {
			Log.e("rsmapp", "downWallpaper:" + e.getMessage());
			e.printStackTrace();
		}
		return file;
	}

	public static File getWallpaperDir() {
		return FileUtils.getAlbumStorageDir("Wallpaper");
	}

	public static File getWallpaper(String wallpaper) {
		return new File(getWallpaperDir(), wallpaper);
	}
}
