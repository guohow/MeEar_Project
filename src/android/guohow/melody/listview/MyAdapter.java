package android.guohow.melody.listview;

import java.util.List;

import android.content.Context;
import android.guohow.melody.R;
import android.guohow.melody.constant.Constant;
import android.guohow.melody.preference.SharedConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter implements SectionIndexer {

	private List<Content> list = null;
	private final Context mContext;

	public MyAdapter(Context mContext, List<Content> list) {
		this.mContext = mContext;
		this.list = list;

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.local_item,
					null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			// 设置提示条颜色，如果是自定义背景，就不要白色了，否则很别扭,直接写一个代码块算了:)
			final int mod = new SharedConfig(Constant.context).getBgMod();
			if (mod == 1) {
				viewHolder.tvLetter.setBackgroundResource(R.color.transparent);
			}
			viewHolder.tvArtist = (TextView) view.findViewById(R.id.artist);
			viewHolder.tvDuration = (TextView) view.findViewById(R.id.duration);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		final Content mContent = list.get(position);
		if (position == 0) {
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getLetter());
		} else {
			final String lastCatalog = list.get(position - 1).getLetter();
			if (mContent.getLetter().equals(lastCatalog)) {
				viewHolder.tvLetter.setVisibility(View.GONE);
			} else {
				viewHolder.tvLetter.setVisibility(View.VISIBLE);
				viewHolder.tvLetter.setText(mContent.getLetter());
			}
		}

		viewHolder.tvTitle.setText(list.get(position).getName());
		viewHolder.tvArtist.setText(list.get(position).getArtist());
		viewHolder.tvDuration.setText(list.get(position).getDuration());

		return view;

	}

	final static class ViewHolder {
		TextView tvTitle;
		TextView tvLetter;
		TextView tvArtist;
		TextView tvDuration;
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSectionForPosition(int position) {

		return 0;
	}

	@Override
	public int getPositionForSection(int section) {
		Content mContent;
		String l;
		if (section == '!') {
			return 0;
		} else {
			for (int i = 0; i < getCount(); i++) {
				mContent = list.get(i);
				l = mContent.getLetter();
				final char firstChar = l.toUpperCase().charAt(0);
				if (firstChar == section) {
					return i + 1;
				}

			}
		}
		mContent = null;
		l = null;
		return -1;
	}

}