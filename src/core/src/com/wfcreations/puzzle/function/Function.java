package com.wfcreations.puzzle.function;

public interface Function {

	public void calculate(float t, float phase);
	
	public float getCurrentX();
	
	public float getCurrentY();
	
	public float getNextX();
	
	public float getNextY();
	
	public boolean equal(Function function, float phase);
	
}
