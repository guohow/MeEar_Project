package android.guohow.melody.constant;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.widget.SeekBar;

/**
 * @author guohow 一个获取和记录状态的类
 * 
 */
public class Constant {
	public static int PLAYMOD = 1;
	public static int PLAYING_STATE = 0;
	public static boolean ERER_PLAYED = false;
	public static String ACTION_SONG_FIRST_PLAY = "SONG_FIRST_PLAY";
	public static String ACTION_SONG_NORMAL_PLAY = "NORMAL_PLAY";
	public static String ACTION_SONG_CHANGED = "SONG_CHANGED";
	public static String ACTION_SONG_STATE_CHANGED = "SONG_STATE_CHANGED";
	public static String ACTION_SONG_NEXT = "SONG_NEXT";
	public static String ACTION_SONG_PREVIOUS = "SONG_PREVIOUS";
	public static String ACTION_SONG_PAUSE = "SONG_PAUSE";
	public static String ACTION_SONG_RESUME = "SONG_RESUME";
	public static String ACTION_SONG_STOP = "SONG_STOP";
	public static String ACTION_MOD_1 = "MOD_1";
	public static String ACTION_MOD_2 = "MOD_2";
	public static String ACTION_MOD_3 = "MOD_3";
	public static String ACTION_MOD_4 = "MOD_4";
	public static String ACTION_FAVOURITE_LIST_UPDATE = "FAV_LIST_UPDATE";
	public static String ACTION_FLIP_END = "FLIP_END";
	public static int CURRENT = 0;
	public static String DB_NAME = "FavouriteTrack.db";
	public static int DB_VERSION = 1;
	public static String TABLE_NAME = "FavouriteTable";
	public static SeekBar seekBar;
	public static Context context;
	public static String current_title = "MelodyEar";
	public static String current_artist = "you and me";
	public static String current_url;
	public static String current_duration;
	public static List<HashMap<String, Object>> trackList;

}
