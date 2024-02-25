package game.Actors.Merchants;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Actions.ExchangeWeaponAction;
import game.Weapons.*;
import game.utils.Status;

/**
 * Class which represents Finger Reader Enia which can accept the Remembrance of the Grafted
 * from the player to be exchanged for either the Axe of Godrick or Grafted Dragon.
 * The player cannot purchase weapons from this trader, but they can still sell
 * anything that is sellable to this trader.
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class FingerReaderEnia extends Merchant {
    /**
     * Constructor for a Merchant.
     */
    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E', 9999999);
    }

    /**
     * Select and return an action to perform on the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Do nothing action
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new ExchangeWeaponAction(new GraftedDragon()));
            actions.add(new ExchangeWeaponAction(new AxeOfGodrick()));
            actions.add(super.PlayerSellWeapons(otherActor));
        }

        return actions;
    }
}
