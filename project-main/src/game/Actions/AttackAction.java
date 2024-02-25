package game.Actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Actors.Enemies.PilesOfBones;
import game.utils.Status;
import game.Actors.Enemies.Enemies;
import game.Managers.RunesManager;

/**
 * An Action to attack another Actor.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Tan Yin Cheng, Muhammad Ibrahim bin Mohd Yusni
 *
 */
public class AttackAction extends Action {

	/**
	 * Getter for target
	 * @return the target of attack
	 */
	public Actor getTarget() {
		return target;
	}

	/**
	 * Setter for target
	 * @param target which represents the actor that we aim to attacking
	 */
	public void setTarget(Actor target) {
		this.target = target;
	}

	/**
	 * The Actor that is to be attacked
	 */
	private Actor target;

	/**
	 * The direction of incoming attack.
	 */
	private String direction;

	/**
	 * Getter for random number
	 * @return random number within the range
	 */
	public Random getRand() {
		return rand;
	}

	/**
	 * Getter for weapon
	 * @return weapon used for attack
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Setter for weapon
	 * @param weapon which represents the weapon used for attack
	 */
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	/**
	 * Random number generator
	 */
	private Random rand = new Random();

	/**
	 * Weapon used for the attack
	 */
	private Weapon weapon;

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction, Weapon weapon) {
		this.target = target;
		this.direction = direction;
		this.weapon = weapon;
	}

	/**
	 * Constructor with intrinsic weapon as default
	 *
	 * @param target the actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
	 * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
	 *
	 * @param actor The actor performing the attack action.
	 * @param map The map the actor is on.
	 * @return the result of the attack, e.g. whether the target is killed, etc.
	 * @see DeathAction
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Location currentLocation = null;

		// If no weapon is specified, use the actor's intrinsic weapon
		if (weapon == null) {
			weapon = actor.getIntrinsicWeapon();
		}

		 // Check if the attack misses based on the weapon's chance to hit, if it does not miss, continue with the rest of the code
		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		// Store and apply damage to the target
		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		// If the target is not conscious after taking damage
		if (!target.isConscious()) {

			// If the target has the capability to become Piles of Bones
			if (target.hasCapability(Status.BECOMEPILESOFBONES)){
				currentLocation = map.locationOf(target); //store location of target to be used later 
				map.removeActor(target); //remove target from location
				currentLocation.addActor(new PilesOfBones(target)); //add Piles of Bones to the location
				result += "\n" + target + " has turned into Piles of Bones.";

				// If the actor is hostile to the enemy which means actor is a Player, generate and add runes to the RunesManager 
				if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
					Enemies targets = (Enemies) target;
					int runes = targets.generateRunes(); //generate random runes to be given to player 
					RunesManager.getInstance().addRunes(runes); //add runes to player 
					result += "\n" + actor + " received " + runes + " Runes ";
				}


			}else{//if target does not have the capability to become Piles of Bones
				result += new DeathAction(actor).execute(target, map); //Perform normal DeathAction
			}
		}

		return result;
	}

	/**
	 * Describes which target the actor is attacking with which weapon
	 *
	 * @param actor The actor performing the action.
	 * @return a description used for the menu UI
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
	}
}
