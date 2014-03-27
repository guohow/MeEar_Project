package android.guohow.melody.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * @author guohow
 *	存储和获取用户配置
 */
public class SharedConfig {
	Context context;
	SharedPreferences first, playModSettings, bgModSettings, fullScr,
			splashText;

	public SharedConfig(Context context) {
		this.context = context;
	}

	public int getFirstInitConfig() {
		first = context.getSharedPreferences("first_config", 0);
		return first.getInt("first_config", 0);
	}

	public void clearFirstInitConfig() {
		first = context.getSharedPreferences("first_config", 0);
		first.edit().putInt("first_config", 1).commit();
	}

	public int getPlayMod() {

		playModSettings = context.getSharedPreferences("playMod_settings", 1);
		return playModSettings.getInt("mod", 1);

	}

	public void setPlayMod(int i) {

		playModSettings = context.getSharedPreferences("playMod_settings", 1);
		playModSettings.edit().putInt("mod", i).commit();

	}

	// 存储背景设置的信息，修改字母textView 背景 0表示使用了默认背景
	public void setBgMod(int i) {

		bgModSettings = context.getSharedPreferences("bgMod_settings", 0);
		bgModSettings.edit().putInt("bg_mod", i).commit();

	}

	// 获取背景设置的信息，
	public int getBgMod() {

		bgModSettings = context.getSharedPreferences("bgMod_settings", 0);
		final int result = bgModSettings.getInt("bg_mod", 0);
		return result;
	}

	// 在这里定义会出错
	public boolean getFullScr() {

		fullScr = PreferenceManager.getDefaultSharedPreferences(context);
		return playModSettings.getBoolean("if_hide_acBar_perf", false);

	}

	public String getSplashText() {

		splashText = PreferenceManager.getDefaultSharedPreferences(context);
		return playModSettings.getString("flash_text_perf", "音乐改变人生");

	}
}
