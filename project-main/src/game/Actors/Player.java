package game.Actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Actions.ConsumeGoldenRunesAction;
import game.Actions.ConsumeFlaskOfCrimsonTears;
import game.Items.FlaskOfCrimsonTears;
import game.Managers.RunesManager;
import game.Managers.ResetManager;
import game.Interfaces.Resettable;
import game.utils.Status;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Choo Carmen, Tan Yin Cheng
 *
 */
public class Player extends Actor implements Resettable {
	/**
	 * Menu Manager of Player
	 */
	private final Menu menu = new Menu();

	/**
	 * Item Flask Of Crimson Tears that restore player's hp by 250 points
	 */
	private FlaskOfCrimsonTears flaskOfCrimsonTears = new FlaskOfCrimsonTears();

	/**
	 * Getter for attribute flaskOfCrimsonTears
	 * @return instance of flaskOfCrimsonTears
	 */
	public FlaskOfCrimsonTears getFlaskOfCrimsonTears() {
		return flaskOfCrimsonTears;
	}

	/**
	 * Setter for item flaskOfCrimsonTears
	 * @param flaskOfCrimsonTears item that restore player's hp by 250 points
	 */
	public void setFlaskOfCrimsonTears(FlaskOfCrimsonTears flaskOfCrimsonTears) {
		this.flaskOfCrimsonTears = flaskOfCrimsonTears;
	}

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.RESPAWNABLE);
		this.addItemToInventory(flaskOfCrimsonTears);
		ResetManager.getInstance().registerResettable(this);
	}

	/**
	 * Select and return an action to perform on the current turn.
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		RunesManager.getInstance().setLastLocation(map.locationOf(this));

		if (lastAction.getNextAction() != null){
			return lastAction.getNextAction();}

		// Check for Golden Runes
		for (Item item : this.getItemInventory()){
			if (item.hasCapability(Status.GENERATE_RUNES)){
				actions.add(new ConsumeGoldenRunesAction(item));
			}
		}

		display.print(printHp() + "\n");
		display.println("Runes: $" + RunesManager.getInstance().getTotalRunes());
		display.println("Flask Of Crimson Tears Left :" + this.getFlaskOfCrimsonTears().getConsumeAmount());
		if (this.getFlaskOfCrimsonTears().getConsumeAmount()>0){
		actions.add(new ConsumeFlaskOfCrimsonTears(this));}
		// return/print the console menu
		return menu.showMenu(this, actions, display);


	}

	/**
	 * Called by reset manager to reset player to have maximum hitpoint and maximum number of flask of crimson tears
	 * @param map which represents the current game map
	 */
	@Override
	public void reset(GameMap map) {
		this.hitPoints=0;
		this.heal(this.getMaxHp());
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 * The Actor 'punches' for 5 damage.
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return super.getIntrinsicWeapon();
	}

}
