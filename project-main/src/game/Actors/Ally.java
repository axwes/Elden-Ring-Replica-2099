package game.Actors;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Actions.AttackAction;
import game.Behaviours.AttackOthersBehaviour;
import game.Interfaces.Behaviour;
import game.Behaviours.WanderBehaviour;
import game.Interfaces.Summoned;
import game.utils.Status;
import java.util.HashMap;
import java.util.Map;

/**
 * An class representing Ally which help the player defeat enemies or Invaders.
 * Does not attack player or other friendly actors, e.g. Traders or other allies.
 * The Player should not attack Ally.
 *
 * Created by:
 * @author Choo Carmen
 * Modified by:
 * @see Summoned
 */
public class Ally extends Actor implements Summoned {
    /**
     * Constructor.
     */
    public Ally() {
        super("Ally", 'A', 0);
        this.setCharacter(this);
        this.behaviours.put(3, new WanderBehaviour());
        this.behaviours.put(1, new AttackOthersBehaviour());
        this.addCapability(Status.ALIES);

    }

    /**
     * A map containing the behaviors for the enemy with their associated priority as the key.
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Method which allows Ally to perform action automatically in the game
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action which Ally will be performing
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Action action = new DoNothingAction();
        for (Behaviour behaviour : behaviours.values()){
            action=behaviour.getAction(this,map);
            if(action!= null){
                return action;
            }
        }
        return action;}

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.ENEMY) || otherActor.hasCapability(Status.INVASIVE)){
            actions.add(new AttackAction(this, direction));
        }

        else {
            actions.add(new DoNothingAction());
        }

        return actions;}
}
