package android.guohow.melody.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.guohow.melody.R;
import android.guohow.melody.constant.Constant;
import android.guohow.melody.preference.SharedConfig;
import android.guohow.melody.service.player.MelodyPlayer;
import android.guohow.melody.utils.ArtWorkUtils;
import android.guohow.melody.view.TopToast;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import fr.castorflex.android.flipimageview.library.FlipImageView;

/**
 * @author guohow ���չ㲥������ؼ���UI
 */
public class BtnUIMonitor implements FlipImageView.OnFlipListener {
	Button btn_play, btn_previous, btn_next, btn_playMod;
	SeekBar seekBar;
	FlipImageView btn_fav, artImage;
	TextView b_text;
	ImageView artIcon;
	static BroadcastReceiver mBroadcastReceiver;

	public BtnUIMonitor(Button btn_play, Button btn_previous, Button btn_next,
			Button btn_playMod, SeekBar seekBar, FlipImageView btn_fav,
			TextView b_text, ImageView artIcon, FlipImageView artImage) {
		this.btn_next = btn_next;
		this.btn_play = btn_play;
		this.btn_previous = btn_previous;
		this.btn_playMod = btn_playMod;
		this.seekBar = seekBar;
		this.btn_fav = btn_fav;
		this.b_text = b_text;
		this.artIcon = artIcon;
		this.artImage = artImage;
		initBoradcastReceiver();
		checkModBtn();
		if (artImage != null) {
			artImage.setOnFlipListener(this);
		}
		initSeekBar();

	}

	private void SetPlayBtnBg(int command) {
		switch (command) {
		case 1:// 1Ҫ������Ϊplay����
			btn_play.setBackgroundResource(R.drawable.btn_play);
			break;
		case 2:
			btn_play.setBackgroundResource(R.drawable.btn_pause);
		}

	}

	private void SetPlayModBtnBg(int command) {

		if (btn_playMod != null) {
			switch (command) {
			case 1:// ����playMod����
				btn_playMod.setBackgroundResource(R.drawable.btn_repeat_all);
				break;
			case 2:
				btn_playMod.setBackgroundResource(R.drawable.btn_repeat_off);
				break;
			case 3:
				btn_playMod.setBackgroundResource(R.drawable.btn_repeat_one);
				break;
			case 4:
				btn_playMod.setBackgroundResource(R.drawable.btn_shuffle_state);
				break;
			}
		}
	}

	private void update_b_text() {
		if (Constant.ERER_PLAYED) {

			Constant.current_title = Constant.trackList.get(Constant.CURRENT)
					.get("title").toString();
			Constant.current_artist = Constant.trackList.get(Constant.CURRENT)
					.get("artist").toString();
			Constant.current_url = Constant.trackList.get(Constant.CURRENT)
					.get("url").toString();
			Constant.current_duration = Constant.trackList
					.get(Constant.CURRENT).get("duration").toString();
		}
		if (Constant.ERER_PLAYED && b_text != null) {
			b_text.setText("���ڲ���:" + Constant.current_title + "		" + "������:"
					+ Constant.current_artist);
		}
		System.out.println(Constant.current_title + Constant.current_artist);
		System.out.println(b_text);

	}

	public static void updateCurrent() {
		if (Constant.ERER_PLAYED) {

			Constant.current_title = Constant.trackList.get(Constant.CURRENT)
					.get("title").toString();
			Constant.current_artist = Constant.trackList.get(Constant.CURRENT)
					.get("artist").toString();
			Constant.current_url = Constant.trackList.get(Constant.CURRENT)
					.get("url").toString();
			Constant.current_duration = Constant.trackList
					.get(Constant.CURRENT).get("duration").toString();
		}

	}

	private void updateArtImageView() {
		if (artIcon != null) {
			ArtWorkUtils.setContent(artIcon);
		}

	}

	private void updateArtImageBig() {
		if (artImage != null) {
			ArtWorkUtils.setContent(artImage);
		}
	}

	// private void updateArtImageSrc(){
	//
	// BitmapDrawable bd=new BitmapDrawable(ArtWorkUtils.getContent());
	// artImage.setFlippedDrawable(bd);
	// }

	// �������ʱ��ȡ֮ǰ�����ã�����ʼ����ť��ͼ
	private void checkModBtn() {
		final int _mod = new SharedConfig(Constant.context).getPlayMod();
		switch (_mod) {
		case 1:
			Constant.PLAYMOD = 1;
			SetPlayModBtnBg(1);
			break;
		case 2:
			Constant.PLAYMOD = 2;
			SetPlayModBtnBg(2);
			break;
		case 3:
			Constant.PLAYMOD = 3;
			SetPlayModBtnBg(3);
			break;
		case 4:
			Constant.PLAYMOD = 4;
			SetPlayModBtnBg(4);
			break;

		}

	}

	private void registerBoradcastReceiver() {
		// �ȳ�ʼ�� ��ע�ᣡ��
		final IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction(Constant.ACTION_SONG_CHANGED);
		myIntentFilter.addAction(Constant.ACTION_SONG_FIRST_PLAY);
		myIntentFilter.addAction(Constant.ACTION_SONG_NORMAL_PLAY);
		myIntentFilter.addAction(Constant.ACTION_SONG_NEXT);
		myIntentFilter.addAction(Constant.ACTION_SONG_PAUSE);
		myIntentFilter.addAction(Constant.ACTION_SONG_PREVIOUS);
		myIntentFilter.addAction(Constant.ACTION_SONG_RESUME);
		myIntentFilter.addAction(Constant.ACTION_SONG_STATE_CHANGED);
		myIntentFilter.addAction(Constant.ACTION_SONG_STOP);
		myIntentFilter.addAction(Constant.ACTION_MOD_1);
		myIntentFilter.addAction(Constant.ACTION_MOD_2);
		myIntentFilter.addAction(Constant.ACTION_MOD_3);
		myIntentFilter.addAction(Constant.ACTION_MOD_4);
		myIntentFilter.addAction(Constant.ACTION_FLIP_END);
		// ע��㲥
		Constant.context.registerReceiver(BtnUIMonitor.mBroadcastReceiver,
				myIntentFilter);
	}

	private void initBoradcastReceiver() {

		BtnUIMonitor.mBroadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				final String action = intent.getAction();
				// �����㲥
				if (action.equals(Constant.ACTION_SONG_FIRST_PLAY)) {
					SetPlayBtnBg(2);
					update_b_text();
					updateArtImageView();
					updateArtImageBig();
					// updateArtImageSrc();
					System.out.println("UIM�յ���һ�β��ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_SONG_NORMAL_PLAY)) {
					SetPlayBtnBg(2);
					update_b_text();
					updateArtImageView();
					updateArtImageBig();
					// updateArtImageSrc();
				}
				if (action.equals(Constant.ACTION_SONG_CHANGED)) {
					update_b_text();
					updateArtImageView();
					updateArtImageBig();
					// updateArtImageSrc();
					if (seekBar != null) {
						seekBar.setVisibility(View.VISIBLE);
					}
				}
				if (action.equals(Constant.ACTION_SONG_NEXT)) {

					update_b_text();
					updateArtImageView();
					updateArtImageBig();
					// updateArtImageSrc();
					System.out.println("UIM�յ�NEXT���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_SONG_PREVIOUS)) {

					update_b_text();
					updateArtImageView();
					updateArtImageBig();
					// updateArtImageSrc();
					System.out.println("UIM�յ�PRE���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_SONG_PAUSE)) {

					SetPlayBtnBg(1);
					update_b_text();
					updateArtImageView();
					updateArtImageBig();
					// updateArtImageSrc();
					System.out.println("UIM�յ�PAUSE���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_SONG_RESUME)) {
					update_b_text();
					SetPlayBtnBg(2);
					updateArtImageView();
					updateArtImageBig();
					// updateArtImageSrc();

				}
				if (action.equals(Constant.ACTION_SONG_STOP)) {
					SetPlayBtnBg(1);
					System.out.println("UIM�յ�STOP���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_MOD_1)) {
					SetPlayModBtnBg(1);
					new SharedConfig(Constant.context).setPlayMod(1);
					TopToast.makeText(Constant.context, "ȫ��ѭ��",
							Toast.LENGTH_SHORT).show();
					System.out.println("UIM�յ�_1���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_MOD_2)) {
					SetPlayModBtnBg(2);
					new SharedConfig(Constant.context).setPlayMod(2);
					TopToast.makeText(Constant.context, "˳�򲥷�",
							Toast.LENGTH_SHORT).show();
					System.out.println("UIM�յ�2���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_MOD_3)) {
					SetPlayModBtnBg(3);
					new SharedConfig(Constant.context).setPlayMod(3);
					TopToast.makeText(Constant.context, "����ѭ��",
							Toast.LENGTH_SHORT).show();
					System.out.println("UIM�յ�3���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_MOD_4)) {
					SetPlayModBtnBg(4);
					new SharedConfig(Constant.context).setPlayMod(4);
					TopToast.makeText(Constant.context, "���",
							Toast.LENGTH_SHORT).show();
					System.out.println("UIM�յ�4���ŵĹ㲥");

				}
				if (action.equals(Constant.ACTION_FLIP_END)) {

					System.out.println("UIM�յ����������Ĺ㲥");

				}
			}

		};
		registerBoradcastReceiver();
	}

	@Override
	public void onClick(FlipImageView view) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFlipStart(FlipImageView view) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFlipEnd(FlipImageView view) {
		// TODO Auto-generated method stub

	}

	private void initSeekBar() {
		if (seekBar != null) {
			MelodyPlayer.seekBar = seekBar;
		}

	}

	public static void unRBD(Context context) {
		context.unregisterReceiver(BtnUIMonitor.mBroadcastReceiver);
	}

}
