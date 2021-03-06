package it.polimi.ingsw.GC_21.PLAYER;

import java.io.Serializable;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class Player implements Serializable{

	private FamilyMember[] familyMembers;
	private String name;
	private Color playerColor;
	private PersonalBoard myPersonalBoard;
	private Game game;
	private boolean checkOnMP;
	private boolean overchargeOnBusyTower;
	private int blackPoint;

	public Player(String name, Color playerColor, Game game) {
		this.familyMembers = FamilyMember.factoryFamilyMembers(game.getBoard().getDices(), this);
		this.name = name;
		this.game = game;
		this.playerColor = playerColor;
		this.myPersonalBoard = new PersonalBoard(this, game);
		this.familyMembers = FamilyMember.factoryFamilyMembers(game.getBoard().getDices(), this);
		this.checkOnMP = false;
		this.overchargeOnBusyTower = false;
		this.game.addPlayers(this);
	}
	
	public void guessedPosition() {
		blackPoint ++;
	}

	public void refreshPlayer() {
		for (int i = 0; i < familyMembers.length; i++) {
			familyMembers[i].setPlaced(false);
		}
	}
	
	

	public boolean isCheckOnMP() {
		return checkOnMP;
	}


	public void setCheckOnMP(boolean checkOnMP) {
		this.checkOnMP = checkOnMP;
	}


	public boolean isOverchargeOnBusyTower() {
		return overchargeOnBusyTower;
	}


	public void setOverchargeOnBusyTower(boolean overchargeOnBusyTower) {
		this.overchargeOnBusyTower = overchargeOnBusyTower;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}

	public PersonalBoard getMyPersonalBoard() {
		return myPersonalBoard;
	}

	public void setMyPersonalBoard(PersonalBoard myPersonalBoard) {
		this.myPersonalBoard = myPersonalBoard;
	}
	

	
	
	public FamilyMember getSpecificFamilyMember(FamilyMemberColor familyMemberColor) {
		for (int i = 0; i < familyMembers.length; i++) {
			if (familyMembers[i].getFamilyMemberColor().equals(familyMemberColor)) {
				return familyMembers[i];
			}
		}
		return null;
	}

	

	public FamilyMember[] getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(FamilyMember[] familyMembers) {
		this.familyMembers = familyMembers;
	}

	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	
	public int getBlackPoint() {
		return blackPoint;
	}

	public void setBlackPoint(int blackPoint) {
		this.blackPoint = blackPoint;
	}

	@Override
	public String toString() {
		return "Player [Name=" + name + ", playerColor=" + playerColor + "]";
	}


}