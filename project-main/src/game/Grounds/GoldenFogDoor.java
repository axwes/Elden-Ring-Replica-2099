package game.Grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Actions.EnterDoorAction;
import game.utils.Status;
/**
 * Represents a GoldenFogDoor ground, which can only be entered by actors hostile to enemies.
 * It provides an action to enter a specified location when an actor attempts to do something on this ground.
 *
 * @author Tan Yin Cheng
 */
public class GoldenFogDoor extends Ground{
    /**
     * The location that can be entered through this GoldenFogDoor.
     */
    private Location enterLocation;
    /**
     * The name of the location that can be entered.
     */    
    private String nameOfLocation;

    /**
     * Constructor of the GoldenFogDoor class.
     *
     * @param enterLocation The location that can be entered through this GoldenFogDoor.
     * @param nameOfLocation The name of the location that can be entered.
     */
    public GoldenFogDoor(Location enterLocation, String nameOfLocation) {
        super('D');
        this.enterLocation = enterLocation;
        this.nameOfLocation = nameOfLocation;
    }

    /**
     * Determines if an actor can enter this GoldenFogDoor.
     * Only actors hostile to enemies can enter.
     *
     * @param actor The actor attempting to enter.
     * @return True if the actor is hostile to enemies, false otherwise.
     */
    public boolean canActorEnter(Actor actor){
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Provides a list of actions that an actor can perform on this GoldenFogDoor.
     * The actor can choose to enter the specified location.
     *
     * @param actor The actor trying to perform an action.
     * @param location The location of this GoldenFogDoor.
     * @param direction The direction of the action, not used in this method.
     * @return A list of actions that the actor can perform.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new EnterDoorAction(this.enterLocation, this.nameOfLocation));
        }

        return actions;
    }
    
}
