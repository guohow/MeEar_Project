package android.guohow.melody.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.guohow.melody.constant.Constant;
import android.guohow.melody.service.player.MelodyPlayer;
import android.os.Binder;
import android.os.IBinder;

/**
 * @author guohow
 *系统的服务，包括对广播的处理
 *
 */
public class PlayTrackService extends Service {

	BroadcastReceiver mBroadcastReceiver;
	private final IBinder binder = new PlayTrackService.MusicBinder();

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return binder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(mBroadcastReceiver);
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		initBoradcastReceiver();// 初始化广播
		System.out
				.println("SERVICE STARTED===================>>>>>>>>>>>>>>>>>>>>>>");
		return super.onStartCommand(intent, flags, startId);
	}

	// 接受广播 1为制定列表和curr 2为未指定任何列表和curr
	private void startPlaying(int command) {
		switch (command) {
		case 1:
			MelodyPlayer.player.play();
			break;
		case 0:

			if (Constant.trackList.size() > 0) {
				MelodyPlayer.startPlaying(0, Constant.trackList);
			}
		}
	}

	private void playNextTrack() {

		MelodyPlayer.next();
	}

	private void playPreviousTrack() {

		MelodyPlayer.previous();
	}

	private void pauseTrack() {

		MelodyPlayer.mPause();
	}

	private void resumeTrack() {

		MelodyPlayer.mResume();
	}

	private void stopTrack() {

		MelodyPlayer.player.stop();
		// MelodyPlayer. player=null;
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
		myIntentFilter.addAction(Constant.ACTION_MOD_1);
		myIntentFilter.addAction(Constant.ACTION_MOD_2);
		myIntentFilter.addAction(Constant.ACTION_MOD_3);
		myIntentFilter.addAction(Constant.ACTION_MOD_4);
		// 注册广播
		registerReceiver(mBroadcastReceiver, myIntentFilter);
	}

	private void initBoradcastReceiver() {

		mBroadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				final String action = intent.getAction();
				// 分析广播
				if (action.equals(Constant.ACTION_SONG_FIRST_PLAY)) {
					// startPlaying(0);
					// Constant.PLAYING_STATE = 1;
					// Constant.ERER_PLAYED = true;
					System.out.println("服务器收到第一次播放的广播");

				}
				if (action.equals(Constant.ACTION_SONG_NORMAL_PLAY)) {
					startPlaying(1);
					Constant.PLAYING_STATE = 1;
					Constant.ERER_PLAYED = true;
					System.out.println("服务器收到按下列表播放的广播");

				}
				if (action.equals(Constant.ACTION_SONG_CHANGED)) {

				}
				if (action.equals(Constant.ACTION_SONG_NEXT)) {
					playNextTrack();
					Constant.PLAYING_STATE = 1;
					Constant.ERER_PLAYED = true;
					System.out.println("服务器收到NEXT播放的广播");

				}
				if (action.equals(Constant.ACTION_SONG_PREVIOUS)) {
					playPreviousTrack();
					Constant.PLAYING_STATE = 1;
					System.out.println("服务器收到PRE播放的广播");

				}
				if (action.equals(Constant.ACTION_SONG_PAUSE)) {
					pauseTrack();
					Constant.PLAYING_STATE = 0;
					System.out.println("服务器收到PAUSE播放的广播");

				}
				if (action.equals(Constant.ACTION_SONG_RESUME)) {
					resumeTrack();
					Constant.PLAYING_STATE = 1;
					System.out.println("服务器收到RESUME播放的广播");

				}
				if (action.equals(Constant.ACTION_SONG_STOP)) {
					stopTrack();
					Constant.PLAYING_STATE = 0;
					System.out.println("服务器收到STOP播放的广播");

				}
				if (action.equals(Constant.ACTION_MOD_1)) {
					Constant.PLAYMOD = 1;
					System.out.println("服务器收到_1播放的广播");

				}
				if (action.equals(Constant.ACTION_MOD_2)) {
					Constant.PLAYMOD = 2;
					System.out.println("服务器收到2播放的广播");

				}
				if (action.equals(Constant.ACTION_MOD_3)) {
					Constant.PLAYMOD = 3;
					System.out.println("服务器收到3播放的广播");

				}
				if (action.equals(Constant.ACTION_MOD_4)) {
					Constant.PLAYMOD = 4;
					System.out.println("服务器收到4播放的广播");

				}
			}

		};
		registerBoradcastReceiver();
	}

	// 返回music service
	public class MusicBinder extends Binder {
		PlayTrackService getService() {

			return PlayTrackService.this;
		}
	}

}
