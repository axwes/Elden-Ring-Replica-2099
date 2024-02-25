package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Actors.Enemies.PilesOfBones;
import game.utils.Status;
/**
 * Represents an action that allows an actor to take a fixed amount of rot damage.
 * Created by:
 * @author Muhammad Ibrahim bin Mohd Yusni
 * Modified by:
 */
public class RotDamageAction extends Action {

    /**
     * The flat damage dealt by the Rot
     */
    private final int DAMAGE = 50;

    /**
     * When executed, the target takes a fixed amount of damage and determines whether the target is killed.
     *
     * @param actor The actor taking damage.
     * @param map The map the actor is on.
     * @return the result of the Rot damage, e.g. whether the target is killed, etc.
     * @see DeathAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location currentLocation;
        String result = "";
        // Apply damage to actor
        result += actor + " rots away for " + DAMAGE + " damage.";
        actor.hurt(DAMAGE);
        // If the target is not conscious after taking damage
        if (!actor.isConscious()) {
            // If the target has the capability to become Piles of Bones
            if (actor.hasCapability(Status.BECOMEPILESOFBONES)){
                currentLocation = map.locationOf(actor); //store location of target to be used later
                map.removeActor(actor); //remove target from location
                currentLocation.addActor(new PilesOfBones(actor)); //add Piles of Bones to the location
                result += "\n" + actor + " has turned into Piles of Bones.";

            }else{//if target does not have the capability to become Piles of Bones
                result += new DeathAction(actor).execute(actor, map); //Perform normal DeathAction
            }
        }
        return result;
    }

    /**
     * Describes the actor taking damage from rot
     * @param actor The actor taking damage.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " rots away for " + DAMAGE + " damage.";
    }

}
