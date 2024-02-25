package game.Grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Actions.SummonAction;
import game.utils.Status;

public class SummonSign extends Ground{

    /**
     * Constructor.
     */
    public SummonSign() {
        super('=');
        this.addCapability(Status.SPAWNED);
    }

    /**
     * Find the action that can be performed by the actor in this ground.
     * @param actor the actor which enter summon sign
     * @param location current location of actor
     * @param direction
     * @return action list which represents action that can be performed in this ground.
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actionList = new ActionList();
        actionList.add(new SummonAction());
        return actionList;
    }

    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }
}
