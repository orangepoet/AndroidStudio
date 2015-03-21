package studio.android.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studio.android.models.Catalog;
import studio.android.models.Catalog.Item;
import studio.android.utils.LogUtility;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * An activity representing a list of Items. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link ItemDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details (if present) is a
 * {@link ItemDetailFragment}.
 * <p>
 * This activity also implements the required {@link ItemListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class ItemListActivity extends FragmentActivity implements
		ItemListFragment.OnItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);
	}

	/**
	 * Callback method from {@link ItemListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {

		Map<String, Class<?>> map = GetMap();
		Class<?> cls = map.get(id);
		if (cls != null) {
			Intent detailIntent = new Intent(this, cls);
			LogUtility.WriteInfo(String.format("Jump to activity: %s", id));
			startActivity(detailIntent);
		}
	}

	private Map<String, Class<?>> GetMap() {
		Map<String, Class<?>> result = new HashMap<String, Class<?>>();
		List<Item> category = Catalog.getItems();
		for (Item item : category) {
			result.put(item.getName(), item.getItemClass());
		}
		return result;
	}
}
