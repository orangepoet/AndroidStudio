package studio.android.models;

@SuppressWarnings("unused")
public class WeatherInfo {
	private String city;
	private String cityid;
	private String isRadar;
	private String Radar;

	private String SD;

	private String temp;
	private String time;
	private String WD;
	private String WS;

	private String WSE;

	public String getCity() {
		return city;
	}

	public String getIsRadar() {
		return isRadar;
	}

	public String getRadar() {
		return Radar;
	}

	public String getSD() {
		return SD;
	}

	public String getTemp() {
		return temp;
	}

	public String getTime() {
		return time;
	}

	public String getWD() {
		return WD;
	}

	public String getWS() {
		return WS;
	}

	public String getWSE() {
		return WSE;
	}

}
