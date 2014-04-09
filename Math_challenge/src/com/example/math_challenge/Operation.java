package com.example.math_challenge;

public class Operation {

	String _operationString;
	int _param1;
	int _param2;
	int _solution;
	int _operation;
	Level _level;
	
	Operation(Level level){
		_level = level;
		_operationString ="";
		_solution =0;
		_operation = _level.generateOperation();
		_param1=_level.generateNumber(_operation);
		_param2=_level.generateNumber(_operation);
		
	}
	
	public String get_operationString() {
		return _operationString;
	}

	public void setOperationSring(String operationSring) {
		this._operationString = operationSring;
	}

	public int getSolution() {
		return _solution;
	}

	public void setSolution(int solution) {
		this._solution = solution;
	}

	public Level getLevel() {
		return _level;
	}

	public void setLevel(Level level) {
		this._level = level;
	}
	public void generateOperation(){
		
		int aux;
		switch(_operation){
		case 0: 
			_solution = _param1 + _param2;
			_operationString =  _param1+"+"+_param2;
			break;
		case 1: 
			if(_param2>_param1){
				aux = _param1;
				_param1 = _param2;
				_param2= aux;
			}
			_solution = _param1 - _param2;	
			_operationString =  _param1+"-"+_param2;
			break;
		case 2: 
			_solution = _param1 *_param2;
			_operationString =  _param1+"x"+_param2;		
			break;
		case 3: 
			while(_param2==0)
				_param2 =_level.generateNumber(_operation);
			_solution = _param1;
			_param1 = _param1 * _param2;
			_operationString =  _param1+"/"+_param2;				
			break;			
		case 4:
			
			String symbol ="default";
			_param2 = _level.generatePower();
			if(_param2 == 2)
					symbol="\u00B2";
			else if (_param2==3)
					symbol="\u00B3";
			else if (_param2==1)
					symbol = "\u00B9";
			
			_solution = (int) Math.pow(_param1, _param2);
			_operationString =  _param1+symbol;
			break;
		case 5: 
			_operationString = "\u221A";
			_solution = _param1;
			_param1 = _param1*_param1;
			_operationString =  "\u221A"+ _param1;			
			break;


		default: 
			_solution = _param1 + _param2;
			_operationString =  _param1+"+"+_param2;			
			break;				
		}
		
	
	}
	
}
