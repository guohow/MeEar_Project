/**
 * 
 */
package android.guohow.melody.listener;

import android.guohow.melody.boradcast.BoradCastManager;
import android.guohow.melody.constant.Constant;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author guohow
 * 
 */
public class PlayButtonListener implements OnClickListener {

	@Override
	public void onClick(View arg0) {
		if (firstPlayConfig()) {
			switch (getPlayState()) {
			case 0:
				BoradCastManager.sentSongResumeBoradCast();// ���͹㲥��ͣ
				break;
			case 1:
				BoradCastManager.sentSongPauseBoradCast();
				break;

			}
		} else {// ���͵�һ�β��ŵĹ㲥
		//	BoradCastManager.sentFirstPlayedBoradCast();
		}

	}

	private int getPlayState() {

		return Constant.PLAYING_STATE;
	}

	private boolean firstPlayConfig() {

		return Constant.ERER_PLAYED;
	}

}
