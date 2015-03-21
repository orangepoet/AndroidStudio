package studio.android.models;

import java.util.ArrayList;
import java.util.List;

import studio.android.activity.FileDownActivity;
import studio.android.activity.NasaDailyActivity;
import studio.android.activity.SMSActivity;
import studio.android.activity.WeatherActivity;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Catalog {
	public static class Item {
		private String desc;

		private Class<?> cls;

		public Item(String name, Class<?> cls) {
			this.desc = name;
			this.cls = cls;
		}

		public Class<?> getItemClass() {
			return cls;
		}

		public String getName() {
			return desc;
		}

		@Override
		public String toString() {
			return getName();
		}
	}

	private static final String FILE_DOWNLOAD = "FileDownload";
	private static final String WEATHER = "Weather";
	private static final String SMS = "SMS";
	private static final String NASA_DAILY = "NasaDaily";

	private static List<Item> elementData = new ArrayList<Item>();

	static {
		elementData.add(new Item(FILE_DOWNLOAD, FileDownActivity.class));
		elementData.add(new Item(WEATHER, WeatherActivity.class));
		elementData.add(new Item(SMS, SMSActivity.class));
		elementData.add(new Item(NASA_DAILY, NasaDailyActivity.class));
	}

	private Catalog() {
	}

	public static List<Item> getItems() {
		return elementData;
	}

}
