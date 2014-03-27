package android.guohow.melody.boradcast;

import android.content.Intent;
import android.guohow.melody.constant.Constant;

/**
 * @author guohow 管理系统的广播发送
 * 
 */
public class BoradCastManager {

	public static void sentFirstPlayedBoradCast() {

		final Intent mIntent = new Intent(Constant.ACTION_SONG_FIRST_PLAY);
		mIntent.putExtra("guohow", "FIRST，来自BrardCastManager的广播");
		System.out.println("Manager发送第一次播放的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sentNormalPlayBoradCast() {

		final Intent mIntent = new Intent(Constant.ACTION_SONG_NORMAL_PLAY);
		mIntent.putExtra("guohow", "正常，来自BrardCastManager的广播");
		System.out.println("Manager发送按下列表播放的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sentSongChangedBoradCast() {

		final Intent mIntent = new Intent(Constant.ACTION_SONG_CHANGED);
		mIntent.putExtra("guohow", "CHANGED，来自BrardCastManager的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sentNextBoradCast() {

		final Intent mIntent = new Intent(Constant.ACTION_SONG_NEXT);
		mIntent.putExtra("guohow", "NEXT，来自BrardCastManager的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sentSongPreviousBoradCast() {

		final Intent mIntent = new Intent(Constant.ACTION_SONG_PREVIOUS);
		mIntent.putExtra("guohow", "PREVIOUS，来自BrardCastManager的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sentSongPauseBoradCast() {

		final Intent mIntent = new Intent(Constant.ACTION_SONG_PAUSE);
		mIntent.putExtra("guohow", "PAUSE，来自BrardCastManager的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sentSongResumeBoradCast() {

		final Intent mIntent = new Intent(Constant.ACTION_SONG_RESUME);
		mIntent.putExtra("guohow", "RESUME，来自BrardCastManager的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sentSongStopBoradCast() {

		final Intent mIntent = new Intent(Constant.ACTION_SONG_STOP);
		mIntent.putExtra("guohow", "STOP，来自BrardCastManager的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sentModChangedTo_1() {

		final Intent mIntent = new Intent(Constant.ACTION_MOD_1);
		mIntent.putExtra("guohow", "1，来自BrardCastManager的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sentModChangedTo_2() {

		final Intent mIntent = new Intent(Constant.ACTION_MOD_2);
		mIntent.putExtra("guohow", "2，来自BrardCastManager的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sentModChangedTo_3() {

		final Intent mIntent = new Intent(Constant.ACTION_MOD_3);
		mIntent.putExtra("guohow", "3，来自BrardCastManager的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sentModChangedTo_4() {

		final Intent mIntent = new Intent(Constant.ACTION_MOD_4);
		mIntent.putExtra("guohow", "4，来自BrardCastManager的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sendFavourListUpdateBoradCast() {

		final Intent mIntent = new Intent(Constant.ACTION_FAVOURITE_LIST_UPDATE);
		mIntent.putExtra("guohow", "fav_list_update，来自BrardCastManager的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

	public static void sendFlipEndBoradCast() {

		final Intent mIntent = new Intent(Constant.ACTION_FLIP_END);
		mIntent.putExtra("guohow", "FLIP END，来自BrardCastManager的广播");

		// 发送广播
		Constant.context.sendBroadcast(mIntent);
	}

}
