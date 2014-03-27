package android.guohow.melody.utils;

import android.graphics.Bitmap;

public class Mp3Bean {
	long id, size;

	public String title;

	String url;

	String art;

	String duration;
	Bitmap icon;

	public String getArt() {

		return art;
	}

	public String getDuration() {
		return duration;
	}

	public Bitmap getIcon() {
		return icon;

	}

	public long getId() {
		return id;
	}

	public long getSize() {
		return size;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;

	}

	public void setArt(String art) {

		this.art = art;
	}

	public void setDuration(String dur) {
		duration = dur;
	}

	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
		;
	}

}
