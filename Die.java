//Solomon Astley, #3938540
//Ramirez CS 0401 Assignment 2, Lab Thurs 10:00 Session
//This class simulates a die roll

import java.util.*;

public class Die
{
	//creates a Random object to simulate die rolls
	Random rand = new Random();
	private int die_roll;
	
	//mutator to simulate die roll
	public int rollDice()
	{
		//do-while loop to make sure the simulated die roll is not equal to zero
		do
		{
			die_roll = rand.nextInt(7);
		} while (die_roll == 0);
		
		//returns die roll
		return die_roll;
	}
}