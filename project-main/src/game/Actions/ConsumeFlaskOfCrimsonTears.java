package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Actors.Player;
import game.utils.Status;

/**
 * Consume Flask Of Crimson Tears Action which heals player hitpoint by 250
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class ConsumeFlaskOfCrimsonTears extends Action {

    /**
     * Player that consume the flask of crimson tears
     */
    private Player player;

    /**
     * Constructor
     * @param player Player that consume the flask of crimson tears
     */
    public ConsumeFlaskOfCrimsonTears(Player player){
        this.player = player;
    }

    /**
     * When executed, heal player by 250 hitpoints
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of this action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Item item : actor.getItemInventory()){
            if (item.hasCapability(Status.HEAL) && player.getFlaskOfCrimsonTears().getConsumeAmount() != 0){
                player.getFlaskOfCrimsonTears().setConsumeAmount(player.getFlaskOfCrimsonTears().getConsumeAmount()-1);
                actor.heal(250);
            }
        }
        return System.lineSeparator() + menuDescription(actor);
    }

    /**
     * Describes which actor consumed flask of crimson tears
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes flask of crimson tears";
    }
}
