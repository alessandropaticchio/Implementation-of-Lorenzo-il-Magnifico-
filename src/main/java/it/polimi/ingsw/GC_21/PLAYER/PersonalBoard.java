package it.polimi.ingsw.GC_21.PLAYER;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.OwnedCards;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.EffectType;
import it.polimi.ingsw.GC_21.EFFECT.ToCallBeforeCraft;
import it.polimi.ingsw.GC_21.EFFECT.ToCallBeforePlacement;
import it.polimi.ingsw.GC_21.EFFECT.ToCallDuringCraft;
import it.polimi.ingsw.GC_21.EFFECT.ToCallWhenEarning;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class PersonalBoard implements Serializable{
	private Game game;
	private final OwnedCards[] myOwnedCards;
	private final Possession craftMinimumReward;
	private Possession myPossession;
	private final Player player;
	private ArrayList<ToCallBeforeCraft> toCallBeforeCraftEffects;
	private ArrayList<ToCallBeforePlacement> toCallBeforePlacementEffects;
	private ArrayList<ToCallWhenEarning> toCallWhenEarningEffects;
	private ArrayList<LeaderCard> leaderCards;
	private ArrayList<OncePerTurnLeaderCard> playedOncePerTurnLeaderCards;

	public ArrayList<ToCallWhenEarning> getToCallWhenEarningEffects() {
		return toCallWhenEarningEffects;
	}

	public void setToCallWhenEarningEffects(ArrayList<ToCallWhenEarning> toCallWhenEarningEffects) {
		this.toCallWhenEarningEffects = toCallWhenEarningEffects;
	}

	public PersonalBoard(Player player, Game game) {
		this.myOwnedCards = OwnedCards.factoryOwnedCards();
		this.myPossession = new Possession(0, 0, 0, 0, 0, 0, 0);
		this.craftMinimumReward = new Possession(1,1,1,1,1,1,1);
		this.player = player;
		this.toCallBeforeCraftEffects= new ArrayList<ToCallBeforeCraft>();
		this.toCallBeforePlacementEffects = new ArrayList<ToCallBeforePlacement>();
		this.toCallWhenEarningEffects = new ArrayList<ToCallWhenEarning>();
		this.leaderCards = new ArrayList<LeaderCard>();
		this.playedOncePerTurnLeaderCards = new ArrayList<OncePerTurnLeaderCard>();
		this.pickLeaderCards(game.getLeaderDeck());
	}
	
	public void addDevCard(DevelopmentCard devCard) {
		OwnedCards tmpCardType = getSpecificOwnedCards(devCard.getDevCardType());
		tmpCardType.add(devCard);
		}
	 
	public void pickLeaderCards(LeaderDeck leaderDeck) {
		this.leaderCards.add((LeaderCard) leaderDeck.getSingleCard());
		this.leaderCards.add((LeaderCard) leaderDeck.getSingleCard());
		this.leaderCards.add((LeaderCard) leaderDeck.getSingleCard());
		this.leaderCards.add((LeaderCard) leaderDeck.getSingleCard());
	}
	

	public void addPermanentEffect(Effect effect){
		if (effect instanceof ToCallBeforeCraft){
			toCallBeforeCraftEffects.add((ToCallBeforeCraft) effect);
		}
		else if (effect instanceof ToCallBeforePlacement){
			toCallBeforePlacementEffects.add((ToCallBeforePlacement)effect);
		}
		else if (effect instanceof ToCallWhenEarning){
			toCallWhenEarningEffects.add((ToCallWhenEarning) effect);
		}
	}
	
	public void payPossession(Possession possession){
		if (possession!= null){
			myPossession.subtract(possession);
		}
	}

	public void activateCraft(CraftType craftType, int actionValue) {
		if(craftType.equals(CraftType.Production)) {
			OwnedCards ownedBuildingCardsCards = getSpecificOwnedCards(DevCardType.Building);
			for (int i = 0; i < ownedBuildingCardsCards.getOwnedCardsnumber(); i++) {
				CraftCard tmp = (CraftCard) ownedBuildingCardsCards.getMyOwnedCards()[i].getCard();
				if(actionValue >=  tmp.getRequiredValueForCraft()) {
					tmp.callCraftEffect(player);
				}
			}
		} else if (craftType.equals(CraftType.Harvest)) {
			OwnedCards ownedTerritoryCards = getSpecificOwnedCards(DevCardType.Territory);
			for (int i = 0; i < ownedTerritoryCards.getOwnedCardsnumber(); i++) {
				CraftCard tmp = (CraftCard) ownedTerritoryCards.getMyOwnedCards()[i].getCard();
				if(actionValue >=  tmp.getRequiredValueForCraft()) {
					tmp.callCraftEffect(player);
				}
			}
		}
	}
	
	public OwnedCards getSpecificOwnedCards(DevCardType devCardType){ //get a specific OwnedCards of the same type of the devCard 
		int i = 0;
		while (!myOwnedCards[i].getOwnedCardsType().equals(devCardType) && i < myOwnedCards.length){
			i++;
		}
		return myOwnedCards[i];	
	}


	public Player getPlayer() {
		return player;
	}


	public ArrayList<ToCallBeforeCraft> getToCallBeforeCraftEffects() {
		return toCallBeforeCraftEffects;
	}

	public void setToCallBeforeCraftEffects(ArrayList<ToCallBeforeCraft> toCallBeforeCraftEffects) {
		this.toCallBeforeCraftEffects = toCallBeforeCraftEffects;
	}

	public ArrayList<ToCallBeforePlacement> getToCallBeforePlacementEffects() {
		return toCallBeforePlacementEffects;
	}

	public void setToCallBeforePlacementEffects(ArrayList<ToCallBeforePlacement> toCallBeforePlacementEffects) {
		this.toCallBeforePlacementEffects = toCallBeforePlacementEffects;
	}

	public OwnedCards[] getMyOwnedCards() {
		return myOwnedCards;
	}

	public Possession getCraftMinimumReward() {
		return craftMinimumReward;
	}

	public Possession getMyPossession() {
		return myPossession;
	}

	public void setMyPossession(Possession myPossession) {
		this.myPossession = myPossession;
	}

	
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ArrayList<LeaderCard> getLeaderCards() {
		return leaderCards;
	}

	public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
		this.leaderCards = leaderCards;
	}

	public ArrayList<OncePerTurnLeaderCard> getPlayedOncePerTurnLeaderCards() {
		return playedOncePerTurnLeaderCards;
	}

	public void setPlayedOncePerTurnLeaderCards(ArrayList<OncePerTurnLeaderCard> playedOncePerTurnLeaderCards) {
		this.playedOncePerTurnLeaderCards = playedOncePerTurnLeaderCards;
	}

	@Override
	public String toString() {
		String myOwnedCardString = "";
		for (int i = 0; i < myOwnedCards.length; i++) {
			myOwnedCardString = myOwnedCardString + myOwnedCards[i].toString() + "\n";
		}
		return player.getName() + " PersonalBoard \n{myPossession=" + myPossession.toString() + "\nmy Development Cards: \n" + myOwnedCardString + "\nmy Leader Cards: " + this.leaderCards.toString() +"}";
	}
	
	


}