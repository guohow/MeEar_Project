package android.guohow.melody.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.guohow.melody.R;
import android.guohow.melody.constant.Constant;
import android.guohow.melody.database.QuerTools;
import android.guohow.melody.database.SQLHelper;
import android.guohow.melody.listener.ItemClickListener;
import android.guohow.melody.listener.ModButtonListener;
import android.guohow.melody.listener.NextButtonListener;
import android.guohow.melody.listener.PlayButtonListener;
import android.guohow.melody.listener.PreviousButtonListener;
import android.guohow.melody.listview.ListViewInitUtils;
import android.guohow.melody.listview.SideBar;
import android.guohow.melody.monitor.BtnUIMonitor;
import android.guohow.melody.settings.ThemeManager;
import android.guohow.melody.utils.ActionBarUtils;
import android.guohow.melody.view.TopToast;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FavouriteFragment extends Fragment {

	public View mRootView;
	protected Context mContext;
	Button btn_play, btn_next, btn_previous, btn_playMod, btn_fav;

	private TextView bottomInfo;
	private ImageView artImage;
	private ListView listView;
	private SideBar indexBar;
	private TextView mDialogText;
	RelativeLayout layout;
	private List<HashMap<String, Object>> trackList;
	int _index;
	private static SQLHelper dbHelper;
	public static SQLiteDatabase db;
	BroadcastReceiver mBroadcastReceiver;

	public FavouriteFragment() {
		super();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity.getApplicationContext();
		Constant.context = mContext;
		new ActionBarUtils().initActionBar(this, mContext, 2);

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub

		// getActivity().getMenuInflater().inflate(R.menu.ac_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.favourite, container, false);
		initButton();
		initListView();
		initListItemClickListener();
		initItemLongPressedControler();
		initBoradcastReceiver();
		new ThemeManager(mContext, listView, bottomInfo, layout, mRootView);
		return mRootView;
	}

	@Override
	public void onDestroy() {
		mContext.unregisterReceiver(mBroadcastReceiver);
		super.onDestroy();
	}

	private void initButton() {
		btn_play = (Button) mRootView.findViewById(R.id.playBtn);
		btn_next = (Button) mRootView.findViewById(R.id.nextBtn);
		btn_previous = (Button) mRootView.findViewById(R.id.previousBtn);
		btn_playMod = (Button) mRootView.findViewById(R.id.mod);

		btn_play.setOnClickListener(new PlayButtonListener());
		btn_next.setOnClickListener(new NextButtonListener());
		btn_previous.setOnClickListener(new PreviousButtonListener());
		btn_playMod.setOnClickListener(new ModButtonListener());
		bottomInfo = (TextView) mRootView.findViewById(R.id.infoText);
		artImage = (ImageView) mRootView.findViewById(R.id.artIcon);
		// 设置专辑头像的点击事件
		artImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final Intent intent = new Intent();
				intent.setClass(getActivity(), PlayingActivity.class);
				startActivity(intent);
			}
		});

		new BtnUIMonitor(btn_play, btn_previous, btn_next, btn_playMod, null,
				null, bottomInfo, artImage, null);
	}

	private void initListView() {

		listView = (ListView) mRootView.findViewById(R.id.listView);
		bottomInfo = (TextView) mRootView.findViewById(R.id.infoText);
		artImage = (ImageView) mRootView.findViewById(R.id.artIcon);
		indexBar = (SideBar) mRootView.findViewById(R.id.slideBar);
		mDialogText = (TextView) mRootView.findViewById(R.id.letter);
		// 获取顶部layout，以便设置主题颜色，放在这里好了:)
		layout = (RelativeLayout) mRootView.findViewById(R.id.topLayout_f);

		trackList = new ArrayList<HashMap<String, Object>>();
		trackList = QuerTools.createAndCursorTable(mContext, trackList);
		System.out.println(trackList.size());
		bottomInfo.setText("收藏队列:" + trackList.size());
		initListItemClickListener();
		// Constant.trackList = trackList;// 给constant传值
		new ListViewInitUtils(mContext, listView, indexBar, mDialogText,
				trackList);

	}

	private void initListItemClickListener() {

		listView.setOnItemClickListener(new ItemClickListener(trackList));
	}

	private void initItemLongPressedControler() {
		listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View arg1,
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				_index = ((AdapterContextMenuInfo) menuInfo).position;// 获取menu点击项的position

				menu.setHeaderIcon(R.drawable.icon_share_sns_music_circle);
				menu.setHeaderTitle(trackList.get(_index).get("title")
						.toString());

				menu.add(0, 6, 0, "移出收藏");
				menu.add(0, 9, 0, "分享");
			}
		});
	}

	// 长按菜单响应
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case 6:

			FavouriteFragment.dbHelper = new SQLHelper(mContext,
					Constant.DB_NAME, null, Constant.DB_VERSION);
			// SQLHelper.TABLE_NAME 表名
			FavouriteFragment.db = FavouriteFragment.dbHelper
					.getWritableDatabase(); // 自动调用SQLiteHelper.OnCreate()
			FavouriteFragment.db
					.delete(Constant.TABLE_NAME,
							"title" + "=?",
							new String[] { (String) trackList.get(_index).get(
									"title") });
			initListView();
			TopToast.makeText(mContext, "已移除", Toast.LENGTH_SHORT).show();
			break;

		case 9:
			final Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
			intent.putExtra(Intent.EXTRA_TEXT, "这首歌挺好听，推荐给你！歌名是："
					+ trackList.get(_index).get("title") + ",一定要听哦！ 来自:Melody");
			startActivity(Intent.createChooser(intent, "分享到"));

			break;

		default:
			break;
		}

		return super.onContextItemSelected(item);

	}

	private void registerBoradcastReceiver() {
		// 先初始化 后注册！！
		final IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction(Constant.ACTION_FAVOURITE_LIST_UPDATE);

		// 注册广播
		mContext.registerReceiver(mBroadcastReceiver, myIntentFilter);
	}

	private void initBoradcastReceiver() {

		mBroadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				final String action = intent.getAction();
				// 分析广播
				if (action.equals(Constant.ACTION_FAVOURITE_LIST_UPDATE)) {
					initListView();
					System.out.println("收藏列表-收到刷新列表");

				}

			}

		};
		registerBoradcastReceiver();
	}

}
