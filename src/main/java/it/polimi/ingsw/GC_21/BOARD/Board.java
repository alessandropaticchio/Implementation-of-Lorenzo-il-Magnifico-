package it.polimi.ingsw.GC_21.BOARD;



import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;


public class Board {
	private Game game;
	private Dice[] dices;
	private Tower[] towers;
	private MarketArea marketArea;
	private CouncilPalace councilPalace;
	private CraftArea productionArea;
	private CraftArea harvestArea;
	
	public Board(Game game) {
		this.dices = Dice.factoryDices();
		this.game=game;
		this.towers = Tower.factoryTowers(game); 
		this.marketArea = new MarketArea(game);
		this.councilPalace = new CouncilPalace(game);
		this.productionArea = new CraftArea(CraftType.Production, game);
		this.harvestArea = new CraftArea( CraftType.Harvest, game);
	}



	public void placeCardOnBoard(DevDeck territoryDeck, DevDeck buildingDeck, DevDeck characterDeck, DevDeck ventureDeck){
		towers[0].pickCards(territoryDeck);
		towers[1].pickCards(buildingDeck);
		towers[2].pickCards(characterDeck);
		towers[3].pickCards(ventureDeck);
	}



	/*public void refreshBoard() {
		for (int i = 0; i < towers.length; i++) {
			for (int j = 0; j < towers[i].getFloors().length; j++) {
				towers[i].getFloors()[j].getSingleActionSpace().setFamilyMember(null);;
			}
		}
		for (int i = 0; i < towers.length; i++) {
			for (int j = 0; j < towers[i].getFloors().length; j++) {
				towers[i].getFloors()[j].getDevCardPlace().setCard(null);
			}
		}	
		for (int j = 0; j < craftAreas.length; j++) {
			for (int i = 0; i < craftAreas[i].getMultipleActionSpace().getFamilyMember().size(); i++) {
				craftAreas[i].getMultipleActionSpace().getFamilyMember().remove(i);		

			}
			craftAreas[j].getSingleActionSpace().setFamilyMember(null);
		}
		for (int i = 0; i < councilPalace.getMultipleActionSpace().getFamilyMember().size(); i++) {
			councilPalace.getMultipleActionSpace().getFamilyMember().remove(i);
			
		}
		for (int i = 0; i < marketArea.getSingleActionSpace().length; i++) {
			marketArea.getSingleActionSpace()[i].setFamilyMember(null);
		}
		
		for (int j = 0; j < dices.length; j++) {
			//<todo>
		}
		
	
	}*/
	
	

	public Dice[] getDices() {
		return dices;
	}



	public void setDices(Dice[] dices) {
		this.dices = dices;
	}



	public Tower[] getTowers() {
		return towers;
	}
	
	public Tower getSpecificTower(DevCardType towerType) {
		for (int i = 0; i < towers.length; i++) {
			if (towers[i].getDevCardType().equals(towerType)) {
				return towers[i];
			}
		}
		return null;
	}



	public void setTowers(Tower[] towers) {
		this.towers = towers;
	}



	public MarketArea getMarketArea() {
		return marketArea;
	}



	public void setMarketArea(MarketArea marketArea) {
		this.marketArea = marketArea;
	}



	public CouncilPalace getCouncilPalace() {
		return councilPalace;
	}



	public void setCouncilPalace(CouncilPalace councilPalace) {
		this.councilPalace = councilPalace;
	}
	
	public CraftArea getSpecificCraftArea(CraftType craftType) {
		if (craftType.equals(CraftType.Harvest)) {
			return getHarvestArea();
		}
		else if (craftType.equals(CraftType.Production)) {
			return getProductionArea();
		}
		else {
			return null;
		}
	}



	public CraftArea getProductionArea() {
		return productionArea;
	}



	public void setProductionArea(CraftArea productionArea) {
		this.productionArea = productionArea;
	}



	public CraftArea getHarvestArea() {
		return harvestArea;
	}



	public void setHarvestArea(CraftArea harvestArea) {
		this.harvestArea = harvestArea;
	}


	public String toString() {
		return dices[0].toString() + " \n " + dices[1].toString() + " \n " + dices[2].toString() + " \n " + towers[0].toString() + " \n " + towers[1].toString() + " \n " + 
				towers[2].toString() + " \n " + towers[3].toString() + " \n " + marketArea.toString() +" \n " + productionArea.toString() + " \n " + harvestArea.toString()
				+ " \n " + councilPalace.toString();
	}




	
}