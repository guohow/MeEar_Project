package android.guohow.melody.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.guohow.melody.R;
import android.guohow.melody.boradcast.BoradCastManager;
import android.guohow.melody.constant.Constant;
import android.guohow.melody.database.QuerTools;
import android.guohow.melody.listener.NextButtonListener;
import android.guohow.melody.listener.PlayButtonListener;
import android.guohow.melody.listener.PreviousButtonListener;
import android.guohow.melody.monitor.BtnUIMonitor;
import android.guohow.melody.service.player.MelodyPlayer;
import android.guohow.melody.utils.ArtWorkUtils;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import fr.castorflex.android.flipimageview.library.FlipImageView;

public class PlayingActivity extends Activity implements
		FlipImageView.OnFlipListener {

	TextView title, artist;
	SeekBar seekBar;
	Button btn_play, btn_next, btn_pre, btn_playMod;
	FlipImageView imageView;
	Handler handler = new Handler();
	BroadcastReceiver mBroadcastReceiver;
	AssetManager a;
	Typeface tf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.playing);
		init();
		initBoradcastReceiver();
		Constant.context = this;
		handler.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				updateSeekBarState();
				SetPlayBtnBg();
				updateText();
				if(imageView!=null&&Constant.PLAYING_STATE==1){
					setBgforImageViewBig();//莫名其妙，有时候封面不自动刷新，于是用线程解决:(
				}
				
				handler.postDelayed(this, 1000);
			}
		});

		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		// 退出时记得取消注册
		unregisterReceiver(mBroadcastReceiver);
		super.onDestroy();
	}

	private void initArtImageBig() {
		imageView = (FlipImageView) findViewById(R.id.img_art);
		setBgforImageViewBig();

	}
	
	private void setBgforImageViewBig(){
		ArtWorkUtils.setContent(imageView);
	}

	private void initTypeface() {
		// 获取配置信息，设置文本
		a = getAssets();// 得到AssetManager
		tf = Typeface.createFromAsset(a, "fonts/HeroLight.ttf");// 根据路径得到Typeface
		// text.setTypeface(tf);// 设置字体
	}

	private void init() {
		btn_play = (Button) findViewById(R.id.btn_play_ing);
		btn_next = (Button) findViewById(R.id.btn_next_ing);
		btn_pre = (Button) findViewById(R.id.btn_pre_ing);
		btn_play.setOnClickListener(new PlayButtonListener());
		btn_next.setOnClickListener(new NextButtonListener());
		btn_pre.setOnClickListener(new PreviousButtonListener());
		initArtImageBig();
		initSeekBar();
		initText();
		SetPlayBtnBg();
		updateSeekBarState();
		new BtnUIMonitor(btn_play, btn_pre, btn_next, null, seekBar, null,
				null, null, imageView);

	}

	private void initSeekBar() {
		seekBar = (SeekBar) findViewById(R.id.seekBar_ing);
	}

	private void initText() {
		title = (TextView) findViewById(R.id.title_text);
		artist = (TextView) findViewById(R.id.art_text);
		initTypeface();
		title.setTypeface(tf);

		artist.setTypeface(tf);
		updateText();
	}

	private void updateText() {
		title.setText(Constant.current_title);
		artist.setText(Constant.current_artist);
	}

	private void updateSeekBarState() {
		if (!Constant.ERER_PLAYED) {
			seekBar.setVisibility(View.INVISIBLE);
		}

		if (Constant.PLAYING_STATE == 1) {
			seekBar.setProgress(MelodyPlayer.player.getCurrentPosition());
			// seekBar.invalidate();
		}
	}

	private void SetPlayBtnBg() {
		final int state = Constant.PLAYING_STATE;
		switch (state) {
		case 0:// 1要求设置为play背景
			btn_play.setBackgroundResource(R.drawable.btn_play);
			break;
		case 1:
			btn_play.setBackgroundResource(R.drawable.btn_pause);
		}

	}

	@Override
	public void onClick(FlipImageView view) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFlipStart(FlipImageView view) {
		// TODO Auto-generated method stub
		final String title = Constant.current_title;
		final String artist = Constant.current_artist;
		final String url = Constant.current_url;
		final String duration = Constant.current_duration;
		QuerTools.queryTable(this, title, artist, url, duration);
		BoradCastManager.sendFavourListUpdateBoradCast();

	}

	@Override
	public void onFlipEnd(FlipImageView view) {
		// TODO Auto-generated method stub

	}

	private void registerBoradcastReceiver() {
		// 先初始化 后注册！！
		final IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction(Constant.ACTION_SONG_CHANGED);

		// 注册广播
		registerReceiver(mBroadcastReceiver, myIntentFilter);
	}

	private void initBoradcastReceiver() {

		mBroadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				final String action = intent.getAction();
				// 分析广播

				if (action.equals(Constant.ACTION_SONG_CHANGED)) {
					updateText();

					System.out.println("Playing 收到了广播》》》》》》》》》》》》》》》》》》》》》");
				}

			}

		};
		registerBoradcastReceiver();
	}

}
