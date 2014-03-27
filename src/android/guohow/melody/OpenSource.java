package android.guohow.melody;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OpenSource extends Activity {

	String url;
	WebView show;
	Dialog dialog;

	@SuppressLint("SetJavaScriptEnabled")
	public void getWebContent() {

		show = (WebView) findViewById(R.id.webView1);

		show.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

		url = "http://github.com/guohow/melody_music_player_with_slideMenu/";
		// url="file:///android_asset/blog.html";

		show.getSettings().setJavaScriptEnabled(true);
		final WebSettings ws = show.getSettings();

		ws.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

		ws.setSupportZoom(true);

		show.loadUrl(url);
		dialog = ProgressDialog.show(this, null, "正在载入，您的网络较慢，请耐心等待...");
		show.reload();
		show.setWebViewClient(new MyWebViewClient(dialog));

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.open_source);
		getWebContent();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && show.canGoBack()) {
			show.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
