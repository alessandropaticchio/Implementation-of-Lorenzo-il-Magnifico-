package it.polimi.ingsw.GC_21.UTILITIES;

import java.awt.List;

import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.CLIENT.PrivilegeMessage;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerForm;
import it.polimi.ingsw.GC_21.UTILITIES.*;
import it.polimi.ingsw.GC_21.VIEW.ExcommInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;

import java.util.*;

import javax.naming.spi.DirStateFactory.Result;
import javax.sound.sampled.LineListener;

public abstract class Observable<C> {
	private ArrayList<P2SObserver<C>> observers;
	private CurrentObserver currentObserver;
	
	public void attachCurrent(CurrentObserver currentObserver){
		this.currentObserver=currentObserver;
	}
	

	public void detachCurrent(){
		this.currentObserver=null;
	}
	
	public Observable() {
		observers = new ArrayList<P2SObserver<C>>();
	}

	public void attach(P2SObserver<C> o) {
		observers.add(o);
	}

	public void detach(P2SObserver<C> o) {
		this.observers.remove(o);
	}

	
	public void notifyCurrentString(String comunication){
		this.currentObserver.updateString(comunication);
	}
	
	public void notifyCurrent(MessageToClient message){
		for (P2SObserver<C> o : this.observers) {
			o.updateCurrent(message);
		}
	}
	
	
	
	public void notifyTurn() {	
		for (P2SObserver<C> o : this.observers) {
			o.updateTurn();
			detachCurrent();
		}
	}

	public void notifyExcomm(ExcommInput excommInput){
		for (P2SObserver<C> o : this.observers) {
			o.updateExcomm(excommInput);
		}
	}
	
	
	public void notifyString(String string) {	
		for (P2SObserver<C> o : this.observers) {
			o.update(string);
		}
	}
	
	public void notifyControllerManager(String string) {	
		for (P2SObserver<C> o : this.observers) {
			o.updateControllerManager(string);
		}
	}
	
	public boolean notifyController(ControllerForm controllerForm) {	
		boolean response=false;
		for (P2SObserver<C> o : this.observers) {
			response = o.updateMessage(controllerForm);
		}
		return response;
	}

	public boolean notifyObservers(C c) {
		for (P2SObserver<C> o : this.observers) {
			boolean result = o.update(c);
			if (!result) {
				return false;
			}
		}
		return true;

	}
	
	public void notifyInit() {
		for (P2SObserver<C> o : this.observers) {
			o.updateInit();
		}
	}
	
	public void notifyMessageToClient(MessageToClient messageToClient) {
		for (P2SObserver<C> o : this.observers) {
			o.updateMessageToClient(messageToClient);
		}
	}

}
