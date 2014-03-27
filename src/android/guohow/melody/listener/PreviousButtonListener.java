package android.guohow.melody.listener;

import android.guohow.melody.boradcast.BoradCastManager;
import android.guohow.melody.constant.Constant;
import android.view.View;
import android.view.View.OnClickListener;

public class PreviousButtonListener implements OnClickListener {

	@Override
	public void onClick(View arg0) {// true:已经播放过
		if (firstPlayConfig()) {

			BoradCastManager.sentSongPreviousBoradCast();
		} else {

			BoradCastManager.sentFirstPlayedBoradCast();
		}

	}

	private boolean firstPlayConfig() {

		return Constant.ERER_PLAYED;
	}

}
