package game.Actions;

import java.util.List;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Managers.RunesManager;

/**
 * SellWeaponItemAction is a class that extends the Action class and represents the action of an Actor selling
 * a weapon from their inventory and receiving Runes in return.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by:
 */
public class SellWeaponItemAction extends Action {
    
    /**
	 * The WeaponItem to be sold
	 */
    private final WeaponItem item;

    /**
	 * The price for which the item will be sold
	 */
    protected int price; 

    /**
     * Constructor for the SellWeaponItemAction class.
     *
     * @param item  The WeaponItem to be sold
     * @param price The price for which the item will be sold
     */
    public SellWeaponItemAction(WeaponItem item, int price){
        this.item = item;
        this.price = price;
    }

    /**
     * Executes the SellWeaponItemAction by checking if the actor has the weapon in their inventory.
     * If so, the item is removed from the inventory, and the actor receives the corresponding Runes.
     *
     * @param actor The actor performing the action
     * @param map   The map the actor is on
     * @return A message describing the result of the action
     */    
    @Override
    public String execute(Actor actor, GameMap map) {
        int grossmesserCount = 0;
        boolean itemFound = false;
        List<WeaponItem> inventory = actor.getWeaponInventory();

         // Iterate through the actor's weapon inventory and check if the item exists
        for (WeaponItem weapon : inventory) {
            if (weapon.getDisplayChar() == item.getDisplayChar()) {
                itemFound = true;
            }
            if (weapon.getDisplayChar() == '?') {
                grossmesserCount++;
            }
        }
        
        // Sell the item and update the actor's Runes balance if the item is found and conditions are met
        if ((itemFound && item.getDisplayChar() != '?') || (item.getDisplayChar() == '?' && grossmesserCount > 1)){
            actor.removeWeaponFromInventory(item);
            RunesManager.getInstance().addRunes(price);
            return actor + " successfully sell " + item + " for " + price;
        }
        else if (item.getDisplayChar() == '?' && grossmesserCount == 1) {
            return "You can only sell " + item + " if you have more than one in your inventory!";
        }
        else {
            return "You do not have that weapon in your inventory!";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + item + " for " + price +" runes";
       
    }
    
}
