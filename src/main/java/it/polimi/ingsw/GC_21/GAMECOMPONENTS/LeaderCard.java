package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import it.polimi.ingsw.GC_21.PLAYER.Player;

public abstract class LeaderCard extends Card {
	protected int numberOfVenturesRequired;
	protected int numberOfCharactersRequired;
	protected int numberOfBuildingRequired;
	protected int numberOfTerritoryRequired;
	protected Possession requirements;
	protected boolean played;




	public LeaderCard(String ID, String name, int numberOfVenturesRequired, int numberOfCharactersRequired,
			int numberOfBuildingRequired, int numberOfTerritoryRequired, Possession requirements, boolean played) {
		super(name);
		this.ID = ID;
		this.numberOfVenturesRequired = numberOfVenturesRequired;
		this.numberOfCharactersRequired = numberOfCharactersRequired;
		this.numberOfBuildingRequired = numberOfBuildingRequired;
		this.numberOfTerritoryRequired = numberOfTerritoryRequired;
		this.requirements = requirements;
		this.played = played;
	}



	public boolean checkRequirements(Player player){
		return player.getMyPersonalBoard().getMyPossession().compare(requirements) && this.checkOnCards(player);
	}



	private boolean checkOnCards(Player player) {
		boolean enoughVentures = player.getMyPersonalBoard().getSpecificOwnedCards(DevCardType.Venture).getOwnedCardsnumber() >= numberOfVenturesRequired;
		boolean enoughTerritories = player.getMyPersonalBoard().getSpecificOwnedCards(DevCardType.Territory).getOwnedCardsnumber() >= numberOfTerritoryRequired;
		boolean enoughCharacters = player.getMyPersonalBoard().getSpecificOwnedCards(DevCardType.Character).getOwnedCardsnumber() >= numberOfCharactersRequired;
		boolean enoughBuildings = player.getMyPersonalBoard().getSpecificOwnedCards(DevCardType.Building).getOwnedCardsnumber() >= numberOfBuildingRequired;
		return enoughBuildings && enoughCharacters && enoughVentures && enoughTerritories;
	}



	public String getName() {
		return name;
	}

	public int getNumberOfVenturesRequired() {
		return numberOfVenturesRequired;
	}



	public void setNumberOfVenturesRequired(int numberOfVenturesRequired) {
		this.numberOfVenturesRequired = numberOfVenturesRequired;
	}



	public int getNumberOfCharactersRequired() {
		return numberOfCharactersRequired;
	}



	public void setNumberOfCharactersRequired(int numberOfCharactersRequired) {
		this.numberOfCharactersRequired = numberOfCharactersRequired;
	}



	public int getNumberOfBuildingRequired() {
		return numberOfBuildingRequired;
	}



	public void setNumberOfBuildingRequired(int numberOfBuildingRequired) {
		this.numberOfBuildingRequired = numberOfBuildingRequired;
	}



	public int getNumberOfTerritoryRequired() {
		return numberOfTerritoryRequired;
	}



	public void setNumberOfTerritoryRequired(int numberOfTerritoryRequired) {
		this.numberOfTerritoryRequired = numberOfTerritoryRequired;
	}





	public boolean isPlayed() {
		return played;
	}



	public void setPlayed(boolean played) {
		this.played = played;
	}



	public void callEffect(Player player) {		
	}

}
