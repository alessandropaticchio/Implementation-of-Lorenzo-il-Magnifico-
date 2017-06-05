package it.polimi.ingsw.GC_21.BOARD;

import java.util.*;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;

public class Tower {

	private final DevCardType devCardType;
	private Floor[] floors = new Floor[4];

	private Tower(DevCardType devCardType) {
		this.devCardType = devCardType;
		for (int i = 0; i < floors.length; i++) {
			floors[i]=new Floor(i+1);
		}
	}

	public static Tower[] factoryTowers(){
		Tower[] towers=new Tower[4];
		towers[0]=new Tower(DevCardType.Territory);
		towers[1]=new Tower(DevCardType.Building);
		towers[2]=new Tower(DevCardType.Character);
		towers[3]=new Tower(DevCardType.Venture);
		return towers;
	}
	
	public void pickCards(DevDeck devDeck) {
		for (int i = 0; i < floors.length; i++) {
			floors[i].getDevCardPlace().setCard(devDeck.getSingleCard());
		}
	}
	

	

	public boolean checkFamilyMemberPresence() {
		for (int i = 0; i < floors.length; i++) {
			if(floors[i].getSingleActionSpace().isBusy()){
				return true;
			}
			}
		    return false;
		}
	
	public boolean checkTowerFamilyMemberPlayer(Player player) {	
		for (int i = 0; i < floors.length; i++) {
			if(floors[i].getSingleActionSpace().isBusy()){
				FamilyMember familyMemberInFloor = floors[i].getSingleActionSpace().getFamilyMemberLocated();
			 if(!familyMemberInFloor.getFamilyMemberColor().equals(FamilyMemberColor.Neutral) && familyMemberInFloor.getOwnerPlayer().equals(player)){
				return true;
			 }
			}
		}
		    return false; 
		}

	
	public Floor[] getFloors() {
		return floors;
	}


	public void setFloors(Floor[] floors) {
		this.floors = floors;
	}
	
	
	public DevCardType getDevCardType() {
		return devCardType;
	}

	@Override
	public String toString() {
		return "Tower [" + devCardType + ", floors=" + Arrays.toString(floors) + "]";
	}
	
}
	
	

