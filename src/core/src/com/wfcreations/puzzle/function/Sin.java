package com.wfcreations.puzzle.function;

import com.wfcreations.puzzle.Constants;

public class Sin implements Function {

	private float frequency;
	
	private float currentX;
	
	private float currentY;
	
	private float nextX;
	
	private float nextY;
	
	public Sin(float frequency) {
		this.frequency = frequency;
	}
	
	@Override
	public void calculate(float t, float phase) {
		currentX = (float) t;
		currentY = (float) Math.sin(2 * Math.PI * frequency * currentX + phase);
		nextX = (float) currentX + 1;
		nextY = (float) Math.sin(2 * Math.PI * frequency * nextX + phase);
	}

	@Override
	public float getCurrentX() {
		return this.currentX;
	}

	@Override
	public float getCurrentY() {
		return this.currentY;
	}

	@Override
	public float getNextX() {
		return this.nextX;
	}

	@Override
	public float getNextY() {
		return this.nextY;
	}

	public boolean equal(Function function, float phase) {
		boolean equal = true;
		
		for (int x = 0; x < Constants.TONE_WIDTH; x++) {
			function.calculate(x, phase);
			this.calculate(x, 0);
			
			equal = equal && Math.abs(function.getCurrentY() - this.getCurrentY()) < 0.2;
			
		}
		
		return equal;
	}
			
}
