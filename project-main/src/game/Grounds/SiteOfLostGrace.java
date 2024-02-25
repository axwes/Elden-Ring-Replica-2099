package game.Grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Actions.RestAction;
import game.Actions.TouchAction;
import game.Managers.SiteOfLostGraceManager;
import game.utils.Status;

/**
 * A class representing Site of Lost Grace which allows player to rest.
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class SiteOfLostGrace extends Ground {

    public boolean touched;
    public String name;
    /**
     * Constructor for SiteOfLostGrace()
     * @param X represents x-coordinate of SiteOfLostGrace
     * @param Y represents y-coordinate of SiteOfLostGrace
     */
    public SiteOfLostGrace(int X, int Y, String name) {
        super('U');
        this.X=X;
        this.Y=Y;
        this.touched = false;
        this.name = name;
    }
    /**
     * x-coordinate fo SiteOfLostGrace
     */
    private int X;
    /**
     * Getter for x-coordinate of SiteOfLostGrace
     * @return x-coordinate of SiteOfLostGrace
     */
    public int getX() {
        return X;
    }
    /**
     * y-coordinate of SiteOfLostGrace
     */
    private int Y;
    /**
     * Getter for y-coordinate of SiteOfLostGrace
     * @return y-coordinate of SiteOfLostGrace
     */
    public int getY() {
        return Y;
    }
    /**
     * Check whether an actor can enter site of lost grace
     * @param actor the Actor to check
     * @return true if actor can enter else return false
     */
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.RESPAWNABLE);
    }

    public void touch() {
        this.touched = true;
    }

    /**
     * Find the action that can be performed by the actor in this ground.
     * @param actor the actor which enter site of lost grace
     * @param location current location of actor
     * @param direction
     * @return action list which represents action that can be performed in this ground.
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actionList = new ActionList();
        Actor isActor = location.getActor();
        if (canActorEnter(actor) & isActor != null){
            if (this.touched == false){
                actionList.add(new TouchAction(this.name, this));
            }
            else{
                actionList.clear();
                actionList.add(new RestAction(this.name));
                SiteOfLostGraceManager.setLastSiteOfLostGraceLocation(location);
            }
        }
        return actionList;
    }

}
