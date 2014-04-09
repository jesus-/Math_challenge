package com.example.math_challenge;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.Context;

public class ScoresDataBase extends SQLiteOpenHelper {
	
	  public static final String TABLE_SCORES = "scores";
	  public static final String COLUMN_ID ="id";
	  public static final String COLUMN_DATE = "date";
	  public static final String COLUMN_NAME = "name";
	  public static final String COLUMN_SCORE = "score";
	  public static final String COLUMN_LEVEL = "level";
	  
	  private static final String DATABASE_NAME = "bestScores.db";
	  private static final int DATABASE_VERSION = 1;
	  // Database creation sql statement
	  private static final String DATABASE_CREATE = 
		  "create table " + TABLE_SCORES + "("
		  + COLUMN_ID + " integer primary key autoincrement, "
		  + COLUMN_NAME + " text not null, " 
		  + COLUMN_SCORE+ " integer," 
		  + COLUMN_LEVEL + " text not null, " 		  
		  + COLUMN_DATE +" numeric"
	      + ");";
	  public ScoresDataBase(Context context) {
		    super(context, DATABASE_NAME, null, DATABASE_VERSION);
		  }
	  public void onCreate(SQLiteDatabase database) {
		    database.execSQL(DATABASE_CREATE);
		  }

		  @Override
		  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		    Log.w(ScoresDataBase.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
		    onCreate(db);
		  }


}
