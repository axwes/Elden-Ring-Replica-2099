package game.Actors.Merchants;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Weapons.*;
import game.utils.Status;
import game.Actions.BuyWeaponItemAction;


/**
 * A class representing the Merchant Kale.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by: Muhammad Ibrahim bin Mohd Yusni & Choo Carmen
 */
public class MerchantKale extends Merchant {

    /**
     * Constructor for Merchant Kale.
     */
    public MerchantKale() {
        super("Merchant Kale", 'K', 9999999);
    }

    /**
     * Returns the action to be performed during Merchant Kale's turn.
     *
     * @param actions collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Returns the list of allowable actions that can be performed by other actors.
     *
     * @param otherActor the Actor that might interact with Merchant Kale
     * @param direction String representing the direction of the other Actor
     * @param map current GameMap
     * @return list of allowable actions
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new BuyWeaponItemAction(new Uchigatana(), new Uchigatana().getBuyingPrice()));
            actions.add(new BuyWeaponItemAction(new GreatKnife(), new GreatKnife().getBuyingPrice()));
            actions.add(new BuyWeaponItemAction(new Club(), new Club().getBuyingPrice()));
            actions.add(new BuyWeaponItemAction(new Scimitar(), new Scimitar().getBuyingPrice()));

            // looping through otherActor inventory to check what they can sell
            actions.add(super.PlayerSellWeapons(otherActor));
        }

        return actions;
    }
    
}
