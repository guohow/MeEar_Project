package android.guohow.melody.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.guohow.melody.R;
import android.guohow.melody.preference.SharedConfig;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ThemeManager {
	private final Context context;
	private SharedPreferences spf, spf2;
	ListView listView;
	TextView bottomInfo;
	RelativeLayout layout;
	String theme, theme2;
	View mRootView;

	public ThemeManager(Context context, ListView listView,
			TextView bottomInfo, RelativeLayout layout, View mRootView) {
		this.context = context;
		this.listView = listView;
		this.bottomInfo = bottomInfo;
		this.layout = layout;
		this.mRootView = mRootView;
		getColorThemePreference();
		setColorTheme();
		getBgThemePreference();
		setBgTheme();

	}

	public void getColorThemePreference() {

		spf = PreferenceManager.getDefaultSharedPreferences(context);
		theme = spf.getString("theme_perf", "pink");

	}

	public void getBgThemePreference() {

		spf2 = PreferenceManager.getDefaultSharedPreferences(context);
		theme2 = spf2.getString("bg_perf", "default");

	}

	// 获取主题设置
	private void setColorTheme() {

		if (theme.equals("default")) {

		}
		if (theme.equals("red")) {
			listView.setSelector(R.drawable.list_selecter_red);
			bottomInfo.setBackgroundResource(R.color.holo_red_dark);
			layout.setBackgroundResource(R.color.holo_red_dark);

		}
		if (theme.equals("green")) {
			listView.setSelector(R.drawable.list_selecter_green);
			bottomInfo.setBackgroundResource(R.color.green);
			layout.setBackgroundResource(R.color.green);
		}
		if (theme.equals("light_blue")) {
			listView.setSelector(R.drawable.list_selecter_blue);
			bottomInfo.setBackgroundResource(R.color.blue);
			layout.setBackgroundResource(R.color.blue);
		}
		if (theme.equals("pink")) {
			listView.setSelector(R.drawable.list_selecter_pink);
			bottomInfo.setBackgroundResource(R.color.pink);
			layout.setBackgroundResource(R.color.pink);
		}
		if (theme.equals("light_yellow")) {
			listView.setSelector(R.drawable.list_selecter_yellow);
			bottomInfo.setBackgroundResource(R.color.light_yellow);
			layout.setBackgroundResource(R.color.light_yellow);
		}
		if (theme.equals("orange")) {
			listView.setSelector(R.drawable.list_selecter_orange);
			bottomInfo.setBackgroundResource(R.color.orange);
			layout.setBackgroundResource(R.color.orange);
		}
		if (theme.equals("tek_dark")) {
			listView.setSelector(R.drawable.list_selecter_tek_dark);
			bottomInfo.setBackgroundResource(R.color.bg_dark_tab);
			layout.setBackgroundResource(R.drawable.bg_main_repeat);
		}

	}

	// 获取背景设置
	private void setBgTheme() {

		if (theme2.equals("default")) {
			new SharedConfig(context).setBgMod(0);
		}
		if (theme2.equals("light_1")) {
			mRootView.setBackgroundResource(R.drawable.light_5);
			new SharedConfig(context).setBgMod(1);
		}
		if (theme2.equals("light_2")) {
			mRootView.setBackgroundResource(R.drawable.light_6);
			new SharedConfig(context).setBgMod(1);

		}
		if (theme2.equals("girl")) {
			mRootView.setBackgroundResource(R.drawable.girl);
			new SharedConfig(context).setBgMod(1);
		}
		if (theme2.equals("sky")) {
			mRootView.setBackgroundResource(R.drawable.sky);
			new SharedConfig(context).setBgMod(1);
		}
		if (theme2.equals("ios7")) {
			mRootView.setBackgroundResource(R.drawable.ios7);
			new SharedConfig(context).setBgMod(1);
		}
		if (theme2.equals("du")) {
			mRootView.setBackgroundResource(R.drawable.du);
			new SharedConfig(context).setBgMod(1);
		}
		if (theme2.equals("rabbit")) {
			mRootView.setBackgroundResource(R.drawable.rabbit);
			new SharedConfig(context).setBgMod(1);
		}
		if (theme2.equals("baby")) {
			mRootView.setBackgroundResource(R.drawable.baby);
			new SharedConfig(context).setBgMod(1);
		}
		if (theme2.equals("desk")) {
			mRootView.setBackgroundResource(R.drawable.desk);
			new SharedConfig(context).setBgMod(1);
		}
		if (theme2.equals("pink")) {
			mRootView.setBackgroundResource(R.drawable.pink);
			new SharedConfig(context).setBgMod(1);
		}

	}

}
