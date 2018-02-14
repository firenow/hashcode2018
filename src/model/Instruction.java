package model;

public class Instruction {

	public String command;
	public int turnNb;
	
	public Instruction(int turnNb, String command) {
		this.turnNb = turnNb;
		this.command = command;
	}
}
