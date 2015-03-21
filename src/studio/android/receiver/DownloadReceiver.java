package studio.android.receiver;

import studio.android.activity.FileDownActivity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class DownloadReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		long rDownloadID = intent.getLongExtra(
				DownloadManager.EXTRA_DOWNLOAD_ID, -1);

		SharedPreferences sharedata = context.getSharedPreferences(
				FileDownActivity.DOWN_LOAD, 0);
		long mDownloadID = sharedata.getLong(FileDownActivity.DOWN_LOAD_ID,
				Integer.MIN_VALUE);
		if (mDownloadID == rDownloadID) {
			Log.i("firstapp", " my download complete.");
		}
	}
}
