package cz.neumimto.rpg.events;

import cz.neumimto.rpg.players.IActiveCharacter;
import cz.neumimto.rpg.scripting.JsBinding;


/**
 * Created by NeumimTo on 9.8.2015.
 */
@JsBinding(JsBinding.Type.CLASS)
public class ManaRegainEvent extends CancellableEvent {
	private IActiveCharacter character;
	private double newVal;
	private double amount;

	public ManaRegainEvent(IActiveCharacter character) {
		this.character = character;
	}

	public ManaRegainEvent(IActiveCharacter character, double newVal) {
		this.character = character;
		this.newVal = newVal;
	}

	public IActiveCharacter getCharacter() {
		return character;
	}

	public void setCharacter(IActiveCharacter character) {
		this.character = character;
	}

	public double getNewVal() {
		return newVal;
	}

	public void setNewVal(double newVal) {
		this.newVal = newVal;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
