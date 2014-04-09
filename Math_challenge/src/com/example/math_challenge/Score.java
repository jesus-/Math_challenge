package com.example.math_challenge;

import java.util.Comparator;



public class Score implements Comparator<Score>{
	  private int _id;
	  private String _name;
	  private int _score;
	  private long _date;
	  private Level _level;
	  
	  private int _ranking;

	public Level getLevel() {
		return _level;
	}

	public void setLevel(Level level) {
		this._level = level;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public long getDate() {
		return _date;
	}

	public void setDate(long date) {
		this._date = date;
	}

	public String getName() {
	    return _name;
	 }

	 public void setName(String name) {
	    this._name = name;
	 }

	 public int getScore() {
	    return _score;
	 }

	 public void setScore(int score) {
	    this._score = score;
	 }

	 // Will be used by the ArrayAdapter in the ListView
	  @Override
	 public String toString() {
		String format = "%2d.%10s%5d";
	    return String.format(format, _ranking,_name,_score);
	 }
	  
	  public int getRanking() {
		return _ranking;
	}

	public void setRanking(int ranking) {
		this._ranking = ranking;
	}

	public int compare(Score object1, Score object2) {
			return object2.getScore()-object1.getScore();
	}

}
