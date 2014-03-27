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
 *ϵͳ�ķ��񣬰����Թ㲥�Ĵ���
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
		initBoradcastReceiver();// ��ʼ���㲥
		System.out
				.println("SERVICE STARTED===================>>>>>>>>>>>>>>>>>>>>>>");
		return super.onStartCommand(intent, flags, startId);
	}

	// ���ܹ㲥 1Ϊ�ƶ��б��curr 2Ϊδָ���κ��б��curr
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
		// �ȳ�ʼ�� ��ע�ᣡ��
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
		// ע��㲥
		registerReceiver(mBroadcastReceiver, myIntentFilter);
	}

	private void initBoradcastReceiver() {

		mBroadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				final String action = intent.getAction();
				// �����㲥
				if (action.equals(Constant.ACTION_SONG_FIRST_PLAY)) {
					// startPlaying(0);
					// Constant.PLAYING_STATE = 1;
					// Constant.ERER_PLAYED = true;
					System.out.println("�������յ���һ�β��ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_SONG_NORMAL_PLAY)) {
					startPlaying(1);
					Constant.PLAYING_STATE = 1;
					Constant.ERER_PLAYED = true;
					System.out.println("�������յ������б��ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_SONG_CHANGED)) {

				}
				if (action.equals(Constant.ACTION_SONG_NEXT)) {
					playNextTrack();
					Constant.PLAYING_STATE = 1;
					Constant.ERER_PLAYED = true;
					System.out.println("�������յ�NEXT���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_SONG_PREVIOUS)) {
					playPreviousTrack();
					Constant.PLAYING_STATE = 1;
					System.out.println("�������յ�PRE���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_SONG_PAUSE)) {
					pauseTrack();
					Constant.PLAYING_STATE = 0;
					System.out.println("�������յ�PAUSE���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_SONG_RESUME)) {
					resumeTrack();
					Constant.PLAYING_STATE = 1;
					System.out.println("�������յ�RESUME���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_SONG_STOP)) {
					stopTrack();
					Constant.PLAYING_STATE = 0;
					System.out.println("�������յ�STOP���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_MOD_1)) {
					Constant.PLAYMOD = 1;
					System.out.println("�������յ�_1���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_MOD_2)) {
					Constant.PLAYMOD = 2;
					System.out.println("�������յ�2���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_MOD_3)) {
					Constant.PLAYMOD = 3;
					System.out.println("�������յ�3���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_MOD_4)) {
					Constant.PLAYMOD = 4;
					System.out.println("�������յ�4���ŵĹ㲥");

				}
			}

		};
		registerBoradcastReceiver();
	}

	// ����music service
	public class MusicBinder extends Binder {
		PlayTrackService getService() {

			return PlayTrackService.this;
		}
	}

}
