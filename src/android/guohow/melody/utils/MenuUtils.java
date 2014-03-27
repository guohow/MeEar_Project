package android.guohow.melody.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.guohow.melody.fragment.MelodyMenuFragment;

public class MenuUtils extends Activity {
	static List<HashMap<String, Object>> items;

	public List<HashMap<String, Object>> getMenuItemList() {
		MenuUtils.items = new ArrayList<HashMap<String, Object>>();

		final HashMap<String, Object> map = new HashMap<String, Object>();
		final HashMap<String, Object> map2 = new HashMap<String, Object>();
		final HashMap<String, Object> map3 = new HashMap<String, Object>();
		final HashMap<String, Object> map4 = new HashMap<String, Object>();
		final HashMap<String, Object> map5 = new HashMap<String, Object>();

		map.put("menu_title", "正在进行");
		final int icon0 = MelodyMenuFragment.icon_0;
		map.put("icon", icon0);

		map2.put("menu_title", "主题");
		final int icon1 = MelodyMenuFragment.icon_1;
		map2.put("icon", icon1);

		map3.put("menu_title", "开发者");
		final int icon2 = MelodyMenuFragment.icon_2;
		map3.put("icon", icon2);

		map4.put("menu_title", "用户偏好");
		final int icon3 = MelodyMenuFragment.icon_3;
		map4.put("icon", icon3);

		map5.put("menu_title", "退出");
		final int icon4 = MelodyMenuFragment.icon_4;
		map5.put("icon", icon4);

		MenuUtils.items.add(map);
		MenuUtils.items.add(map2);
		MenuUtils.items.add(map3);
		MenuUtils.items.add(map4);
		MenuUtils.items.add(map5);

		for (int i = 0; i < 5; i++) {
			System.out.println("" + MenuUtils.items.get(i).get("menu_title"));
		}

		return MenuUtils.items;
	}

}
