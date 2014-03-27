package android.guohow.melody.utils;

import android.app.ActionBar;
import android.content.Context;
import android.support.v4.app.Fragment;

public class ActionBarUtils {

	public void initActionBar(Fragment fragment, Context context, int _id) {

		switch (_id) {
		// ������Ŀ��ͬ����Ч��
		case 1:
			// ����actionBarͼ��
			final ActionBar mActionBar = fragment.getActivity().getActionBar();
			// mActionBar.setLogo(R.drawable.ac_img_menu_local);
			// mActionBar.setTitle("ȫ��");
			// mActionBar.setBackgroundDrawable(context.getResources()
			// .getDrawable(R.color.blue));
			mActionBar.hide();
			break;
		case 2:

			final ActionBar mActionBar_2 = fragment.getActivity()
					.getActionBar();
			// mActionBar_2.setLogo(R.drawable.ac_img_menu_favour);
			// mActionBar_2.setTitle("�ղ�");
			// mActionBar_2.setBackgroundDrawable(context.getResources()
			// .getDrawable(R.color.blue));
			mActionBar_2.hide();
			break;

		case 3:

			final ActionBar mActionBar_3 = fragment.getActivity()
					.getActionBar();
			// mActionBar_3.setLogo(R.drawable.ac_img_menu_help);
			// mActionBar_3.setTitle("���");
			// mActionBar_3.setBackgroundDrawable(context.getResources()
			// .getDrawable(R.color.blue));
			mActionBar_3.hide();
			break;

		}
	}
}
