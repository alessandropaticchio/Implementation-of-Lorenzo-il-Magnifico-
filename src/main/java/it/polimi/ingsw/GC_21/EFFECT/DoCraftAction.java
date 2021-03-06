package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;

import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.CLIENT.CraftMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;


public class DoCraftAction extends Effect {
	private CraftType craftType;
	protected int actionValueInfluencer;
	protected int actionValueBonus;
	
	public DoCraftAction(Game game, Possession rewards, CraftType craftType, int actionValueInfluencer, int actionValueBonus, int privileges) {
		super(rewards, privileges, game);
		this.craftType = craftType;
		this.actionValueInfluencer = actionValueInfluencer;
		this.actionValueBonus = actionValueBonus;
	}
	
	@Override
	// At first this effect give the player the rewards, then executes a craft
	public void activateEffect(Player player, Action action){
		super.activateEffect(player, action);
		PlacementAction placementAction = (PlacementAction) action;
		if (actionValueInfluencer==0 && action!=null){
			CraftMessage craftMessage = new CraftMessage(craftType, placementAction.getActionValue() + actionValueBonus);
			game.notifyCurrent(craftMessage);
		}
		else{
			CraftMessage craftMessage = new CraftMessage(craftType, actionValueInfluencer);
			game.notifyCurrent(craftMessage);
		}
	}
}
