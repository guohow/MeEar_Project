package android.guohow.melody.listener;

import android.guohow.melody.boradcast.BoradCastManager;
import android.guohow.melody.constant.Constant;
import android.view.View;
import android.view.View.OnClickListener;

public class ModButtonListener implements OnClickListener {

	@Override
	public void onClick(View arg0) {
		switch (getPlayMod()) {
		case 1:
			BoradCastManager.sentModChangedTo_2();// ˳��
			break;
		case 2:
			BoradCastManager.sentModChangedTo_3();// ����
			break;
		case 3:
			BoradCastManager.sentModChangedTo_4();// ���
			break;
		case 4:
			BoradCastManager.sentModChangedTo_1();// ȫ��
			break;

		}

	}

	private int getPlayMod() {

		return Constant.PLAYMOD;
	}

}
