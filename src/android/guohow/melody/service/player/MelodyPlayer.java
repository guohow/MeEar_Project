/**
 * 
 */
package android.guohow.melody.service.player;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.guohow.melody.boradcast.BoradCastManager;
import android.guohow.melody.constant.Constant;
import android.media.MediaPlayer;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * @author Administrator 自定义的player类
 * 
 */
public class MelodyPlayer extends MediaPlayer {
	public static MelodyPlayer player = new MelodyPlayer();
	public static SeekBar seekBar;

	public MelodyPlayer() {
	}

	// 1默认顺序循环 2全部循环3单曲循环4随机播放
	// 获取下一首歌的current
	public static int getNextCursor() {
		int next = 0;
		switch (MelodyPlayer.getPlayMod()) {
		case 1:
			if (Constant.CURRENT != Constant.trackList.size() - 1) {
				next = Constant.CURRENT + 1 % Constant.trackList.size();
			} else {
				MelodyPlayer.player.stop();
			}
			break;
		case 2:
			if (Constant.CURRENT != Constant.trackList.size() - 1) {
				next = Constant.CURRENT + 1 % Constant.trackList.size();
			} else {
				next = 0;
			}
			break;
		case 3:
			next = Constant.CURRENT;
			break;
		case 4:
			next = new Random().nextInt(Constant.trackList.size());
			break;

		}

		return next;
	}

	public static int getPreviousCursor() {
		int previous = 0;
		switch (MelodyPlayer.getPlayMod()) {
		case 0:
			previous = Constant.CURRENT - 1 % Constant.trackList.size();
			if (previous < 0) {
				previous = Constant.trackList.size() - 1;
			}
			break;
		case 1:
			previous = Constant.CURRENT - 1 % Constant.trackList.size();
			if (previous < 0) {
				previous = Constant.trackList.size() - 1;
			}
			break;
		case 2:
			previous = Constant.CURRENT;
			break;
		case 3:
			previous = new Random().nextInt(Constant.trackList.size());
			break;
		}
		return previous;
	}

	private static int getPlayMod() {
		return Constant.PLAYMOD;

	}

	public static void mPause() {
		if (MelodyPlayer.player != null) {
			MelodyPlayer.player.pause();
		}
	}

	public static void mResume() {
		if (MelodyPlayer.player != null) {
			MelodyPlayer.player.start();
		}
	}

	public static void mStop() {

		if (MelodyPlayer.player != null) {
			MelodyPlayer.player.stop();
		}
	}

	public static void next() {

		Constant.CURRENT = MelodyPlayer.getNextCursor();
		MelodyPlayer.player.stop();
		MelodyPlayer.player.play();

	}

	public static void previous() {
		Constant.CURRENT = MelodyPlayer.getPreviousCursor();
		MelodyPlayer.player.stop();
		MelodyPlayer.player.play();

	}

	// 该方法适用于用户未选择任何列表
	public static void startPlaying(int current,
			List<HashMap<String, Object>> trackList) {
		Constant.CURRENT = current;
		Constant.trackList = trackList;// 查询数据库
		MelodyPlayer.player.play();
	}

	public static void mPlay() {
		MelodyPlayer.player.play();
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return super.getDuration();
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return super.isPlaying();
	}

	@Override
	public void pause() throws IllegalStateException {
		// TODO Auto-generated method stub

		super.pause();
	}

	public void play() {
		MelodyPlayer.player.reset();
		// 设置播放资源
		try {
			MelodyPlayer.player.setDataSource((String) Constant.trackList.get(
					Constant.CURRENT).get("url"));
			MelodyPlayer.player.prepare();
		} catch (final IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MelodyPlayer.player.start();
		// 进度条监听
		MelodyPlayer.player.seekBarMonit();
		// 播放器监听器
		MelodyPlayer.player.setOnCompletionListener(new MyPlayerListener());

	}

	private void seekBarMonit() {
		// 设置进度条长度

		if (MelodyPlayer.seekBar != null) {
			MelodyPlayer.seekBar.setMax(MelodyPlayer.player.getDuration());
			MelodyPlayer.seekBar
					.setOnSeekBarChangeListener(new MySeekBarListener());
		}
	}

	@Override
	public void start() throws IllegalStateException {
		// TODO Auto-generated method stub
		super.start();
	}

	@Override
	public void stop() throws IllegalStateException {
		// TODO Auto-generated method stub
		super.stop();
	}

	private final class MyPlayerListener implements OnCompletionListener {
		// 歌曲播放完后自动播放下一首歌曲

		@Override
		public void onCompletion(MediaPlayer mp) {
			try {
				MelodyPlayer.next();
				BoradCastManager.sentSongChangedBoradCast();
			} catch (final IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public class MySeekBarListener implements OnSeekBarChangeListener {
		// 移动触发
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
		}

		// 起始触发
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		// 结束触发
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			MelodyPlayer.player.seekTo(seekBar.getProgress());

		}
	}

}
