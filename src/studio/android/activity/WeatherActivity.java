package studio.android.activity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import studio.android.models.Weather;
import studio.android.models.WeatherInfo;
import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * 天气预报, 当前支持最新气温, 风力等查询
 * 
 * @author Orange Cheng
 * 
 */
public class WeatherActivity extends Activity {
	private WeatherInfo wi;
	private Handler handler;
	private ProgressDialog pd;
	private TextView temp;
	private TextView wd;
	private TextView ws;
	private TextView city;
	private TextView sd;
	private TextView time;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		findControls();
		getWeatherInfo();
		getLocation();
	}

	private void findControls() {
		temp = (TextView) findViewById(R.id.temp1);
		wd = (TextView) findViewById(R.id.wd);
		ws = (TextView) findViewById(R.id.ws);
		city = (TextView) findViewById(R.id.city);
		sd = (TextView) findViewById(R.id.sd1);
		time = (TextView) findViewById(R.id.time);
	}

	private void getWeatherInfo() {
		pd = ProgressDialog.show(WeatherActivity.this, "天气信息", "加载天气信息中...");
		handler = new Handler();
		// TODO Auto-generated method stub
		Thread workThread = new Thread() {
			@Override
			public void run() {
				// Request Weather Info from weather.com.cn
				try {
					URL url = new URL(
							"http://www.weather.com.cn/data/sk/101020500.html");

					Gson gson = new Gson();
					Weather weather = gson.fromJson(
							new InputStreamReader(url.openStream(), "UTF-8"),
							Weather.class);

					wi = weather.getWeatherinfo();
					handler.post(new Runnable() {
						@Override
						public void run() {
							// loading the weather info.
							city.setText(wi.getCity());
							temp.setText(wi.getTemp());
							wd.setText(wi.getWD());
							ws.setText(wi.getWS());
							sd.setText(wi.getSD());
							time.setText(wi.getTime());

							pd.dismiss();
						}
					});
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		workThread.start();
	}

	public void onRefresh(View view) {
		getWeatherInfo();
	}

	public void getLocation() {
		LocationManager locationMgr = (LocationManager) WeatherActivity.this
				.getSystemService(LOCATION_SERVICE);
		CurLocationListener listener = new CurLocationListener();
		locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				listener);
	}

	private class CurLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			handler.post(new Runnable() {

				@Override
				public void run() {
					Toast.makeText(WeatherActivity.this, "位置发生变化!",
							Toast.LENGTH_SHORT).show();
				}
			});
			System.out.println("Longitude: " + location.getLongitude());
			System.out.println("Latitude: " + location.getLatitude());

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

	}
}
