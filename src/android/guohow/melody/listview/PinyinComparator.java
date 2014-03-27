package android.guohow.melody.listview;

import java.util.Comparator;

public class PinyinComparator implements Comparator<Content> {

	@Override
	public int compare(Content o1, Content o2) {
		if (o1.getLetter().equals("@")
				|| o2.getLetter().equals("#")) {
			return -1;
		} else if (o1.getLetter().equals("#")
				|| o2.getLetter().equals("@")) {
			return 1;
		} else {
			return o1.getLetter().compareTo(o2.getLetter());
		}
	}

}
