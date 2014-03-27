package android.guohow.melody.welcome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.guohow.melody.R;
import android.guohow.melody.constant.Constant;
import android.guohow.melody.fragment.MelodyApplication;
import android.guohow.melody.preference.SharedConfig;
import android.guohow.melody.service.PlayTrackService;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

//软件入口，闪屏界面。
public class WelcomeSplash extends Activity {
	private View view;
	private Context context;
	private Animation animation;
	private int _first;
	private static int TIME = 1000; // 进入主程序的延迟时间

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = View.inflate(this, R.layout.splash, null);
		setContentView(view);
		final TextView text = (TextView) findViewById(R.id.splash);
		// 获取配置信息，设置文本
		final AssetManager a = getAssets();// 得到AssetManager
		final Typeface tf = Typeface.createFromAsset(a, "fonts/HeroLight.ttf");// 根据路径得到Typeface
		text.setTypeface(tf);// 设置字体
		text.setText(getText());
		context = this; // 得到上下文
		_first = new SharedConfig(context).getFirstInitConfig(); // 得到配置文件
		Constant.context = this;
		startService();

	}

	private void startService() {

		// 启动服务
		final Intent intent = new Intent();
		intent.setClass(this, PlayTrackService.class);
		startService(intent);
	}

	public String getText() {

		final SharedPreferences spf = PreferenceManager
				.getDefaultSharedPreferences(this);
		final String text = spf.getString("flash_text_perf", "MELODY EAR");
		return text;
	}

	@Override
	protected void onResume() {
		into();
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	// 进入主程序的方法
	private void into() {
		// 设置动画效果是alpha，在anim目录下的alpha.xml文件中定义动画效果
		animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
		// 给view设置动画效果
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			// 这里监听动画结束的动作，在动画结束的时候开启一个线程，这个线程中绑定一个Handler,并
			// 在这个Handler中调用goHome方法，而通过postDelayed方法使这个方法延迟500毫秒执行，达到
			// 达到持续显示第一屏500毫秒的效果
			@Override
			public void onAnimationEnd(Animation arg0) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent intent;
						// 如果第一次，则进入引导页WelcomeActivity
						if (_first == 0) {
							intent = new Intent(WelcomeSplash.this,
									WelcomeActivity.class);
						} else {
							intent = new Intent(WelcomeSplash.this,
									MelodyApplication.class);
						}
						startActivity(intent);
						// 设置Activity的切换效果
						overridePendingTransition(R.anim.in_from_right,
								R.anim.out_to_left);
						WelcomeSplash.this.finish();
					}
				}, WelcomeSplash.TIME);
			}
		});

	}
}
