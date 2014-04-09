package com.example.math_challenge;

import java.util.Random;

public enum Level {
	EASY(21,11,6,2,new int []{0,0,0,1,1,1,2,2,3,3,4,5}),
	HARD(50,13,10,3,new int []{0,0,1,1,2,2,2,3,3,3,4,5}),
	EXTREME(100,30,20,3,new int []{0,0,1,1,2,2,3,3,4,4,5,5});
	
	private int _maxAddSubstractNumber;
	private int _maxTimesDivide;
	private int _maxRootSquare;
	private int _maxPower;
	private int [] _gradesOperation;
	
	
	Level(int maxAddSubstractNumber, int maxTimesDivide,int maxRootSquare,  int maxPower, int [] gradesOperation){
		_maxAddSubstractNumber = maxAddSubstractNumber;
		_maxTimesDivide=maxTimesDivide;
		_maxRootSquare = maxRootSquare;
		_maxPower = maxPower;
		_gradesOperation = gradesOperation;
		
	}
	public int getOperation(){
		
		return this.generateOperation();
	}
	
//	public int getMaxRandomNumber(){
//		return _maxRandomNumber;
//	}
	public int generateNumber(int operation){
		int number =0;
		switch (operation){
			case 0:
			case 1:
				number = generateNumberAddSub();
				break;
			case 2:
			case 3:
				number = generateNumberDivideTimes();
				break;
			case 4:
			case 5:
				number = generateNumberRootPower();
				break;
			default:
				number=1;
				break;
		}
		return number;
	
	}
	public int generateNumberAddSub(){
		Random randomGenerator = new Random();
		return  randomGenerator.nextInt(_maxAddSubstractNumber);
	
	}
	public int generateNumberDivideTimes(){
		Random randomGenerator = new Random();
		return  randomGenerator.nextInt(_maxTimesDivide);
	
	}	
	public int generateNumberRootPower(){
		Random randomGenerator = new Random();
		return  randomGenerator.nextInt(_maxRootSquare);
	
	}	
	public int generateOperation(){
		Random randomGenerator = new Random();
		return  _gradesOperation[randomGenerator.nextInt(12)];
	
	}	
	public int generatePower(){
		Random randomGenerator = new Random();
		return  randomGenerator.nextInt(_maxPower) +1;
	
	}	

}
