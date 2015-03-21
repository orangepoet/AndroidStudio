package studio.android.activity;

import java.io.IOException;

import studio.android.handler.IotdHandler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class NasaDailyActivity extends Activity {

	private ProgressDialog pd;
	private Handler handler;
	public static final long FAKE_EXECUTE_TIME = 1500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nasa_daily);
		refreshFromFeed();
	}

	private void refreshFromFeed() {
		IotdHandler handler = new IotdHandler();
		handler.processFeed();
	}

	public void onRefresh(View view) {
		// System.out.println("Main thread id:" +
		// Thread.currentThread().getId());
		System.out.println("1.Refresh button click.");
		pd = ProgressDialog.show(this, "Loading",
				"Loading the image of the Day");

		handler = new Handler();
		Thread workThread = new Thread() {
			@Override
			public void run() {

				System.out.println("3.Processing feed begins.");

				IotdHandler itodhandler = new IotdHandler();
				itodhandler.processFeed();

				System.out.println("4.Processing feed completes.");

				handler.post(new Runnable() {

					@Override
					public void run() {
						System.out.println("5.Update UI and dismiss dialog.");
						pd.dismiss();
					}
				});
			}
		};
		System.out.println("2.Work thread starts.");
		workThread.start();
	}

	public void setWallpaper(View view) {
		Thread thread = new Thread() {
			@Override
			public void run() {

				WallpaperManager wpMgr = WallpaperManager
						.getInstance(NasaDailyActivity.this);
				Bitmap bitmap = null;
				ImageView imageView = (ImageView) findViewById(R.id.nasa_image);
				bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

				try {
					wpMgr.setBitmap(bitmap);
					handler.post(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(NasaDailyActivity.this,
									"Set up success", Toast.LENGTH_SHORT)
									.show();

						}
					});

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					handler.post(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(NasaDailyActivity.this,
									"Set up success", Toast.LENGTH_SHORT)
									.show();

						}
					});
				}
			}
		};
		thread.start();
	}
}
