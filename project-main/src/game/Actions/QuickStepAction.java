package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Actions.AttackAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Special skill for the weapon Great Knife
 * Created by:
 * @author Choo Carmen
 * Modified by:
 *
 */

public class QuickStepAction extends Action {

    /**
     * AttackAction which represents an Action to attack another Actor.
     */
    private AttackAction attack;
    /**
     * An Action that moves the Actor.
     */
    private MoveActorAction moveActor;
    /**
     * An Actor which represents the target of attack
     */
    private Actor target;
    /**
     * String which represents the direction of attack ( North, East, South, West .. etc within the exit )
     */
    private String direction;
    /**
     * Weapon used to perform the attack in this case would be Great Knife
     */
    private Weapon weapon;


    /**
     * Constructor
     * @param target An Actor which represents the target of attack
     * @param direction String which represents the direction of attack ( North, East, South, West .. etc within the exit )
     * @param weapon Weapon used to perform the attack in this case would be Great Knife
     */
    public QuickStepAction(Actor target, String direction, Weapon weapon){
        attack = new AttackAction(target, direction, weapon);
        this.target=target;
        this.direction=direction;
        this.weapon=weapon;
    }

    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
     * After dealing the damage, the user will move away from the enemy to any location within the surroundings,
     * evading the enemies attack.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     */
    public String execute(Actor actor, GameMap map) {
        Location loc = map.locationOf(actor);
        List<Exit> surroundings = loc.getExits();
        String output = attack.execute(actor, map);
        for (Exit exit: surroundings) {
            if (exit.getDestination().getGround().canActorEnter(actor)&& exit.getDestination().containsAnActor()== false){
                moveActor= new MoveActorAction(exit.getDestination(),exit.getName(), exit.getHotKey());
                output += moveActor.execute(actor,map);
                break;
            }
            else {
                output += "Quick Step can't be performed";
            }
        }
        return output;
    }



    /**
     * Describes which target the actor is attacking with which weapon and whether special skill of the weapon is used
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Quick Step and attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon") ;
    }


}
