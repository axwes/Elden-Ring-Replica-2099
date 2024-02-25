package game.Grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Actions.DeathAction;
import game.utils.Status;

/**
 * Represents a Cliff ground, which can only be entered by actors hostile to enemies.
 * Any actor entering the Cliff is immediately killed.
 *
 * @author Tan Yin Cheng
 */
public class Cliff extends Ground {
    /**
     * Constructor of the Cliff class.
     */
	public Cliff() {
		super('+');
	}
    /**
     * Determines if an actor can enter this Cliff.
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
     * Tick method for the Cliff, which is called every game turn.
     * If there is an actor on the Cliff, they are immediately killed.
     *
     * @param location The location of the Cliff.
     */
    public void tick(Location location){
        Actor isActor = location.getActor();
        if (isActor != null){
            Action dead = new DeathAction(null);
            dead.execute(isActor, location.map());
        }
    }
}
