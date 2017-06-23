package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.naming.TimeLimitExceededException;
import javax.net.ssl.SSLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.Pass;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ActionInput extends InputForm implements Callable<InputForm> {
	protected String choice;
	protected Scanner keyboard;
	protected Player player;
	protected boolean outOfTime;
	
	

	
	
	public InputForm chooseAction(Scanner keyboard, Player player) throws ExecutionException, InterruptedException {
		ActionInput actionInput = new ActionInput(keyboard, player);
		//ActionTimer actionTimer = new ActionTimer(actionInput);
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future future = executorService.submit(actionInput);
		InputForm inputForm = (InputForm) future.get();
		return inputForm;
		//executorService.submit(actionTimer);
		/*while (true){
			if (outOfTime){
				return new PassInput();
			}
			
		}*/
	}

	@Override
	public InputForm call(){
		System.out.println("Choose your action: " + "\n 1: Tower placement" + "\n 2: Craft placement "
				+ "\n 3: Market placement " + "\n 4: Council placement" + "\n 5: Pass" + "\n 6: Play Leader Card" + "\n 7: Discard Leader Card");
			String choice = keyboard.next();
			switch (choice) {
			case "1":
				TowerPlacementInput towerPlacementInput = new TowerPlacementInput();
				towerPlacementInput.inputFromCli(keyboard);
				return towerPlacementInput;
			case "2":
				CraftPlacementInput craftPlacementInput = new CraftPlacementInput();
				craftPlacementInput.inputFromCli(keyboard);
				return craftPlacementInput;
			case "3":
				MarketPlacementInput marketPlacementInput = new MarketPlacementInput();
				marketPlacementInput.inputFromCli(keyboard);
				return marketPlacementInput;
			case "4":
				CouncilPlacementInput councilPlacementInput = new CouncilPlacementInput();
				councilPlacementInput.inputFromCli(keyboard);
				return councilPlacementInput;
			case "5":
				PassInput passInput = new PassInput();
				passInput.inputFromCli(keyboard);
				return passInput;
			case "6":
				LeaderInput leaderInput = new LeaderInput(player);
				leaderInput.inputFromCli(keyboard);
				return leaderInput;
			case "7":
				DiscardInput discardInput = new DiscardInput(player);
				discardInput.inputFromCli(keyboard);
				return discardInput;
			default:
				System.out.println("Invalid input, try again!");
				return this.call();
			} 
	}
			public String getChoice() {
				return choice;
			}

			public void setChoice(String choice) {
				this.choice = choice;
			}

			public Scanner getKeyboard() {
				return keyboard;
			}

			public void setKeyboard(Scanner keyboard) {
				this.keyboard = keyboard;
			}

			public Player getPlayer() {
				return player;
			}

			public void setPlayer(Player player) {
				this.player = player;
			}

			public boolean isOutOfTime() {
				return outOfTime;
			}

			public void setOutOfTime(boolean outOfTime) {
				this.outOfTime = outOfTime;
			}

			public ActionInput(String choice) {
				this.choice = choice;
				outOfTime = false;
			}
			
			public ActionInput() {
				outOfTime = false;

			}
			
			public ActionInput(Scanner keyboard, Player player) {
				this.keyboard = keyboard;
				this.player = player;
				outOfTime = false;

			}
		
	}
	


