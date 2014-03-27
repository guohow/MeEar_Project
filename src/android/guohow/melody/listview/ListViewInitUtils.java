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
		// 初始化数据
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
			duration = trackList.get(i).get("duration").toString();// 这行出错，获取不到吗？
			url = trackList.get(i).get("url").toString();
			letter = tools.String2Alpha(song_title);
			Content c = new Content(letter, song_title, artist, duration, url);
			list.add(c);
		}
		// 根据a-z进行排序
		// Collections.sort(list, new PinyinComparator());
		// 实例化自定义内容适配类
		MyAdapter adapter = new MyAdapter(context, list);
		// 为listView设置适配
		mListView.setAdapter(adapter);
		// 设置SideBar的ListView内容实现点击a-z中任意一个进行定位
		// indexBar.setListView(mListView);
	}

}
