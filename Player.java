//Solomon Astley, #3938540
//Ramirez CS 0401 Assignment 2, Lab Thurs 10:00 Session
//This class stores information about the player

import java.util.*;
import java.text.*;

public class Player
{
	//allows for good formatting of money values
	NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
	
	private String first_name;
	private String last_name;
	private double tot_money;
	private int rounds_played;
	private int rounds_won;
	
	//constructor to initialize private variables from arguments
	public Player(String first, String last, double tot, int played, int won)
	{
		first_name = first;
		last_name = last;
		tot_money = tot;
		rounds_played = played;
		rounds_won = won;
	}
	
	//overrides constructor
	public Player()
	{}
	
	//toString accessor to print out player's information in a nice fashion
	public String toString()
	{
		return("Here is your updated information: \nName: " + first_name + " " + last_name + "\nMoney Left: " + formatter.format(tot_money) + "\nRounds Played: " + rounds_played + "\nRounds Won: " + rounds_won);
	}
	
	//mutator to change the user's total money
	public double addMoney(double added_money)
	{
		tot_money = tot_money + added_money;
		return tot_money;
	}
	
	//mutator to change the user's total money
	public double subtractMoney(double subtracted_money)
	{
		tot_money = tot_money - subtracted_money;
		return tot_money;
	}
	
	//mutator to change number of games user has won
	public int wonAGame()
	{
		rounds_won++;
		return rounds_won;
	}
	
	//mutator to change number of games user has played
	public int playedAGame()
	{
		rounds_played++;
		return rounds_played;
	}
}