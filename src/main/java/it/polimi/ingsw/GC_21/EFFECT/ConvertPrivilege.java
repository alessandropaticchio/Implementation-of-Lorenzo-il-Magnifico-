package it.polimi.ingsw.GC_21.EFFECT;

import java.awt.HeadlessException;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Privileges;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ConvertPrivilege extends Immediate {
	private boolean coinsEarned;
	private boolean woodsAndStonesEarned;
	private boolean servantsEarned;
	private boolean militaryPointsEarned;
	private boolean faithPointsEarned;
	private final Possession woodsAndStonesReward;
	private final Possession servantsReward;
	private final Possession coinsReward;
	private final Possession militaryPointsReward;
	private final Possession faithPointsReward;
	
	public ConvertPrivilege(Possession rewards) {
		super(rewards);
	}

	@Override
	public void activateEffect(Player player) {
		/* The method ask the player to choose the reward, if it's valid then 
		he gets it, if it's not then another cycle is done*/
		for (int i = this.rewards.getPrivileges().getValue(); i > 0; i--) {
			Possession tmpPossession = this.chooseReward();
			if (validConversion(tmpPossession) == true ){
				this.rewards.add(tmpPossession);
				setEarnedReward(tmpPossession);
				//TODO: se prendo una possession devo settare il rispettivo boolean a true e poi devo gestire il controllo!!
			}
			else {
				i++;
			}
			}
			super.activateEffect(player);
	}
	

	
	
	public Possession chooseReward(){
		Scanner in = new Scanner(System.in);
		System.out.println("Choose your reward! Type:");
		System.out.println("1 -> 1x Woods 1x Stones");
		System.out.println("2 -> 2x Servants");
		System.out.println("3 -> 2x Coins");		
		System.out.println("4 -> 2x Military Points");
		System.out.println("5 -> 1x Faith Points");
		int choice = in.nextInt();
		switch(choice){
		case 1: choice = 1;
		return this.woodsAndStonesReward;
		case 2: choice = 2;
		return this.servantsReward;
		case 3: choice = 3;
		return this.coinsReward;
		case 4: choice = 4;
		return this.militaryPointsReward;
		case 5: choice = 5;
		return this.faithPointsReward;
		default: 
			System.out.println("Invalid choice! Try again!");
			return chooseReward();
		}
	}
	
	public void setEarnedReward(Possession reward) {
		
		
	}
	
	public boolean validConversion(Possession possession){
		
	}

}