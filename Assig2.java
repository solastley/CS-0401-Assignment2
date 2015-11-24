//Solomon Astley, #3938540
//Ramirez CS 0401 Assignment 2, Lab Thurs 10:00 Session
//This program simulates the gambling game Over and Under

import java.util.*;
import java.io.*;
import java.text.*;

public class project2
{
	public static void main(String [] args) throws IOException
	{
		String first_name, last_name, new_round, user_choice;
		double tot_money, bet_amt, original_money;
		int rounds_played, rounds_won, die_roll1, die_roll2, sum_dice, original_rounds_played, original_rounds_won;
		boolean False = (3 < 0);
		
		//Allows for good formatting for the money values
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
		
		//creates a scanner object for user input
		Scanner inScan = new Scanner(System.in);
		//creates two Die objects in order to simulate random rolls
		Die die1 = new Die();
		Die die2 = new Die();
	
		System.out.println("Welcome to Over and Under");
		System.out.println("What is your first name?");
		first_name = inScan.next();
		System.out.println();
		
		//creates new File object based on user's first name
		File filename = new File(first_name + ".txt");
		
		//if the file already exists:
		if (filename.exists())
		{
			System.out.println("Welcome back, " + first_name + "!");
			System.out.println();
			
			//creates a scanner object and scans in all of the user's information from the file
			Scanner fileIn = new Scanner(filename);
			first_name = fileIn.nextLine();
			last_name = fileIn.nextLine();
			tot_money = Double.parseDouble(fileIn.nextLine());
			rounds_played = Integer.parseInt(fileIn.nextLine());
			rounds_won = Integer.parseInt(fileIn.nextLine());
			
			//closes file to flush buffer
			fileIn.close();
			
			//creates variables for the player's info before they play any games
			original_money = tot_money;
			original_rounds_played = rounds_played;
			original_rounds_won = rounds_won;
			
			//creates a Player object to manipulate and store player's data throughout the game
			Player new_player = new Player(first_name, last_name, tot_money, rounds_played, rounds_won);
			//prints out player's current information in a nice fashion
			System.out.println(new_player.toString());
			
			System.out.println();
			System.out.print("Would you like to play a round? (y/n): ");
			new_round = inScan.next();
			
			while (new_round.equals("y") == False && new_round.equals("Y") == False && new_round.equals("n") == False && new_round.equals("N") == False)
			{
				System.out.print("Would you like to play a round? (y/n): ");
				new_round = inScan.next();
			}
			
			System.out.println();
			
			//while the user chooses to continue playing and has some amount of money:
			while ((new_round.equals("y") || new_round.equals("Y")) && tot_money > 0)
			{
				do {
				System.out.print("How much would you like to bet? (<= " + formatter.format(tot_money) + "): ");
				bet_amt = inScan.nextDouble();
				
				//if bet is equal to zero, don't simulate the game
				if (bet_amt == 0)
				{
					break;
				}
				
				} while (bet_amt > tot_money || bet_amt < 0);
				
				do {
				System.out.print("Select your choice: (O)ver, (U)nder, (S)even > ");
				user_choice = inScan.next();
				} while (!user_choice.equals("O") && !user_choice.equals("o") && !user_choice.equals("U") && !user_choice.equals("u") && !user_choice.equals("S") && !user_choice.equals("s"));
				
				//creates two random numbers from one to six and adds them for the total roll
				die_roll1 = die1.rollDice();
				die_roll2 = die2.rollDice();
				sum_dice = die_roll1 + die_roll2;
				
				System.out.println("The dice have been rolled... and the result is...");
				System.out.println("Die 1: " + die_roll1 + " Die 2: " + die_roll2 + " => Total: " + sum_dice);
				
				//series of if, else statements to simulate various end-game scenarios and manipulate player data accordingly
				if (sum_dice < 7)
				{
					System.out.println("...UNDER!...");
					if (user_choice.equals("U") || user_choice.equals("u"))
					{
						System.out.println("You have WON this round!");
						tot_money = new_player.addMoney(bet_amt);
						rounds_played = new_player.playedAGame();
						rounds_won = new_player.wonAGame();
						
						System.out.println("You won " + formatter.format(bet_amt));
						System.out.println("Your updated money value is " + formatter.format(tot_money));
					}
					else
					{
						System.out.println("You have LOST this round!");
						tot_money = new_player.subtractMoney(bet_amt);
						rounds_played = new_player.playedAGame();
						
						System.out.println("You lost " + formatter.format(bet_amt));
						System.out.println("Your updated money value is " + formatter.format(tot_money));
					}
				}
				
				else if (sum_dice == 7)
				{
					System.out.println("...SEVEN!...");
					if (user_choice.equals("S") || user_choice.equals("s"))
					{
						System.out.println("You have WON this round!");
						tot_money = new_player.addMoney(bet_amt * 4);
						rounds_played = new_player.playedAGame();
						rounds_won = new_player.wonAGame();
						
						System.out.println("You won " + formatter.format((bet_amt * 4)));
						System.out.println("Your updated money value is " + formatter.format(tot_money));
					}
					else
					{
						System.out.println("You have LOST this round!");
						tot_money = new_player.subtractMoney(bet_amt);
						rounds_played = new_player.playedAGame();
						
						System.out.println("You lost " + formatter.format(bet_amt));
						System.out.println("Your updated money value is " + formatter.format(tot_money));
					}
				}
				
				else
				{
					System.out.println("...OVER!...");
					if (user_choice.equals("O") || user_choice.equals("o"))
					{
						System.out.println("You have WON this round!");
						tot_money = new_player.addMoney(bet_amt);
						rounds_played = new_player.playedAGame();
						rounds_won = new_player.wonAGame();
						
						System.out.println("You won " + formatter.format(bet_amt));
						System.out.println("Your updated money value is " + formatter.format(tot_money));
					}
					else
					{
						System.out.println("You have LOST this round!");
						tot_money = new_player.subtractMoney(bet_amt);
						rounds_played = new_player.playedAGame();
						
						System.out.println("You lost " + formatter.format(bet_amt));
						System.out.println("Your updated money value is " + formatter.format(tot_money));
					}
				}
				
				System.out.println();
				
				//if player still has money left:
				if (tot_money > 0)
				{
					do {
						System.out.print("Would you like to play another round? (y/n): ");
						new_round = inScan.next();
					} while(new_round.equals("y") == False && new_round.equals("Y") == False && new_round.equals("n") == False && new_round.equals("N") == False);
				}
			}
			
			//if player has no money to play with:
			if (tot_money == 0)
			{
				System.out.println("Sorry, you don't have any money left to play with!");
			}
			
			System.out.println();
			System.out.println("Thank you for playing Over and Under!");
			System.out.println();
			//prints out player's new info in a nice fashion
			System.out.println(new_player.toString());
			System.out.println();
			
			//if player lost money this game:
			if (original_money - tot_money > 0)
			{
				System.out.println("During this game you lost " + formatter.format(original_money - tot_money));
			}
			//if player won money this game:
			else
			{
				System.out.println("During this game you earned " + formatter.format(original_money - tot_money));
			}
			
			System.out.println("During this game you won " + (rounds_won - original_rounds_won) + " games out of " + (rounds_played - original_rounds_played));
			
			//deletes old file
			filename.delete();
			//creates new file with same name as old file
			File filename2 = new File(first_name + ".txt");
			
			//prints new player information to new file
			PrintWriter fileOut = new PrintWriter(filename2);
			fileOut.println(first_name);
			fileOut.println(last_name);
			fileOut.println(tot_money);
			fileOut.println(rounds_played);
			fileOut.println(rounds_won);
			fileOut.close();
		}
		//if file with player's first name does not already exist:
		else
		{
			//prompts user for initial information
			System.out.println("I see it's your first time here. Please enter your info below: ");
			System.out.println("Last name:");
			last_name = inScan.next();
		
			System.out.println("Initial money:");
			tot_money = inScan.nextDouble();
			System.out.println();
			
			original_money = tot_money;
		
			rounds_played = 0;
			rounds_won = 0;
		
			//creates Player object to manipulate and store data
			Player new_player = new Player(first_name, last_name, tot_money, rounds_played, rounds_won);
			System.out.println(new_player.toString());
			
			System.out.println();
			System.out.print("Would you like to play a round? (y/n): ");
			new_round = inScan.next();
			
			while (new_round.equals("y") == False && new_round.equals("Y") == False && new_round.equals("n") == False && new_round.equals("N") == False)
			{
				System.out.print("Would you like to play a round? (y/n): ");
				new_round = inScan.next();
			}
			
			System.out.println();
			
			//while player continues to choose to play and has some amount of money:
			while ((new_round.equals("y") || new_round.equals("Y")) && tot_money > 0)
			{
				do {
				System.out.print("How much would you like to bet? (<= " + formatter.format(tot_money) + "): ");
				bet_amt = inScan.nextDouble();
				
				//if bet is equal to zero, do not simulate game
				if (bet_amt == 0)
				{
					break;
				}
				
				} while (bet_amt > tot_money || bet_amt < 0);
				
				do {
				System.out.print("Select your choice: (O)ver, (U)nder, (S)even > ");
				user_choice = inScan.next();
				} while (!user_choice.equals("O") && !user_choice.equals("o") && !user_choice.equals("U") && !user_choice.equals("u") && !user_choice.equals("S") && !user_choice.equals("s"));
				
				//generates random values from 1 to 6 and adds them for roll total
				die_roll1 = die1.rollDice();
				die_roll2 = die2.rollDice();
				sum_dice = die_roll1 + die_roll2;
				
				System.out.println("The dice have been rolled... and the result is...");
				System.out.println("Die 1: " + die_roll1 + " Die 2: " + die_roll2 + " => Total: " + sum_dice);
				
				//series of if, else statements to simulate various end-game scenarios and manipulate player data accordingly
				if (sum_dice < 7)
				{
					System.out.println("...UNDER!...");
					if (user_choice.equals("U") || user_choice.equals("u"))
					{
						System.out.println("You have WON this round!");
						tot_money = new_player.addMoney(bet_amt);
						rounds_played = new_player.playedAGame();
						rounds_won = new_player.wonAGame();
						
						System.out.println("You won " + formatter.format(bet_amt));
						System.out.println("Your updated money value is " + formatter.format(tot_money));
					}
					else
					{
						System.out.println("You have LOST this round!");
						tot_money = new_player.subtractMoney(bet_amt);
						rounds_played = new_player.playedAGame();
						
						System.out.println("You lost " + formatter.format(bet_amt));
						System.out.println("Your updated money value is " + formatter.format(tot_money));
					}
				}
				
				else if (sum_dice == 7)
				{
					System.out.println("...SEVEN!...");
					if (user_choice.equals("S") || user_choice.equals("s"))
					{
						System.out.println("You have WON this round!");
						tot_money = new_player.addMoney(bet_amt * 4);
						rounds_played = new_player.playedAGame();
						rounds_won = new_player.wonAGame();
						
						System.out.println("You won " + formatter.format((bet_amt * 4)));
						System.out.println("Your updated money value is " + formatter.format(tot_money));
					}
					else
					{
						System.out.println("You have LOST this round!");
						tot_money = new_player.subtractMoney(bet_amt);
						rounds_played = new_player.playedAGame();
						
						System.out.println("You lost " + formatter.format(bet_amt));
						System.out.println("Your updated money value is " + formatter.format(tot_money));
					}
				}
				
				else
				{
					System.out.println("...OVER!...");
					if (user_choice.equals("O") || user_choice.equals("o"))
					{
						System.out.println("You have WON this round!");
						tot_money = new_player.addMoney(bet_amt);
						rounds_played = new_player.playedAGame();
						rounds_won = new_player.wonAGame();
						
						System.out.println("You won " + formatter.format(bet_amt));
						System.out.println("Your updated money value is " + formatter.format(tot_money));
					}
					else
					{
						System.out.println("You have LOST this round!");
						tot_money = new_player.subtractMoney(bet_amt);
						rounds_played = new_player.playedAGame();
						
						System.out.println("You lost " + formatter.format(bet_amt));
						System.out.println("Your updated money value is " + formatter.format(tot_money));
					}
				}
				
				System.out.println();
				
				//if player still has money left:
				if (tot_money > 0)
				{
					do {
						System.out.print("Would you like to play another round? (y/n): ");
						new_round = inScan.next();
					} while(new_round.equals("y") == False && new_round.equals("Y") == False && new_round.equals("n") == False && new_round.equals("N") == False);
				}
			}
			
			//if player has no money left:
			if (tot_money == 0)
			{
				System.out.println("Sorry, you don't have any money left to play with!");
			}
			
			System.out.println();
			System.out.println("Thank you for playing Over and Under!");
			System.out.println();
			//prints out player's info in a nice fashion
			System.out.println(new_player.toString());
			System.out.println();
			
			//if player lost money this game:
			if (original_money - tot_money > 0)
			{
				System.out.println("During this game you lost " + formatter.format(original_money - tot_money));
			}
			
			//if player gained money this game:
			else
			{
				System.out.println("During this game you earned " + formatter.format(original_money - tot_money));
			}
			
			System.out.println("During this game you won " + rounds_won + " games out of " + rounds_played);
			
			//writes the player's updated information to a file with the player's name
			PrintWriter fileOut = new PrintWriter(filename);
			fileOut.println(first_name);
			fileOut.println(last_name);
			fileOut.println(tot_money);
			fileOut.println(rounds_played);
			fileOut.println(rounds_won);
			fileOut.close();
		}
	}
}
