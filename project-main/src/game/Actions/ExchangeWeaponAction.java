package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;

/**
 * Class to exchange Godrick's weapon with Merchant FingerReaderEnia
 *
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class ExchangeWeaponAction extends Action {

    /**
     * Constructor
     * @param weapon Godrick's weapon to be exchanged
     */
    public ExchangeWeaponAction(WeaponItem weapon) {
        this.weapon = weapon;
    }

    /**
     * Getter for weapon
     * @return weapon to be exchanged
     */
    public WeaponItem getWeapon() {
        return weapon;
    }

    /**
     * Setter for weapon
     * @param weapon
     */
    public void setWeapon(WeaponItem weapon) {
        this.weapon = weapon;
    }

    /**
     * Weapon to be exchanged
     */
    private WeaponItem weapon;

    /**
     * Exchange Godrick's weapon using Remembrance of Godrick via Mechant FInger Reader Enia
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String which represents what weapon is exchange else if there is not
     * sufficient remembrance of Godrick return string to represent insufficient credit
     *
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Item item : actor.getItemInventory()){
            if (item.hasCapability(Status.EXCHANGABLE)){
                actor.removeItemFromInventory(item);
                actor.addWeaponToInventory(getWeapon());
                return actor + " has exchanged " + getWeapon() ;
            }
        }
            return "You do not have enough Remembrance Of Grafted";

    }

    /**
     * String to be displayed in menu UI which allows player to know what weapon can be exchanged
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Exchange " + getWeapon() + " with remembrances of Godrick";
    }
}
