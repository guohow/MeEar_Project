/**
 * 
 */
package android.guohow.melody.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.guohow.melody.constant.Constant;

/**
 * @author guohao
 * 
 */
public class SQLHelper extends SQLiteOpenHelper {

	public static String CREATE_TABLE;

	public SQLHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	private void init() {

		SQLHelper.CREATE_TABLE = "CREATE TABLE" + " " + Constant.TABLE_NAME
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT ,"
				+ "title TEXT,artist TEXT,url TEXT,duration,TEXT)";//
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		init();//
		db.execSQL(SQLHelper.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + Constant.TABLE_NAME);//
		onCreate(db);//
	}

}
