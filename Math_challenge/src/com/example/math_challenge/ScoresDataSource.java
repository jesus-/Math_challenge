package com.example.math_challenge;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

	
public class ScoresDataSource {
	
	 // Database fields
	  private SQLiteDatabase _database;
	  private ScoresDataBase _dbScores;
	  private String[] _allColumns = { 
			  ScoresDataBase.COLUMN_ID,
			  ScoresDataBase.COLUMN_NAME,
			  ScoresDataBase.COLUMN_SCORE,
			  ScoresDataBase.COLUMN_LEVEL,
			  ScoresDataBase.COLUMN_DATE
			  };
	  static final int MAX_ROWS_DB = 20;
	  
	  public ScoresDataSource(Context context) {
		  _dbScores = new ScoresDataBase(context);
	  }

	  public void open() throws SQLException {
	    _database = _dbScores.getWritableDatabase();
	  }

	  public void close() {
		  _dbScores.close();
	  }

	  public Score createScore(int score, String name, Level level, long date) {
	    ContentValues values = new ContentValues();
	    values.put(ScoresDataBase.COLUMN_SCORE, score);
	    values.put(ScoresDataBase.COLUMN_NAME,name);
	    values.put(ScoresDataBase.COLUMN_LEVEL,level.name());
	    values.put(ScoresDataBase.COLUMN_DATE, date);
	    
	    long insertId = _database.insert(ScoresDataBase.TABLE_SCORES,null,
	        values);
	    Cursor cursor = _database.query(ScoresDataBase.TABLE_SCORES,
	        _allColumns, ScoresDataBase.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Score newScore = cursorToScore(cursor,0);
	    cursor.close();
	    return newScore;
	  }

	  public void deleteNotTopScores(Level level) {
		 _database.delete(ScoresDataBase.TABLE_SCORES, 
		 ScoresDataBase.COLUMN_LEVEL + "="+ "'"+level.name() + "' and "+		 
	     ScoresDataBase.COLUMN_ID + " not in (select "+ScoresDataBase.COLUMN_ID + " from "+
	     ScoresDataBase.TABLE_SCORES+" where "+
	     ScoresDataBase.COLUMN_LEVEL + "="+ "'"+level.name()+"' order by "+
	     ScoresDataBase.COLUMN_SCORE +" desc limit "+MAX_ROWS_DB+")"      
	    , null);
	  }

	  public ArrayList<Score> getAllScores(Level level) {
	    ArrayList<Score> scores = new ArrayList<Score>();

	    Cursor cursor = _database.query(ScoresDataBase.TABLE_SCORES,
	        _allColumns, 
	        ScoresDataBase.COLUMN_LEVEL + "="+ "'"+level.name()+"'",
//	        null, null, null, ScoresDataBase.COLUMN_SCORE +" desc",Integer.toString(MAX_ROWS_DB));
	        null, null, null, ScoresDataBase.COLUMN_SCORE +" desc",null);
	    cursor.moveToFirst();
	    int rank =1;
	    while (!cursor.isAfterLast()) {
	      Score score = cursorToScore(cursor,rank);
	      scores.add(score);
	      cursor.moveToNext();
	      rank++;
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return scores;
	  }

	  private Score cursorToScore(Cursor cursor,int rank) {
	    Score score = new Score();
	    score.setId(cursor.getInt(0));
	    score.setName(cursor.getString(1));
	    score.setScore(cursor.getInt(2));
	    score.setLevel(Level.valueOf(cursor.getString(3)));	    
	    score.setDate(cursor.getLong(4));
	    score.setRanking(rank);
	    return score;
	  }
}
