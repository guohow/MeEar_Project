package android.guohow.melody.listener;

import android.guohow.melody.boradcast.BoradCastManager;
import android.guohow.melody.constant.Constant;
import android.view.View;
import android.view.View.OnClickListener;

public class NextButtonListener implements OnClickListener {

	@Override
	public void onClick(View arg0) {
		if (firstPlayConfig()) {
			BoradCastManager.sentNextBoradCast();
		} else {
			BoradCastManager.sentFirstPlayedBoradCast();
		}

	}

	private boolean firstPlayConfig() {

		return Constant.ERER_PLAYED;
	}

}
