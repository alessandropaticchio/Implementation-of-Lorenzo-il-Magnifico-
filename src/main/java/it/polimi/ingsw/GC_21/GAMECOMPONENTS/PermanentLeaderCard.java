package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import it.polimi.ingsw.GC_21.EFFECT.DontCheckMP;
import it.polimi.ingsw.GC_21.EFFECT.DontSpend3Coins;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Permanent;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PermanentLeaderCard extends LeaderCard{
	private Permanent permanentEffect;
	
	@Override
	public void callEffect(Player player) {
		Effect effectToAdd = (Effect) permanentEffect;
		if (effectToAdd instanceof DontSpend3Coins || effectToAdd instanceof DontCheckMP){
			effectToAdd.activateEffect(player, null);
		}
		else{
			player.getMyPersonalBoard().addPermanentEffect(effectToAdd);
		}
	}

	public PermanentLeaderCard(String ID, String name, int numberOfVenturesRequired, int numberOfCharactersRequired,
			int numberOfBuildingRequired, int numberOfTerritoryRequired, Possession requirements, boolean played,
			Permanent permanentEffect) {
		super(ID, name, numberOfVenturesRequired, numberOfCharactersRequired, numberOfBuildingRequired,
				numberOfTerritoryRequired, requirements, played);
		this.permanentEffect = permanentEffect;
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


	public Permanent getPermanentEffect() {
		return permanentEffect;
	}

	public void setPermanentEffect(Permanent permanentEffect) {
		this.permanentEffect = permanentEffect;
	}


}
