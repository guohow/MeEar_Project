package android.guohow.melody.listener;

import java.util.HashMap;
import java.util.List;

import android.guohow.melody.boradcast.BoradCastManager;
import android.guohow.melody.constant.Constant;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ItemClickListener implements OnItemClickListener {

	private final List<HashMap<String, Object>> trackList;

	public ItemClickListener(List<HashMap<String, Object>> trackList) {
		this.trackList = trackList;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		Constant.trackList = trackList;
		Constant.CURRENT = position;
		BoradCastManager.sentNormalPlayBoradCast();

	}

}
