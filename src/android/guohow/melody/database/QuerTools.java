package android.guohow.melody.database;

import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.guohow.melody.constant.Constant;
import android.guohow.melody.view.TopToast;
import android.widget.Toast;

public class QuerTools {
	private static SQLHelper dbHelper;
	public static SQLiteDatabase db;
	private static Cursor cursor;

	// 添加&删除并返回查询结果
	public static int queryTable(Context context, String str, String str_1,
			String str_2, String str_3) {
		int flag = 0;
		try {

			QuerTools.dbHelper = new SQLHelper(context, Constant.DB_NAME, null,
					Constant.DB_VERSION);
			// SQLHelper.TABLE_NAME 表名
			QuerTools.db = QuerTools.dbHelper.getWritableDatabase(); // 自动调用SQLiteHelper.OnCreate()
			/* 查询表，得到cursor对象 */
			QuerTools.cursor = QuerTools.db.query(Constant.TABLE_NAME, null,
					null, null, null, null, "title" + " DESC");
			QuerTools.cursor.moveToFirst();

			while (Constant.PLAYING_STATE == 1
					&& !QuerTools.cursor.isAfterLast()
					&& QuerTools.cursor.getString(1) != null && flag == 0) {
				final String title = QuerTools.cursor.getString(1);
				System.out.println(title + "------------" + str);
				if (title.equals(str)) {
					System.out
							.println("找到匹配文件++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					flag = 1;
					// db.delete(Constant.TABLE_NAME, "title" + "=?",
					// new String[] { str });
					// Folder.UPDATE = 1;
					TopToast.makeText(context, "该歌曲已存在", Toast.LENGTH_SHORT)
							.show();
					break;
				} else {
					QuerTools.cursor.moveToNext();
				}
			}

			// 如果插入了空值，会引发不断的FC
			if (Constant.PLAYING_STATE == 1 && flag == 0) {
				final ContentValues values = new ContentValues();
				if (str != "") {
					values.put("title", str);
					values.put("artist", str_1);
					values.put("url", str_2);
					values.put("duration", str_3);

					QuerTools.db.insert(Constant.TABLE_NAME, null, values);
					TopToast.makeText(context, "收藏成功", Toast.LENGTH_SHORT)
							.show();
					System.out.println("" + QuerTools.cursor);
				}
			}

		} catch (final IllegalArgumentException e) {
			e.printStackTrace();
			// 当版本变更时会调用SQLiteHelper.onUpgrade()方法重建
			// 注：表以前数据将丢失
			++Constant.DB_VERSION;
			QuerTools.dbHelper.onUpgrade(QuerTools.db, --Constant.DB_VERSION,
					Constant.DB_VERSION);
		}
		return flag;//

	}

	// 只是返回查询结果
	public static int queryTableNormal(Context context, String str,
			String str_1, String str_2) {
		int flag_n = 0;
		try {

			QuerTools.dbHelper = new SQLHelper(context, Constant.DB_NAME, null,
					Constant.DB_VERSION);
			// SQLHelper.TABLE_NAME 表名
			QuerTools.db = QuerTools.dbHelper.getWritableDatabase(); // 自动调用SQLiteHelper.OnCreate()
			/* 查询表，得到cursor对象 */
			QuerTools.cursor = QuerTools.db.query(Constant.TABLE_NAME, null,
					null, null, null, null, "title" + " DESC");
			QuerTools.cursor.moveToFirst();

			while (Constant.ERER_PLAYED && !QuerTools.cursor.isAfterLast()
					&& QuerTools.cursor.getString(1) != null && flag_n == 0) {
				final String title = QuerTools.cursor.getString(1);
				System.out.println(title + "------------" + str);
				if (title.equals(str)) {
					System.out
							.println("已找到匹配歌曲++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					flag_n = 1;
				} else {
					QuerTools.cursor.moveToNext();
				}
			}

		} catch (final IllegalArgumentException e) {
			e.printStackTrace();
			// 当版本变更时会调用SQLiteHelper.onUpgrade()方法
			// 注：表以前数据将丢失
			++Constant.DB_VERSION;
			QuerTools.dbHelper.onUpgrade(QuerTools.db, --Constant.DB_VERSION,
					Constant.DB_VERSION);
		}
		return flag_n;// 0表示未查到

	}

	// 初始化数据库
	public static List<HashMap<String, Object>> createAndCursorTable(
			Context context, List<HashMap<String, Object>> trackList) {

		try {
			// 初始化&创建数据库
			QuerTools.dbHelper = new SQLHelper(context, Constant.DB_NAME, null,
					Constant.DB_VERSION);
			// 创建表
			// TABLE_NAME数据库名 *.db
			// SQLHelper.TABLE_NAME 表名
			QuerTools.db = QuerTools.dbHelper.getWritableDatabase(); // 自动调用SQLiteHelper.OnCreate()
			/* 查询表，得到cursor对象 */
			QuerTools.cursor = QuerTools.db.query(Constant.TABLE_NAME, null,
					null, null, null, null, "title" + " DESC");
			QuerTools.cursor.moveToFirst();
			trackList.clear();
			while (!QuerTools.cursor.isAfterLast()
					&& QuerTools.cursor.getString(1) != null) {
				final HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("title", QuerTools.cursor.getString(1));
				map.put("artist", QuerTools.cursor.getString(2));
				map.put("url", QuerTools.cursor.getString(3));
				map.put("duration", QuerTools.cursor.getString(4));
				trackList.add(map);
				QuerTools.cursor.moveToNext();
			}

		} catch (final IllegalArgumentException e) {
			e.printStackTrace();
			// 当版本变更时会调用SQLiteHelper.onUpgrade()方法重建表
			// 注：表以前数据将丢失
			++Constant.DB_VERSION;
			QuerTools.dbHelper.onUpgrade(QuerTools.db, --Constant.DB_VERSION,
					Constant.DB_VERSION);
		}

		return trackList;

	}

}
