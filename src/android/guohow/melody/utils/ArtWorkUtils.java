package android.guohow.melody.utils;

import java.io.File;

import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.guohow.melody.constant.Constant;
import android.widget.ImageView;

public class ArtWorkUtils {

	/**
	 * @author guohow®¹
	 */
	public static void setContent(ImageView view) {

		Bitmap bitmap = null;
		String url = "";
		if (Constant.ERER_PLAYED) {
			url = (String) Constant.trackList.get(Constant.CURRENT).get("url");
		}
		try {
			final File sourceFile = new File(url);
			MP3File mp3file = null;

			mp3file = new MP3File(sourceFile);
			final AbstractID3v2Tag tag = mp3file.getID3v2Tag();
			final AbstractID3v2Frame frame = (AbstractID3v2Frame) tag
					.getFrame("APIC");
			final FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();
			final byte[] imageData = body.getImageData();
			bitmap = BitmapFactory.decodeByteArray(imageData, 0,
					imageData.length);
			System.out.println("img--------------------" + bitmap);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		if (bitmap != null) {
			view.setImageBitmap(bitmap);
		}

	}

	public static Bitmap getContent() {

		Bitmap bitmap = null;
		String url = "";
		if (Constant.ERER_PLAYED) {
			url = (String) Constant.trackList.get(Constant.CURRENT).get("url");
		}
		try {
			final File sourceFile = new File(url);
			MP3File mp3file = null;

			mp3file = new MP3File(sourceFile);
			final AbstractID3v2Tag tag = mp3file.getID3v2Tag();
			final AbstractID3v2Frame frame = (AbstractID3v2Frame) tag
					.getFrame("APIC");
			final FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();
			final byte[] imageData = body.getImageData();
			bitmap = BitmapFactory.decodeByteArray(imageData, 0,
					imageData.length);
			System.out.println("img--------------------" + bitmap);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return bitmap;
	}

}