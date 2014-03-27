package android.guohow.melody.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.guohow.melody.utils.Gb2Alpha;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewInitUtils {

	private ListView mListView;
	private SideBar indexBar;
	private WindowManager mWindowManager;
	private TextView mDialogText;
	private Context context;
	private List<HashMap<String, Object>> trackList;

	public ListViewInitUtils(Context context, ListView mListView,
			SideBar indexBar, TextView mDialogText,
			List<HashMap<String, Object>> trackList) {
		this.context = context;
		this.mListView = mListView;
		this.indexBar = indexBar;
		this.mDialogText = mDialogText;
		this.trackList = trackList;
		init();

	}

	public void init() {

		mDialogText.setVisibility(View.INVISIBLE);
		// mWindowManager = (WindowManager) context
		// .getSystemService(Context.WINDOW_SERVICE);
		// WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
		// LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
		// WindowManager.LayoutParams.TYPE_APPLICATION,
		// WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
		// | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
		// PixelFormat.TRANSLUCENT);
		// mWindowManager.addView(mDialogText, lp);
		indexBar.setTextView(mDialogText);
		// ��ʼ������
		List<Content> list = new ArrayList<Content>();
		Gb2Alpha tools = new Gb2Alpha();
		String song_title;
		String letter;
		String artist;
		String duration;
		String url;
		for (int i = 0; i < trackList.size(); i++) {
			song_title = trackList.get(i).get("title").toString();
			artist = trackList.get(i).get("artist").toString();
			duration = trackList.get(i).get("duration").toString();// ���г�����ȡ������
			url = trackList.get(i).get("url").toString();
			letter = tools.String2Alpha(song_title);
			Content c = new Content(letter, song_title, artist, duration, url);
			list.add(c);
		}
		// ����a-z��������
		// Collections.sort(list, new PinyinComparator());
		// ʵ�����Զ�������������
		MyAdapter adapter = new MyAdapter(context, list);
		// ΪlistView��������
		mListView.setAdapter(adapter);
		// ����SideBar��ListView����ʵ�ֵ��a-z������һ�����ж�λ
		// indexBar.setListView(mListView);
	}

}
