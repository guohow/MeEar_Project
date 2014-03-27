package android.guohow.melody.fragment;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.guohow.melody.R;
import android.guohow.melody.boradcast.BoradCastManager;
import android.guohow.melody.monitor.BtnUIMonitor;
import android.guohow.melody.service.PlayTrackService;
import android.guohow.melody.settings.DevelopersSettings;
import android.guohow.melody.settings.PersonalizeSettings;
import android.guohow.melody.settings.ThemeSettings;
import android.guohow.melody.utils.MenuUtils;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MelodyMenuFragment extends ListFragment {

	static List<HashMap<String, Object>> items;

	public static int icon_0, icon_1, icon_2, icon_3, icon_4;

	private Context mContext;

	public int getDrawable(int _id) {
		int icon_ = 0;
		switch (_id) {
		case 0:
			icon_ = R.drawable.img_menu_ing;
			break;
		case 1:
			icon_ = R.drawable.img_menu_theme;
			break;
		case 2:
			icon_ = R.drawable.img_about;
			break;
		case 3:
			icon_ = R.drawable.img_menu_per;
			break;
		case 4:
			icon_ = R.drawable.img_menu_help;
			break;

		}

		return icon_;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		final ListView listView = getListView();
		listView.setBackgroundResource(R.color.white);
		listView.setSelector(R.drawable.list_selecter_pink);
		listView.setKeepScreenOn(true);

		MelodyMenuFragment.items = new MenuUtils().getMenuItemList();
		final SimpleAdapter adapter = new SimpleAdapter(getActivity(),
				MelodyMenuFragment.items, R.layout.menuitem, new String[] {
						"icon", "menu_title" }, new int[] {
						R.id.icon_menu_item, R.id.mMTitle });

		setListAdapter(adapter);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		MelodyMenuFragment.icon_0 = getDrawable(0);
		MelodyMenuFragment.icon_1 = getDrawable(1);
		MelodyMenuFragment.icon_2 = getDrawable(2);
		MelodyMenuFragment.icon_3 = getDrawable(3);
		MelodyMenuFragment.icon_4 = getDrawable(4);
		mContext = activity.getApplicationContext();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {

		switch (position) {
		case 0:
			final Intent intent = new Intent();
			intent.setClass(getActivity(), PlayingActivity.class);
			startActivity(intent);
			break;

		case 1:
			final Intent intent2 = new Intent();
			intent2.setClass(getActivity(), ThemeSettings.class);
			startActivity(intent2);

			break;
		case 2:
			final Intent intent3 = new Intent();
			intent3.setClass(getActivity(), DevelopersSettings.class);
			startActivity(intent3);

			break;

		case 3:
			final Intent intent4 = new Intent();
			intent4.setClass(getActivity(), PersonalizeSettings.class);
			startActivity(intent4);
			break;
		case 4:
			final Intent service = new Intent();
			service.setClass(getActivity(), PlayTrackService.class);
			BoradCastManager.sentSongPauseBoradCast();
			BtnUIMonitor.unRBD(mContext);
			mContext.stopService(service);
			getActivity().finish();// 终止服务并退出activity
			break;

		}

	}

}
