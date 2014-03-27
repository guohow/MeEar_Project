package android.guohow.melody.fragment;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.guohow.melody.R;
import android.guohow.melody.boradcast.BoradCastManager;
import android.guohow.melody.constant.Constant;
import android.guohow.melody.database.QuerTools;
import android.guohow.melody.database.SQLHelper;
import android.guohow.melody.listener.ItemClickListener;
import android.guohow.melody.listener.NextButtonListener;
import android.guohow.melody.listener.PlayButtonListener;
import android.guohow.melody.listener.PreviousButtonListener;
import android.guohow.melody.listview.ListViewInitUtils;
import android.guohow.melody.listview.SideBar;
import android.guohow.melody.monitor.BtnUIMonitor;
import android.guohow.melody.settings.ThemeManager;
import android.guohow.melody.utils.ActionBarUtils;
import android.guohow.melody.utils.Mp3Bean;
import android.guohow.melody.utils.MusicUtils;
import android.guohow.melody.view.TopToast;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import fr.castorflex.android.flipimageview.library.FlipImageView;

public class LocalFragment extends Fragment implements
		FlipImageView.OnFlipListener {

	public View mRootView;
	protected Context mContext;
	private Button btn_play, btn_next, btn_previous, btn_playMod;
	private FlipImageView btn_fav;
	private TextView bottomInfo;
	private ImageView artImage;
	private ListView listView;
	private SideBar indexBar;
	private TextView mDialogText;
	RelativeLayout layout;
	private List<HashMap<String, Object>> trackList;
	private static SQLHelper dbHelper;
	public static SQLiteDatabase db;
	int _index;

	public LocalFragment() {
		super();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity.getApplicationContext();
		Constant.context = mContext;
		new ActionBarUtils().initActionBar(this, mContext, 1);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.local, container, false);
		// ���� ����
		// mRootView.setBackgroundResource(R.drawable.girl);
		initButton();
		initListView(inflater, container);
		initListItemClickListener();
		initItemLongPressedControler();
		// ��ʼ������:)
		new ThemeManager(mContext, listView, bottomInfo, layout, mRootView);
		return mRootView;
	}

	private void initButton() {
		btn_play = (Button) mRootView.findViewById(R.id.playBtn);
		btn_next = (Button) mRootView.findViewById(R.id.nextBtn);
		btn_previous = (Button) mRootView.findViewById(R.id.previousBtn);
		btn_fav = (FlipImageView) mRootView.findViewById(R.id.btn_set_fav_a);
		// ����
		btn_fav.setOnFlipListener(this);

		btn_play.setOnClickListener(new PlayButtonListener());
		btn_next.setOnClickListener(new NextButtonListener());
		btn_previous.setOnClickListener(new PreviousButtonListener());
		// btn_playMod.setOnClickListener(new ModButtonListener());
		bottomInfo = (TextView) mRootView.findViewById(R.id.infoText);
		artImage = (ImageView) mRootView.findViewById(R.id.artIcon);
		new BtnUIMonitor(btn_play, btn_previous, btn_next, btn_playMod, null,
				btn_fav, bottomInfo, artImage, null);
	}

	private void initListView(LayoutInflater inflater, ViewGroup container) {

		listView = (ListView) mRootView.findViewById(R.id.listView);
		bottomInfo = (TextView) mRootView.findViewById(R.id.infoText);
		artImage = (ImageView) mRootView.findViewById(R.id.artIcon);
		indexBar = (SideBar) mRootView.findViewById(R.id.slideBar);
		mDialogText = (TextView) mRootView.findViewById(R.id.letter);
		// ��ȡ����layout���Ա�����������ɫ�������������:)
		layout = (RelativeLayout) mRootView.findViewById(R.id.topLayout);
		// ����ר��ͷ��ĵ���¼�
		artImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final Intent intent = new Intent();
				intent.setClass(getActivity(), PlayingActivity.class);
				startActivity(intent);
			}
		});

		System.out.println(mDialogText);
		final Cursor cursor = mContext.getContentResolver().query(
				// ��ȡCURSOR
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		final List<Mp3Bean> mp3InfoList = MusicUtils.getMp3Infos(cursor,
				mContext);
		// ����a-z��������
		// Collections.sort(mp3InfoList, new PinyinComparator());
		MusicUtils.listUpdate(mp3InfoList);
		// ����trackList
		trackList = MusicUtils.mp3list;
		bottomInfo.setText("���ض���:" + trackList.size());
		System.out.println(trackList.size());
		// Constant.trackList = trackList;// ��constant��ֵ
		new ListViewInitUtils(mContext, listView, indexBar, mDialogText,
				trackList);

	}

	private void initListItemClickListener() {

		listView.setOnItemClickListener(new ItemClickListener(trackList));
	}

	private void initItemLongPressedControler() {
		listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View arg1,
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				_index = ((AdapterContextMenuInfo) menuInfo).position;// ��ȡmenu������position
				menu.setHeaderIcon(R.drawable.icon_share_sns_music_circle);
				menu.setHeaderTitle(trackList.get(_index).get("title")
						.toString());
				menu.add(0, 0, 0, "��ӵ��ղ�");
				menu.add(0, 3, 0, "����");

			}
		});

	}

	// �����˵���Ӧ����
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case 0:
			// ��ʼ��&�������ݿ�
			LocalFragment.dbHelper = new SQLHelper(mRootView.getContext(),
					Constant.DB_NAME, null, Constant.DB_VERSION);
			// ������
			LocalFragment.db = LocalFragment.dbHelper.getWritableDatabase(); // �Զ�����SQLiteHelper.OnCreate()
			// ��ӵ���ϲ��
			final ContentValues values = new ContentValues();
			values.put("title", (String) trackList.get(_index).get("title"));
			values.put("artist", (String) trackList.get(_index).get("artist"));
			values.put("url", (String) trackList.get(_index).get("url"));
			values.put("duration",
					(String) trackList.get(_index).get("duration"));

			LocalFragment.db.insert(Constant.TABLE_NAME, null, values);
			BoradCastManager.sendFavourListUpdateBoradCast();
			TopToast.makeText(mContext, "�����", Toast.LENGTH_SHORT).show();
			break;

		case 3:
			final Intent intent = new Intent(Intent.ACTION_SEND);

			intent.setType("text/plain");
			// intent.setType();//����������ʲô��

			intent.putExtra(Intent.EXTRA_SUBJECT, "����");
			intent.putExtra(Intent.EXTRA_TEXT, "���׸�ͦ�������Ƽ����㣡�����ǣ�"
					+ trackList.get(_index).get("title") + ",һ��Ҫ��Ŷ�� ����:Melody");
			startActivity(Intent.createChooser(intent, "����"));
			break;

		default:
			break;
		}

		return super.onContextItemSelected(item);

	}

	@Override
	public void onClick(FlipImageView view) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFlipStart(FlipImageView view) {
		// TODO Auto-generated method stub
		BoradCastManager.sendFlipEndBoradCast();
		final String title = Constant.current_title;
		final String artist = Constant.current_artist;
		final String url = Constant.current_url;
		final String duration = Constant.current_duration;
		QuerTools.queryTable(mContext, title, artist, url, duration);
		BoradCastManager.sendFavourListUpdateBoradCast();

	}

	@Override
	public void onFlipEnd(FlipImageView view) {
		// TODO Auto-generated method stub

	}

}
