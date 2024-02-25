package game.Actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Actors.Enemies.Enemies;
import game.Actors.Enemies.PilesOfBones;
import game.Managers.RunesManager;
import game.utils.Status;

/**
 * Special skill for the weapon Uchigatana
 * Created by:
 * @author Choo Carmen
 * Modified by: Muhammad Ibrahim bin Mohd Yusni
 *
 */
public class UnsheatheAction extends AttackAction {
    /**
     * An actor which represents the enemy we aim to attack
     */
    private Actor target;
    /**
     * String which represents the direction of attack ( North, East, South, West .. etc within the exit )
     */
    private String direction;
    /**
     * Weapon used to perform the attack in this case would be Uchigatana
     */
    private Weapon weapon;
    /**
     * Integer which represents the Chance to hit enemy
     */
    private final int chanceToHit = 60;

    /**
     * Special skill for Uchigatana that deals 2x damage of the weapon with a 60% chance to hit the enemy.
     * @param target An actor which represents the enemy we aim to attack
     * @param direction String which represents the direction of attack ( North, East, South, West .. etc within the exit )
     * @param weapon Weapon used to perform the attack
     */
    public UnsheatheAction(Actor target, String direction, Weapon weapon) {
        super(target, direction, weapon);
        this.target=target;
        this.direction=direction;
        this.weapon=weapon;
    }

    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
     * It will have 2x damage of the weapon with a 60% chance to hit the enemy.
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     * @see DeathAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if (!(super.getRand().nextInt(100) <= this.chanceToHit)) {
            return actor + " misses " + super.getTarget() + ".";
        }

        // Deals double damage
        int damage = super.getWeapon().damage()*2;
        String result = actor + " " + super.getWeapon().verb() + " " + super.getTarget() + " for " + damage + " damage.";
        super.getTarget().hurt(damage);
        if (!super.getTarget().isConscious()) {
            // If the target has the capability to become Piles of Bones
            if (super.getTarget().hasCapability(Status.BECOMEPILESOFBONES)){
                Location currentLocation = map.locationOf(super.getTarget()); //store location of target to be used later
                map.removeActor(super.getTarget()); //remove target from location
                currentLocation.addActor(new PilesOfBones(super.getTarget())); //add Piles of Bones to the location
                result += super.getTarget() + " has turned into Piles of Bones.";

                // If the actor is hostile to the enemy which means actor is a Player, generate and add runes to the RunesManager
                if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                    Enemies targets = (Enemies) super.getTarget();
                    int runes = targets.generateRunes(); //generate random runes to be given to player
                    RunesManager.getInstance().addRunes(runes); //add runes to player
                    result += "\n" + actor + " received " + runes + " Runes ";
                }


            }else{//if target does not have the capability to become Piles of Bones
                result += new DeathAction(actor).execute(super.getTarget(), map); //Perform normal DeathAction
            }
        }

        return result;
    }

    /**
     * Describes which target the actor is attacking with which weapon and whether special skill of the weapon is used
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Unsheathe and attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon") ;
    }

    }

