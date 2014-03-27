package android.guohow.melody.fragment;

import java.util.ArrayList;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.guohow.melody.R;
import android.guohow.melody.boradcast.BoradCastManager;
import android.guohow.melody.constant.Constant;
import android.guohow.melody.monitor.BtnUIMonitor;
import android.guohow.melody.utils.ArtWorkUtils;
import android.guohow.melody.view.TopToast;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MelodyApplication extends SlidingFragmentActivity {

	BroadcastReceiver br1;

	BroadcastReceiver br2;
	NotificationManager mNotificationManager;
	BroadcastReceiver mBroadcastReceiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getIfFullScreen())

		{
			// 全屏
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		System.out.println(getIfFullScreen()
				+ "++++++++++++++++++++++++++++++++++++++++++++>>>>>>>>>>>>>");
		initSlidingMenu(savedInstanceState);

		initView();
		initReceiver();
		initBoradcastReceiver();
		Constant.context = this;
	}

	public boolean getIfFullScreen() {

		final SharedPreferences spf = PreferenceManager
				.getDefaultSharedPreferences(this);

		return spf.getBoolean("if_hide_acBar_perf", false);
	}

	/**
	 * 初始化滑动菜单
	 */
	/**
	 * 
	 */
	private void initSlidingMenu(Bundle savedInstanceState) {

		// 设置滑动菜单打开后的视图界面
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new MelodyMenuFragment()).commit();

		// 设置滑动菜单的属性值
		final SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.85f);// 渐入溅出的效果
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		final ViewPager vp = new ViewPager(this);
		vp.setId("VP".hashCode());
		vp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
		setContentView(vp);

		vp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_FULLSCREEN);
					break;
				default:
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_MARGIN);
					break;
				}
			}

		});

		vp.setCurrentItem(0);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	}

	public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		private final ArrayList<Fragment> mFragments;

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragments = new ArrayList<Fragment>();
			// 添加页面
			mFragments.add(new LocalFragment());
			mFragments.add(new FavouriteFragment());
			// mFragments.add(new RecentFragment());

		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}

	}

	public void updateNoti(String currentTitle, String artist) {

		final Intent resultIntent = new Intent(this, PlayingActivity.class);
		final TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack
		stackBuilder.addParentStack(PlayingActivity.class);
		// Adds the Intent to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// Sets an ID for the notification, so it can be updated
		final int notifyID = 1;
		final NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
				this).setContentTitle(currentTitle).setContentText(artist)
				.setSmallIcon(R.drawable.ic_launcher).setLargeIcon(getIcon())
				.setAutoCancel(true).setOngoing(true);

		// Start of a loop that processes data and then notifies the user...
		// Because the ID remains unchanged, the existing notification is
		// updated.

		mNotificationManager.notify(notifyID, mNotifyBuilder.build());
	}

	// 获取专辑图片，交给通知栏
	private Bitmap getIcon() {
		Bitmap icon = null;
		icon = ArtWorkUtils.getContent();
		return icon;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0) {
			toggle();
			return true;
		}

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			BoradCastManager.sentSongPauseBoradCast();
			TopToast.makeText(this, "播放已暂停", Toast.LENGTH_SHORT).show();
			finish();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void registerBoradcastReceiver() {
		// 先初始化 后注册！！
		final IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction(Constant.ACTION_SONG_CHANGED);
		myIntentFilter.addAction(Constant.ACTION_SONG_FIRST_PLAY);
		myIntentFilter.addAction(Constant.ACTION_SONG_NORMAL_PLAY);
		myIntentFilter.addAction(Constant.ACTION_SONG_NEXT);
		myIntentFilter.addAction(Constant.ACTION_SONG_PAUSE);
		myIntentFilter.addAction(Constant.ACTION_SONG_PREVIOUS);
		myIntentFilter.addAction(Constant.ACTION_SONG_RESUME);
		myIntentFilter.addAction(Constant.ACTION_SONG_STATE_CHANGED);
		myIntentFilter.addAction(Constant.ACTION_SONG_STOP);

		// 注册广播
		registerReceiver(mBroadcastReceiver, myIntentFilter);
	}

	private void initBoradcastReceiver() {

		mBroadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				final String action = intent.getAction();

				if (action.equals(Constant.ACTION_SONG_CHANGED)
						|| action.equals(Constant.ACTION_SONG_FIRST_PLAY)
						|| action.equals(Constant.ACTION_SONG_NEXT)
						|| action.equals(Constant.ACTION_SONG_NORMAL_PLAY)
						|| action.equals(Constant.ACTION_SONG_PAUSE)
						|| action.equals(Constant.ACTION_SONG_PREVIOUS)
						|| action.equals(Constant.ACTION_SONG_RESUME)) {
					BtnUIMonitor.updateCurrent();
					updateNoti(Constant.current_title, Constant.current_artist);
					System.out
							.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>APP收到广播");
				}
			}

		};
		registerBoradcastReceiver();
	}

	private void initReceiver() {

		// 意图过滤器
		final IntentFilter filter = new IntentFilter();
		final IntentFilter filter2 = new IntentFilter();
		br1 = new PhoneListener();
		// 耳机监听
		br2 = new HeadsetPlugReceiver();

		filter2.addAction("android.intent.action.HEADSET_PLUG");
		// 播出电话暂停音乐播放
		filter.addAction("android.intent.action.NEW_OUTGOING_CALL");

		registerReceiver(br1, filter);
		registerReceiver(br2, filter2);

		// 创建一个电话服务
		final TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		// 监听电话状态，接电话时停止播放
		manager.listen(new MyPhoneStateListener(),
				PhoneStateListener.LISTEN_CALL_STATE);
	}

	private class HeadsetPlugReceiver extends BroadcastReceiver {

		private static final String TAG = "HeadsetPlugReceiver";

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.hasExtra("state")) {
				if (intent.getIntExtra("state", 0) == 0) {
					BoradCastManager.sentSongPauseBoradCast();
					TopToast.makeText(context, "耳机未连接", Toast.LENGTH_SHORT)
							.show();
					Log.i(HeadsetPlugReceiver.TAG, "拔出耳机");

				} else if (intent.getIntExtra("state", 0) == 1) {
					TopToast.makeText(context, "耳机已连接", Toast.LENGTH_SHORT)
							.show();
					Log.i(HeadsetPlugReceiver.TAG, "插入耳机");
				}
			}

		}

	}

	private final class MyPhoneStateListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			BoradCastManager.sentSongPauseBoradCast();
		}
	}

	private final class PhoneListener extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			BoradCastManager.sentSongPauseBoradCast();
		}
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(br1);
		unregisterReceiver(br2);
		unregisterReceiver(mBroadcastReceiver);
		super.onDestroy();
	}

}
