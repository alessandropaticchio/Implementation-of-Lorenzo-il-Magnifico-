package it.polimi.ingsw.GC_21.BOARD;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class CraftArea {
	
	private final CraftType craftType;
	private final SingleActionSpace singleActionSpace;
	private final MultipleActionSpace multipleActionSpace;
	
	public CraftArea(CraftType craftType, Immediate MalusOnCraft) {
		super();
		this.craftType = craftType;
		this.singleActionSpace = new SingleActionSpace(1, null);
		this.multipleActionSpace = new MultipleActionSpace(1, MalusOnCraft);
	}

	public boolean checkFamilyMemberColor(Color color) {//return true if there is a family member with the indicated color in the singleActionSpace or in the multiple
		if (color.equals(Color.Neutral)) {//if the Family Member is Neutral the check is not important 
			return false;
		}
		else {
			return singleActionSpace.getFamilyMember().getColor().equals(color) || 
					multipleActionSpace.checkColorPresence(color);
		}
		
		
	}


	public CraftType getCraftType() {
		return craftType;
	}

	public SingleActionSpace getSingleActionSpace() {
		return singleActionSpace;
	}


	public MultipleActionSpace getMultipleActionSpace() {
		return multipleActionSpace;
	}


}