package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * This class represents an action where an actor enters a specific location through a door.
 * The action updates the actor's position in the game map to the entered location.
 *
 * @author Tan Yin Cheng
 * Modified by:
 */
public class EnterDoorAction extends Action{
    /**
     * The location that the actor enters when this action is performed.
     */
    private Location enterLocation;
    /**
     * The name of the location that the actor enters.
     */
    private String locationName;

    /**
     * Constructor of the EnterDoorAction
     * 
     * @param enterLocation The location that the actor will enter.
     * @param locationName The name of the location that the actor will enter.
     */
    public EnterDoorAction(Location enterLocation, String locationName){
        this.enterLocation = enterLocation;
        this.locationName = locationName;
    }

    /**
     * Executes the action of the actor entering a new location. 
     * The actor is moved to the specified location in the map.
     *
     * @param actor The actor who will perform the action.
     * @param map The game map, which will be updated with the actor's new location.
     * @return null because this action does not produce a result message.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, this.enterLocation);
        return null;
    }

    /**
     * Provides a description of this action for display in a menu.
     *
     * @param actor The actor who will perform the action.
     * @return A string describing the action, in the form "Actor enters locationName".
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " enters " + this.locationName;
    }
    
}
