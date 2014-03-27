package android.guohow.melody;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class About extends Activity {

	private void initActionBarHome() {

		getActionBar().setHomeButtonEnabled(true);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setLogo(R.drawable.ac_img_menu_help);
		getActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.color.holo_red_dark));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		initActionBarHome();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
