package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.Serializable;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import it.polimi.ingsw.GC_21.BOARD.Dice;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;

public class Round implements Serializable{

	private int roundNumber;
	private Game game;
	private Player blackPlayer;
	
	public Round(int roundNumber, Game game) {
		this.roundNumber = roundNumber;
		this.game = game;
		
		}
	
	private void setTurnOrder() {
		ArrayList<Player> turnOrder = game.getBoard().getCouncilPalace().getTurnOrder();
		game.setThisTurnOrder(turnOrder);
		blackPlayer = game.getSpecificPlayer(Color.Black);
		ArrayList<Player> playersInGame = game.getPlayers();	
		for (int j = 0; j < playersInGame.size(); j++) {
			if (!turnOrder.contains(playersInGame.get(j))) {
				turnOrder.add(playersInGame.get(j));
			}
		}
	}

	public void executeRound() {
		if (!game.isSavedGame()) {
			setTurnOrder();
			game.getBoard().refreshBoard();
			for (int i = 0; i < game.getPlayers().size(); i++) {
				game.getPlayers().get(i).refreshPlayer();
			}
			this.placeCard();
		}
		else {
			game.setSavedGame(false);
		}
		if (blackPlayer != null && game.isBlackTurn()) {
			game.getTurnOrder().remove(blackPlayer);//for black player there is a specific notify turn in which he plays all his family members at the beginning 
			for (int i = game.getCurrentTurn().getTurnNumber(); i < 5; i++) {
				Turn currentTurn = new Turn(i, game);
				game.setCurrentTurn(currentTurn);
				game.notifyBlackTurn(blackPlayer);
			}
			game.setBlackTurn(false);
			game.setCurrentTurn(new Turn(1, game));
		}		
		for (int i = game.getCurrentTurn().getTurnNumber(); i < 5 ; i++) {
			Turn currentTurn = new Turn(i, game);
			game.setCurrentTurn(currentTurn);
			currentTurn.executeView(game.getTurnOrder());
		}
		game.setBlackTurn(true);
		game.setCurrentTurn(new Turn(1, game));
		game.getBoard().setDices(Dice.factoryDices());
		for (int i = 0; i < game.getNumberOfPlayers(); i++) {
			game.getPlayers().get(i).setFamilyMembers(FamilyMember.factoryFamilyMembers(game.getBoard().getDices(), game.getPlayers().get(i)));
		}
	}
	
	public void placeCard() {
		game.getBoard().getSpecificTower(DevCardType.Building).pickCards(game.getCurrentAge().buildingDeck);  
		game.getBoard().getSpecificTower(DevCardType.Territory).pickCards(game.getCurrentAge().territoryDeck);  
		game.getBoard().getSpecificTower(DevCardType.Venture).pickCards(game.getCurrentAge().ventureDeck);  
		game.getBoard().getSpecificTower(DevCardType.Character).pickCards(game.getCurrentAge().characterDeck);  
	
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	
	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	

	
	
	
	
}