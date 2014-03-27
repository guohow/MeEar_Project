package android.guohow.melody.view;

import android.content.Context;
import android.guohow.melody.R;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BottomToast extends Toast {

	public BottomToast(Context context) {
		super(context);
	}

	public static Toast makeText(Context context, CharSequence text,
			int duration) {
		final Toast result = new Toast(context);
		final LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		final View layout = inflater.inflate(R.layout.toast, null);

		final TextView textView = (TextView) layout.findViewById(R.id.toast);

		textView.setText(text);

		result.setView(layout);
		result.setGravity(Gravity.BOTTOM, 0, 0);
		// result.setMargin(null, 20f);
		result.setDuration(duration);

		return result;
	}

}
